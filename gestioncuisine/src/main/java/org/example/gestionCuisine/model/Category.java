package org.example.gestionCuisine.model;

import lombok.*;

import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Category {
    private UUID id;
    private String name;
    private String description;

}
