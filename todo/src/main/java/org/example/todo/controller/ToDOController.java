package org.example.todo.controller;

import org.example.todo.interfaces.ToDoInterface;
import org.example.todo.model.ToDo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class ToDOController {
    private ToDoInterface toDoService;

    public ToDOController(ToDoInterface toDoService) {
        this.toDoService = toDoService;
    }

    @RequestMapping("/{name}")
    @ResponseBody
    public ToDo getToDo(@PathVariable String name) {
        return toDoService.getToDo(name);
    }

    @RequestMapping("/todos")
    @ResponseBody
    public List<ToDo> getAllToDos() {
        return toDoService.getAllToDos();
    }
}
