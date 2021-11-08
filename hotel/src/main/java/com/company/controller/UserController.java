package com.company.controller;

import com.company.api.service.ISecurityService;
import com.company.api.service.IUserService;
import com.company.model.User;
import com.company.validator.UserValidator;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/")
public class UserController {

    public final IUserService userService;

    private final ISecurityService securityService;

    private final UserValidator userValidator;

    public UserController(IUserService userService, ISecurityService securityService, UserValidator userValidator) {
        this.userService = userService;
        this.securityService = securityService;
        this.userValidator = userValidator;
    }

    @GetMapping("/registration")
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "registration";
    }

    @PostMapping("/registration")
    public String registration(@ModelAttribute("userForm") User userForm, BindingResult bindingResult, Model model) {
        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration";

        }

        userService.save(userForm);

        securityService.autoLogin(userForm.getUsername(), userForm.getPasswordConfirm());

        return "redirect:/welcome";

    }

    @GetMapping("/login")
    public String login(Model model, String error, String logout) {
        if (error != null) {
            model.addAttribute("error", "Username or password is incorrect");
        }
        if (logout != null) {
            model.addAttribute("message", "Logged out successfully");
        }
        return "login";
    }

    @GetMapping({"/welcome"})
    public String welcome(Model model) {
        return "welcome";
    }

    @GetMapping({"/"})
    public String main(Model model) {
        return "index";
    }

    @GetMapping("/admin")
    public String admin(Model model) {
        return "admin";
    }


}
