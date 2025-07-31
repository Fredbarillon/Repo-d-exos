package org.app.tpdocker.model.entity;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor

public class Fart {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String smell;

    private int dissipation;

    private double soundVolume;

    private String origin;
}
