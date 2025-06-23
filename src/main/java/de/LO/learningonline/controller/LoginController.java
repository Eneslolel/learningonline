package de.LO.learningonline.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";  // Thymeleaf rendert login.html
    }

    @GetMapping("/dashboard")
    public String dashboard() {
        // hier je nach Rolle auf verschiedene Views weiterleiten:
        return "dashboard";
    }
}
