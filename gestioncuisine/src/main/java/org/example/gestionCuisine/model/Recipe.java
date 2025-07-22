package org.example.gestionCuisine.model;
import lombok.*;

import java.util.List;
import java.util.Map;
import java.util.UUID;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class Recipe {
    private UUID id;
    private String name;
    private List<String> ingredients;
    private Map<Integer,String> instructions;
    private UUID categoryId;
}
