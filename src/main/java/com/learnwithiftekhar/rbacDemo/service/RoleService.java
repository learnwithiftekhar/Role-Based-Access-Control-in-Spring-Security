package com.learnwithiftekhar.rbacDemo.service;

import com.learnwithiftekhar.rbacDemo.model.Permission;
import com.learnwithiftekhar.rbacDemo.model.Role;
import com.learnwithiftekhar.rbacDemo.repository.PermissionRepository;
import com.learnwithiftekhar.rbacDemo.repository.RoleRepository;
import jakarta.transaction.Transactional;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class RoleService {

    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;

    public RoleService(RoleRepository roleRepository, PermissionRepository permissionRepository) {
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
    }

    public List<Role> getAllRoles() {
        return roleRepository.findAll();
    }

    public Optional<Role> getRoleById(Long id) {
        return roleRepository.findById(id);
    }

    public Optional<Role> getRoleByName(String name) {
        return roleRepository.findByName(name);
    }

    @Transactional
    public Role createRole(String name) {
        if(roleRepository.findByName(name).isPresent()) {
            throw new RuntimeException("Role with name " + name + " already exists");
        }

        Role role = new Role();
        role.setName(name);
        return roleRepository.save(role);
    }

    @Transactional
    public void assignPermissionToRole(String roleName, String permissionName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role with name " + roleName + " does not exist"));
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission with name " + permissionName + " does not exist"));

        role.addPermission(permission);
        roleRepository.save(role);
    }

    @Transactional
    public void unassignPermissionFromRole(String roleName, String permissionName) {
        Role role = roleRepository.findByName(roleName)
                .orElseThrow(() -> new RuntimeException("Role with name " + roleName + " does not exist"));
        Permission permission = permissionRepository.findByName(permissionName)
                .orElseThrow(() -> new RuntimeException("Permission with name " + permissionName + " does not exist"));

        role.getPermissions().remove(permission);
        roleRepository.save(role);
    }

    @Transactional
    public void updateRole(Role role) {
        roleRepository.save(role);
    }

    @Transactional
    public void deleteRole(Long id) {
        roleRepository.deleteById(id);
    }
}
