package org.example.todo.service;

import org.example.todo.interfaces.ToDoInterface;
import org.example.todo.model.ToDo;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ToDoService implements ToDoInterface {
    public List<ToDo> toDoList;

    public ToDoService() {
        toDoList = new ArrayList<>();
        toDoList.add(new ToDo("fun", "Jouer à Doom", false));
        toDoList.add(new ToDo("chores", "Faire la vaisselle", false));
        toDoList.add(new ToDo("famille", "Appeler papa", false));
        toDoList.add(new ToDo("autre", "La... ça rend sourd", false));
    }

    @Override
    public List<ToDo> getAllToDos() {
        return toDoList;
    }

    @Override
    public ToDo getToDo(String name) {
        for (ToDo todo : toDoList) {
            if (todo.getName().equals(name)) {
                return todo;
            }
        }
        return null;
    }
}