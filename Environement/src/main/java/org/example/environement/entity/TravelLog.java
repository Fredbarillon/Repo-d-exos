package org.example.environement.entity;

import jakarta.persistence.*;
import lombok.*;
import org.example.environement.dto.travellogs.TravelLogDtoResponse;
import org.example.environement.entity.enums.TravelMode;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Builder
@Getter
@Setter

public class TravelLog {
    @Id
    @GeneratedValue
    private long id;
    @Column(nullable = false)
    private Double estimationCo2Kg;
    @Column(nullable = false)
    private Double distanceKm;
    @Enumerated(EnumType.STRING)
    private TravelMode mode;
    @ManyToOne
    @JoinColumn(name = "observation_id")
    private Observation observation;

    public TravelLogDtoResponse entityToDto() {
        return TravelLogDtoResponse.builder()
                .id(id)
                .estimatedCo2Kg(estimationCo2Kg)
                .distanceKm(distanceKm)
                .mode(String.valueOf(mode))
                .build();
    }

    public void calculateCO2() {
        if (distanceKm != null && mode != null) {
            double gazRelease = switch (mode) {
                case CAR -> 0.192;
                case PLANE -> 0.255;
                case TRAIN -> 0.041;
                case BIKE -> 0.0;
                case WALKING -> 0.0;
                case BUS -> 0.050;
            };
            this.estimationCo2Kg = distanceKm * gazRelease;
        } else {
            this.estimationCo2Kg = 0.0;
        }
    }
}
