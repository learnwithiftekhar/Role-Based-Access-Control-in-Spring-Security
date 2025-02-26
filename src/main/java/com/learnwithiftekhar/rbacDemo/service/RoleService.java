package com.learnwithiftekhar.rbacDemo.service;

import com.learnwithiftekhar.rbacDemo.model.Role;
import com.learnwithiftekhar.rbacDemo.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    public RoleService(RoleRepository roleRepository) {
        this.roleRepository = roleRepository;
    }

    @Transactional
    public Role createRole(String name) {

        if(roleRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Role already exists");
        }

        Role role = new Role(name);
        return roleRepository.save(role);
    }


    public List<Role> findAllRoles() {
        return roleRepository.findAll();
    }

    public Role findRoleByName(String name) {
        return roleRepository.findByName(name).orElseThrow(() -> new RuntimeException("Role not found"));
    }


}
