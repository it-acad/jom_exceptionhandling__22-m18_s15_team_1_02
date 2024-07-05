package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.User;
import com.softserve.itacademy.repository.UserRepository;
import com.softserve.itacademy.service.UserService;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserServiceImpl implements UserService {

    private static final String NULL_ENTITY_EXCEPTION = "Reference to an entity is null";
    private static final String ENTITY_NOT_FOUND_EXCEPTION = "Reference to an entity is not found";

    private UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public User create(User user) {
        if(user == null){
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION);
        }

            return userRepository.save(user);
    }

    @Override
    public User readById(long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        Optional<User> optional = userRepository.findById(id);
            return optional.get();
    }

    @Override
    public User update(User user) {
        if(user == null){
            throw new NullEntityReferenceException(NULL_ENTITY_EXCEPTION);
        }
            User oldUser = readById(user.getId());
                return userRepository.save(user);
    }

    @Override
    public void delete(long id) {
        if(!userRepository.existsById(id)){
            throw new EntityNotFoundException(ENTITY_NOT_FOUND_EXCEPTION);
        }
        User user = readById(id);
            userRepository.delete(user);
    }

    @Override
    public List<User> getAll() {
        List<User> users = userRepository.findAll();
        return users.isEmpty() ? new ArrayList<>() : users;
    }

}
