package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.PropertyService;
import lt.codeacademy.rentProject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@Controller
@RequestMapping("/private/myproperties")
@SessionAttributes("myproperties")
public class MyPrivateProperties {

    private final PropertyService propertyService;
    private final UserService userService;


    public MyPrivateProperties(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }


    @GetMapping
    public String myProperties(
            @RequestParam(name = "page", defaultValue = "0")
                    int pageNumber, Model model) {

        Page<Property> propertyPage = propertyService.findAllPagable(10, pageNumber);

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        User user = userService.findById(principal.getId());
        List<Property> properties = user.getProperties().stream().toList();


        model.addAttribute("properties", properties);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", propertyPage.getTotalPages());

        return "propertyMyList";
    }

    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('USER')")
    public String deleteFromPropertyList(@PathVariable("id") Integer id) {
        propertyService.deleteById(id);
        return "redirect:/private/myproperties";
    }
}
