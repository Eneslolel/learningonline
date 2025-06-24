package de.LO.learningonline.controller;

import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class LoginController {
    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @GetMapping("/postLogin")
    public String redirectByRole(Authentication auth) {
        boolean isDozent = auth.getAuthorities().stream()
                .anyMatch(a -> a.getAuthority().equals("ROLE_DOZENT"));
        return isDozent
                ? "redirect:/dozent/dashboard"
                : "redirect:/student/dashboard";
    }
}

