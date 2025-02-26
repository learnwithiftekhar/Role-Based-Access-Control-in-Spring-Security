package com.learnwithiftekhar.rbacDemo.service;

import com.learnwithiftekhar.rbacDemo.model.Role;
import com.learnwithiftekhar.rbacDemo.model.User;
import com.learnwithiftekhar.rbacDemo.repository.RoleRepository;
import com.learnwithiftekhar.rbacDemo.repository.UserRepository;
import jakarta.transaction.Transactional;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final PasswordEncoder passwordEncoder;

    public UserService(UserRepository userRepository, RoleRepository roleRepository, PasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Transactional
    public User createUser(User user) {
        // check if the username already exist
        if(userRepository.findByUsername(user.getUsername()).isPresent()) {
            throw new UsernameNotFoundException("Username already exists");
        }

        // if not exist then we will store the user.
        // we need to encode the password before saving.
        user.setPassword(passwordEncoder.encode(user.getPassword()));

        // assign the role
        // if no role found, then set the default role "USER"
        Role role = roleRepository.findByName(user.getRole().getName())
                .orElse(new Role("USER"));

        user.setRole(role);

        return userRepository.save(user);
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username)
                .orElseThrow(() -> new UsernameNotFoundException("Username not found"));
    }
}
