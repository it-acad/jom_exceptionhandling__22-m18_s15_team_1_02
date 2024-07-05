package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Task;
import com.softserve.itacademy.repository.TaskRepository;
import com.softserve.itacademy.service.TaskService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class TaskServiceImpl implements TaskService {

    private static final String NULL_ENTITY_EXCEPTION_MESSAGE = "The passed Task object cannot be null.";

    private final TaskRepository taskRepository;

    public TaskServiceImpl(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    @Override
    public Task create(Task user) throws NullEntityReferenceException {
        if(user == null) {
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION_MESSAGE);
        }

        return taskRepository.save(user);
    }

    @Override
    public Task readById(long id) throws EntityNotFoundException {
        return taskRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("An object of class Task could not be found by the specified identifier \"%d\".", id)));
    }

    @Override
    public Task update(Task task) throws NullEntityReferenceException, EntityNotFoundException {
        if(task == null) {
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION_MESSAGE);
        }

        readById(task.getId());
        return taskRepository.save(task);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Task task = readById(id);
        taskRepository.delete(task);
    }

    @Override
    public List<Task> getAll() {
        List<Task> tasks = taskRepository.findAll();
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }

    @Override
    public List<Task> getByTodoId(long todoId) {
        List<Task> tasks = taskRepository.getByTodoId(todoId);
        return tasks.isEmpty() ? new ArrayList<>() : tasks;
    }
}
