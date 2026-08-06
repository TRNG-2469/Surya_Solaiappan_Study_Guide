package com.rev.web;

import com.rev.web.DAO.ToDoDAO;
import com.rev.web.service.ToDoService;
import com.rev.web.DAO.ToDoDAOImpl;
import com.rev.web.controller.ToDoController;
import com.rev.web.exceptions.ErrorResponse;
import io.javalin.Javalin;

public class ToDoApp {

    public static void main(String[] args) {

        ToDoDAO dao = new ToDoDAOImpl();
        ToDoService service = new ToDoService(dao);
        ToDoController todoController = new ToDoController(service);

        Javalin app = Javalin.create().start(8080);

        // endpoint 1. retrieve all todos
        app.get("/api/todos", todoController::getAllTodos);
        app.get("/api/todos/{id}", todoController::getTodoById);
        app.delete("/api/todos/{id}", todoController::deleteTodo);
        app.post("/api/todos", todoController::createTodo);
        app.put("/api/todos/{id}", todoController::updateTodo);

        app.exception(IllegalArgumentException.class, (e, ctx) -> {
            ctx.status(400);
            ctx.json(new ErrorResponse("An unexpected client error occurred."));
        });

        app.exception(Exception.class, (e, ctx) -> {
            ctx.status(500);
            ctx.json(new ErrorResponse("An unexpected server error occurred"));
        });
    }
}