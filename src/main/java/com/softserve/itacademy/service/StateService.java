package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.State;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface StateService {

    State create(State state) throws NullEntityReferenceException;
    State readById(long id) throws EntityNotFoundException;
    State update(State state) throws NullEntityReferenceException, EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;

    State getByName(String name) throws EntityNotFoundException;
    List<State> getAll();

}
