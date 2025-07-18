package org.example.todo.interfaces;

import org.example.todo.model.ToDo;

import java.util.List;

public interface ToDoInterface {
    List<ToDo> getAllToDos();
    ToDo getToDo(String name);

}
