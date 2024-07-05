package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.exception.NullEntityReferenceException;
import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import java.util.ArrayList;
import java.util.List;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private static final String ROLE_CANNOT_BE_NULL = "Role cannot be null";
    private static final String ROLE_NOT_FOUND = "Role not found";
    private static final String ROLE_NOT_FOUND_WITH_ID = "Role not found with id ";

    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) throws NullEntityReferenceException {
        if (role == null){
            throw new NullEntityReferenceException(ROLE_CANNOT_BE_NULL);
        }
        return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) throws EntityNotFoundException {
       return roleRepository.findById(id)
               .orElseThrow(() -> new EntityNotFoundException(ROLE_NOT_FOUND));
    }

    @Override
    public Role update(Role role) throws EntityNotFoundException, NullEntityReferenceException {
        if (role == null){
            throw new NullEntityReferenceException(ROLE_CANNOT_BE_NULL);
        }
        if (!roleRepository.existsById(role.getId())){
            throw new EntityNotFoundException(ROLE_NOT_FOUND_WITH_ID + role.getId());
        }
        return roleRepository.save(role);
    }

    @Override
    public void delete(long id) throws EntityNotFoundException {
        Role role = readById(id);
            roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
