package org.example.todo.model;

import lombok.*;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class ToDo {
    private  String name;
    private  String description;
    private  boolean isDone;

}
