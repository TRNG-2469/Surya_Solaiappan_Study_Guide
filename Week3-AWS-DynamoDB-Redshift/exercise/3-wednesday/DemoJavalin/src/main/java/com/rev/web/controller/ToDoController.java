package com.rev.web.controller;

import com.rev.web.model.ToDo;
import com.rev.web.service.ToDoService;
import io.javalin.http.Context;

import java.util.List;

public class ToDoController {

    private final ToDoService service;

    public ToDoController(ToDoService service) {
        this.service = service;
    }

    public void getAllTodos(Context ctx) {
        List<ToDo> todos = service.getAll();
        ctx.json(todos);
    }

    public void getTodoById(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ToDo todo = service.get(id);
        ctx.json(todo);
    }

    public void deleteTodo(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        service.delete(id);
        ctx.status(204);
    }

    public void createTodo(Context ctx) {
        ToDo payload = ctx.bodyAsClass(ToDo.class);
        ToDo newTodo = service.insert(payload);
        ctx.status(201);
        ctx.json(newTodo);
    }

    public void updateTodo(Context ctx) {
        int id = Integer.parseInt(ctx.pathParam("id"));
        ToDo payload = ctx.bodyAsClass(ToDo.class);
        payload.setId(id);
        ToDo updated = service.update(payload);
        ctx.json(updated);
    }
}