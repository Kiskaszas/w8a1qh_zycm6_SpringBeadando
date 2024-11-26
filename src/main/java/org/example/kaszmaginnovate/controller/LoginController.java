package org.example.kaszmaginnovate.controller;

import io.swagger.v3.oas.annotations.parameters.RequestBody;
import org.example.kaszmaginnovate.model.User;
import org.example.kaszmaginnovate.repository.UserRepository;
import org.example.kaszmaginnovate.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class LoginController {

    private final UserService userService;
    private final PasswordEncoder passwordEncoder = new BCryptPasswordEncoder();

    @Value("${admin_registration_secret_code}")
    private String adminRegistrationSecretCode;

    public LoginController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping("/login")
    public String login() {
        return "login";
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password, Model model) {
        boolean isAuthenticated = userService.authenticate(username, password);
        if (isAuthenticated) {
            return "redirect:/"; // Sikeres bejelentkezés esetén a főoldalra navigálás
        } else {
            model.addAttribute("errorMessage", "Hibás felhasználónév vagy jelszó");
            return "login"; // Visszairányítás a login oldalra hibaüzenettel
        }
    }

    @GetMapping("/register")
    public String register() {
        return "register";
    }

    @PostMapping("/register")
    public String registerUser(@ModelAttribute User user, Model model) {
        if ("ADMIN".equals(user.getRole()) && !adminRegistrationSecretCode.equals(user.getSecurityCode())) {
            model.addAttribute("errorMessage", "Hibás biztonsági kód");
            return "register"; // Hibaüzenettel vissza a regisztrációs oldalra
        }
        userService.save(user);
        return "redirect:/login"; // Sikeres regisztráció után irány a bejelentkezési oldalra
    }
}
