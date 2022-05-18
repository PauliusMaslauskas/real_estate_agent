package lt.codeacademy.rentProject.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/properties")
public class PropertyController {


/*
    @GetMapping("/registration")
    public String getRegisteredUser(Model model){
        model.addAttribute("user", new User());
        return "registrationForm";
    }

    @PostMapping("/register")
    public String postProperty(User userEntity, Model model){
        User createdUser = userService.create(userEntity);

        model.addAttribute("user", createdUser);
        return "redirect:/properties";
    }

*/
}
