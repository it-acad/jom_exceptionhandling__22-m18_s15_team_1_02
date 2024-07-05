package com.softserve.itacademy.service;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;

import javax.persistence.EntityNotFoundException;
import java.util.List;

public interface RoleService {
    Role create(Role role) throws NullEntityReferenceException;
    Role readById(long id) throws EntityNotFoundException;
    Role update(Role role) throws NullEntityReferenceException, EntityNotFoundException;
    void delete(long id) throws EntityNotFoundException;
    List<Role> getAll();
}
