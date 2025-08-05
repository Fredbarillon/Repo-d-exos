package org.example.environement.service;

import lombok.RequiredArgsConstructor;
import org.example.environement.dto.travellogs.TravelLogDtoResponse;
import org.example.environement.dto.travellogs.TravelLogDtoStat;
import org.example.environement.entity.TravelLog;
import org.example.environement.repository.TravelLogRepository;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.util.*;

@Service
@RequiredArgsConstructor
public class TravelLogsService {

    private final TravelLogRepository repository;

    public List<TravelLogDtoResponse> get(int limit) {
        List<TravelLogDtoResponse> result = new ArrayList<>();
        int inc = 0;

        for (TravelLog log : repository.findAll()) {
            if (inc++ >= limit) break;
            result.add(log.entityToDto());
        }

        return result;
    }

    public TravelLogDtoStat getStat(long observationId) {
        List<TravelLog> logs = repository.findTravelLogByObservation_Id(observationId);
        return buildStat(logs);
    }

    public Map<String, TravelLogDtoStat> getStatForUserLastMonth(String username) {
        LocalDate oneMonthAgo = LocalDate.now().minusMonths(1);

        List<TravelLog> logs = new ArrayList<>();
        repository.findAll().forEach(log -> {
            if (log.getObservation() != null &&
                    username.equalsIgnoreCase(log.getObservation().getObserverName()) &&
                    log.getObservation().getObservationDate() != null &&
                    log.getObservation().getObservationDate().isAfter(oneMonthAgo)) {
                logs.add(log);
            }
        });

        Map<String, List<TravelLog>> grouped = new HashMap<>();
        for (TravelLog log : logs) {
            String mode = log.getMode().name();
            grouped.computeIfAbsent(mode, k -> new ArrayList<>()).add(log);
        }

        Map<String, TravelLogDtoStat> result = new HashMap<>();
        for (Map.Entry<String, List<TravelLog>> entry : grouped.entrySet()) {
            result.put(entry.getKey(), buildStat(entry.getValue()));
        }

        return result;
    }

    private TravelLogDtoStat buildStat(List<TravelLog> logs) {
        TravelLogDtoStat stat = new TravelLogDtoStat();

        for (TravelLog log : logs) {
            double distance = log.getDistanceKm() != null ? log.getDistanceKm() : 0.0;
            double co2 = log.getEstimationCo2Kg() != null ? log.getEstimationCo2Kg() : 0.0;
            String mode = log.getMode().name();

            stat.addTotalDistanceKm(distance);
            stat.addTotalEmissionsKg(co2);

            double current = stat.getByMode().getOrDefault(mode, 0.0);
            stat.addMode(mode, current + distance);
        }

        return stat;
    }
}
