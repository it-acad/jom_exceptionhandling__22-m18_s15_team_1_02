package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.ToDo;

import javax.persistence.EntityNotFoundException;
import javax.validation.constraints.Null;
import java.util.List;

public interface ToDoService {
    ToDo create(ToDo todo) throws NullEntityReferenceException;
    ToDo readById(long id) throws EntityNotFoundException;
    ToDo update(ToDo todo) throws NullEntityReferenceException, EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

    List<ToDo> getAll();
    List<ToDo> getByUserId(long userId);
}
