package ru.kata.spring.boot_security_bootstrap.demo.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ru.kata.spring.boot_security_bootstrap.demo.entity.Role;
import ru.kata.spring.boot_security_bootstrap.demo.repo.RoleRepository;

import java.util.List;

@Service
public class RoleService {
    private final RoleRepository roleRepository;

    @Autowired
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    public List<Role> roles() {
        return roleRepository.findAll();
    }
}
