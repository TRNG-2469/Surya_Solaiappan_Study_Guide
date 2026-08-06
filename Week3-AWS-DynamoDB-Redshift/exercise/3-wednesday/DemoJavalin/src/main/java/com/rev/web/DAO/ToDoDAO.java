package com.rev.web.DAO;

import com.rev.web.model.ToDo;

import java.util.List;

public interface ToDoDAO {

    ToDo get(int id);
    List<ToDo> getAll();
    ToDo insert(ToDo task);
    ToDo update(ToDo task);
    void delete(int id);

}
