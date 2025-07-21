package org.example.getsionetudiants.model;
import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Student {
    private UUID id;
    private String nom;
    private String prenom;
    private int age;
    private String email;
}
