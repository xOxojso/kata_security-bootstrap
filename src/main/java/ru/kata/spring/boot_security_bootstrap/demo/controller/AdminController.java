package ru.kata.spring.boot_security_bootstrap.demo.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;
import ru.kata.spring.boot_security_bootstrap.demo.entity.User;
import ru.kata.spring.boot_security_bootstrap.demo.service.RoleService;
import ru.kata.spring.boot_security_bootstrap.demo.service.UserService;

import java.security.Principal;
import java.util.List;

@Controller
@RequestMapping("/admin")
public class AdminController {
    private final UserService userService;
    private final RoleService roleService;

    @Autowired
    public AdminController(UserService userService, RoleService roleService) {
        this.userService = userService;
        this.roleService = roleService;
    }

    @GetMapping()
    public String adminPage(Model model, Principal principal) {
        model.addAttribute("users", userService.getAll());
        model.addAttribute("user", userService.getUserByUsername(principal.getName()));
        model.addAttribute("roles", roleService.roles());
        return "admin/all";
    }

    @GetMapping("/add")
    public String registrationPage(@ModelAttribute("user") User user) {
        return "admin/newUser";
    }

    @PostMapping("/new")
    public String performRegistration(@ModelAttribute("user") User user) {
        userService.save(user);
        return "redirect:/admin";
    }

    @GetMapping("/{id}")
    public String showUser(@PathVariable("id") Long id, Model model) {
        User user = userService.getById(id);
        model.addAttribute("user", user);
        return "admin/all";
    }


    @PostMapping("/{id}/update")
    public String updateUser(@PathVariable("id") Long id, @ModelAttribute("user") User user, Model model) {
        model.addAttribute("roles", roleService.roles());
        userService.updateUser(id, user);
        return "redirect:/admin";
    }

    @PostMapping("/delete/{id}")
    public String deleteUser(@PathVariable("id") long id) {
        userService.delete(id);
        return "redirect:/admin";
    }
}
