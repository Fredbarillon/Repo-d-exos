package org.example.environement.repository;

import org.example.environement.entity.TravelLog;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.PagingAndSortingRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface TravelLogRepository extends JpaRepository<TravelLog, Long> {
    public List<TravelLog> findTravelLogByObservation_Id (long id);

   @Query("select t from TravelLog t where t.observation.observerName = ?1 and t.observation.observationDate > ?2")
   public List<TravelLog> findTravelLogByUserForLastMonth (String user, LocalDate date);
}
