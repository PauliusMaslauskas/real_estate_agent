package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.PropertyService;
import lt.codeacademy.rentProject.service.UserService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;


@Controller
@RequestMapping("/properties")
public class PropertyController {

    private final PropertyService propertyService;
    private final UserService userService;


    public PropertyController(PropertyService propertyService, UserService userService) {
        this.propertyService = propertyService;
        this.userService = userService;
    }

    @GetMapping
    public String getPropertyList(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            Model model) {

        Page<Property> propertyPage = propertyService.findAllPagable(25, pageNumber);

        List<Property> properties = propertyPage.getContent();

        model.addAttribute("properties", properties);
        model.addAttribute("currentPage", pageNumber);
        model.addAttribute("totalPages", propertyPage.getTotalPages());

        return "propertyList";
    }


    @GetMapping(path = "/{id}")
    public String getPropertyPage(
            @PathVariable(name = "id") int id,
            @RequestParam(name = "showPrice", required = false) boolean showPrice,
            Model model
    ) {
        Property property = propertyService.findById(id);

        model.addAttribute("showPrice", showPrice);
        model.addAttribute("property", property);
        return "propertyPage";
    }

    @GetMapping("/property")
    public String getPostedProperty( Model model){
        model.addAttribute("property", new Property());
        return "propertyForm";
    }

    @PostMapping("/postproperty")
    public String postProperty(@Valid Property property, BindingResult errors, Model model){
        if (errors.hasErrors()){
            return "propertyForm";
        }

        Property listedProperty = propertyService.create(property);
        model.addAttribute("property", listedProperty);
        return "redirect:/properties/" + listedProperty.getId();
    }

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


}
