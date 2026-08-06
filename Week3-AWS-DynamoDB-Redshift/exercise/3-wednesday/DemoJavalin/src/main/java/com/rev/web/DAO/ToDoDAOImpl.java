package com.rev.web.DAO;

import com.rev.web.model.ToDo;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.atomic.AtomicInteger;

public class ToDoDAOImpl implements ToDoDAO {
    private static final ConcurrentHashMap<Integer, ToDo> db= new ConcurrentHashMap<>();
    private static final AtomicInteger idSequence = new AtomicInteger(0);

    static{
        int id1 = idSequence.incrementAndGet();
        db.put(1, new ToDo(id1, "Learn Javalin", false));
        int id2 = idSequence.incrementAndGet();
        db.put(2, new ToDo(id2, "Learn Java", false));
    }

    @Override
    public ToDo get(int id) {
        return db.get(id);
    }

    @Override
    public List<ToDo> getAll() {
        return new ArrayList<>(db.values());
    }

    @Override
    public ToDo insert(ToDo task) {
        int newId = idSequence.incrementAndGet();
        ToDo newTodo = new ToDo(newId, task.getTitle(), task.isComplete());
        db.put(newId, newTodo);
        return newTodo;
    }

    @Override
    public ToDo update(ToDo task) {
        ToDo updated = new ToDo(task.getId(), task.getTitle(), task.isComplete());
        db.put(task.getId(), updated);
        return updated;
    }

    @Override
    public void delete(int id) {
        db.remove(id);
    }
}
