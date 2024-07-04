package com.softserve.itacademy.service.impl;

import com.softserve.itacademy.model.Role;
import com.softserve.itacademy.repository.RoleRepository;
import com.softserve.itacademy.service.RoleService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class RoleServiceImpl implements RoleService {

    private final RoleRepository roleRepository;

    @Override
    public Role create(Role role) {
            return roleRepository.save(role);
    }

    @Override
    public Role readById(long id) {
        Optional<Role> optional = roleRepository.findById(id);
            return optional.get();
    }

    @Override
    public Role update(Role role) {
            Role oldRole = readById(role.getId());
                return roleRepository.save(role);
    }

    @Override
    public void delete(long id) {
        Role role = readById(id);
            roleRepository.delete(role);
    }

    @Override
    public List<Role> getAll() {
        List<Role> roles = roleRepository.findAll();
        return roles.isEmpty() ? new ArrayList<>() : roles;
    }
}
