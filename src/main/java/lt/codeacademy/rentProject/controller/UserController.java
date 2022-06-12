package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.PropertyService;
import lt.codeacademy.rentProject.service.UserService;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;


@Controller
@RequestMapping("/private/user")
@SessionAttributes("user")
public class UserController {

    private final PropertyService propertyService;
    private final UserService userService;

    public UserController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping
    public String getUserPropertiesView(Model model) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", principal);
        return "userSettings";
    }

    @PostMapping("/update")
    public String getUpdateForm(Model model, User user, String username){
        user.setUsername(username);
        model.addAttribute("user", userService.create(user));
        return "redirect:/public/user";
    }
}
