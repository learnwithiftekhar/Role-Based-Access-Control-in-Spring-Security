package com.learnwithiftekhar.rbacDemo.config;

import com.learnwithiftekhar.rbacDemo.model.Role;
import com.learnwithiftekhar.rbacDemo.model.User;
import com.learnwithiftekhar.rbacDemo.service.RoleService;
import com.learnwithiftekhar.rbacDemo.service.UserService;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

@Component
public class DataInitializer implements CommandLineRunner {
    private final UserService userService;
    private final RoleService roleService;

    public DataInitializer(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @Override
    public void run(String... args) throws Exception {
        Role adminRole = roleService.createRole("ADMIN");
        Role managerRole = roleService.createRole("MANAGER");
        Role userRole = roleService.createRole("USER");

        User adminUser = new User();
        adminUser.setUsername("admin");
        adminUser.setPassword("password");
        adminUser.setRole(adminRole);
        userService.createUser(adminUser);

        User managerUser = new User();
        managerUser.setUsername("manager");
        managerUser.setPassword("password");
        managerUser.setRole(managerRole);
        userService.createUser(managerUser);

        User regularUser = new User();
        regularUser.setUsername("user");
        regularUser.setPassword("password");
        regularUser.setRole(userRole);
        userService.createUser(regularUser);
    }
}
