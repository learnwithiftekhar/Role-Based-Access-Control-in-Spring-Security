package com.learnwithiftekhar.rbacDemo.config;

import com.learnwithiftekhar.rbacDemo.model.Permission;
import com.learnwithiftekhar.rbacDemo.model.Role;
import com.learnwithiftekhar.rbacDemo.model.User;
import com.learnwithiftekhar.rbacDemo.repository.PermissionRepository;
import com.learnwithiftekhar.rbacDemo.repository.RoleRepository;
import com.learnwithiftekhar.rbacDemo.repository.UserRepository;
import com.learnwithiftekhar.rbacDemo.service.RoleService;
import com.learnwithiftekhar.rbacDemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;

import java.util.Set;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleRepository roleRepository;
    private final PermissionRepository permissionRepository;
    private final UserRepository userRepository;
    private final PasswordEncoder passwordEncoder;

    public DataInitializer(UserService userService, RoleRepository roleRepository, PermissionRepository permissionRepository, UserRepository userRepository, PasswordEncoder passwordEncoder) {
        this.userService = userService;
        this.roleRepository = roleRepository;
        this.permissionRepository = permissionRepository;
        this.userRepository = userRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public void run(String... args) throws Exception {

        // Create permissions
        Permission readUser = new Permission();
        readUser.setName("user:read");

        Permission writeUser = new Permission();
        writeUser.setName("user:write");

        Permission deleteUser = new Permission();
        deleteUser.setName("user:delete");

        Permission readProduct = new Permission();
        readProduct.setName("product:read");

        Permission writeProduct = new Permission();
        writeProduct.setName("product:write");

        Permission deleteProduct = new Permission();
        deleteProduct.setName("product:delete");

        Permission readOrder = new Permission();
        readOrder.setName("order:read");

        Permission writeOrder = new Permission();
        writeOrder.setName("order:write");

        Permission deleteOrder = new Permission();
        deleteOrder.setName("order:delete");

        permissionRepository.saveAll(Set.of(
                readUser, writeUser, deleteUser,
                readProduct, writeProduct, deleteProduct,
                readOrder, writeOrder, deleteOrder
        ));

        // Create roles and assign permissions
        Role adminRole = new Role();
        adminRole.setName("ADMIN");
        adminRole.setPermissions(Set.of(
                readUser, writeUser, deleteUser,
                readProduct, writeProduct, deleteProduct,
                readOrder, writeOrder, deleteOrder
        ));

        Role managerRole = new Role();
        managerRole.setName("MANAGER");
        managerRole.setPermissions(Set.of(
                readUser, writeUser,
                readProduct, writeProduct,
                readOrder, writeOrder
        ));

        Role userRole = new Role();
        userRole.setName("USER");
        userRole.setPermissions(Set.of(
                readUser,
                readProduct,
                readOrder
        ));

        roleRepository.saveAll(Set.of(adminRole, managerRole, userRole));

        // Create sample users
        User admin = new User();
        admin.setUsername("admin");
        admin.setPassword(passwordEncoder.encode("password"));
        admin.setRoles(Set.of(adminRole));

        User manager = new User();
        manager.setUsername("manager");
        manager.setPassword(passwordEncoder.encode("password"));
        manager.setRoles(Set.of(managerRole));

        User user = new User();
        user.setUsername("user");
        user.setPassword(passwordEncoder.encode("password"));
        user.setRoles(Set.of(userRole));

        userRepository.saveAll(Set.of(admin, manager, user));

        System.out.println("Sample data initialized successfully!");
    }
}
