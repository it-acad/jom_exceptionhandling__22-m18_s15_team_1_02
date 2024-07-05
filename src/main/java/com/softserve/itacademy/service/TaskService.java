package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Task;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface TaskService {

    Task create(Task task) throws NullEntityReferenceException;
    Task readById(long id) throws EntityNotFoundException;
    Task update(Task task) throws NullEntityReferenceException, EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

    List<Task> getAll();
    List<Task> getByTodoId(long todoId);

}
