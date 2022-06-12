package lt.codeacademy.rentProject.controller;

import lombok.AllArgsConstructor;
import lt.codeacademy.rentProject.configuration.SecurityConfig;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.UserService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import javax.validation.Valid;

@Controller
@AllArgsConstructor
@RequestMapping(path = "/public/registration")
public class RegistrationController {

    private final SecurityConfig securityConfig;
    private final UserService userService;

    @GetMapping("/register")
    public String getRegistrationForm( Model model){
        model.addAttribute("user", new User());
        return "registrationForm";
    }

    @PostMapping("/registerUser")
    public String registerUser(@Valid User user, BindingResult errors, Model model){
        if (errors.hasErrors()){
            return "registrationForm";
        }
        user.setPassword(securityConfig.passwordEncoder().encode(user.getPassword()));
        User registeredUser = userService.create(user);
        model.addAttribute("user", registeredUser);
        return "redirect:/login";
    }

}


