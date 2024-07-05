package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.State;
import com.softserve.itacademy.repository.StateRepository;
import com.softserve.itacademy.service.StateService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class StateServiceImpl implements StateService {

    private static final String NULL_ENTITY_EXCEPTION_MESSAGE = "The passed State object cannot be null.";

    private final StateRepository stateRepository;

    public StateServiceImpl(StateRepository stateRepository) {
        this.stateRepository = stateRepository;
    }

    @Override
    public State create(State state) throws NullEntityReferenceException {
        if(state == null) {
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION_MESSAGE);
        }

        return stateRepository.save(state);
    }

    @Override
    public State readById(long id) throws EntityNotFoundException {
        return stateRepository.findById(id)
                .orElseThrow(() -> new EntityNotFoundException(String.format("An object of class State could not be found by the specified identifier \"%d\".", id)));
    }

    @Override
    public State update(State state) throws NullEntityReferenceException, EntityNotFoundException {
        if(state == null) {
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION_MESSAGE);
        }

        readById(state.getId());
        return stateRepository.save(state);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        State state = readById(id);
        stateRepository.delete(state);
    }

    @Override
    public State getByName(String name) throws EntityNotFoundException {
        return Optional.ofNullable(stateRepository.getByName(name))
                .orElseThrow(() -> new EntityNotFoundException(String.format("An object of class State could not be found by the specified name \"%s\".", name)));
    }

    @Override
    public List<State> getAll() {
        List<State> states = stateRepository.getAll();
        return states.isEmpty() ? new ArrayList<>() : states;
    }

}
