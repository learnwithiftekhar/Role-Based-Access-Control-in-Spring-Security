package com.learnwithiftekhar.rbacDemo.controller;

import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("api/manager")
public class ManagerController {

    @GetMapping
    public String managerAccess(Authentication authentication) {
        return "Hello! "+authentication.getName();
    }
}
