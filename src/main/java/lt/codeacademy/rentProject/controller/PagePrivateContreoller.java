package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/private/properties")
public class PagePrivateContreoller {

    private final PropertyService propertyService;

    public PagePrivateContreoller(PropertyService propertyService) {
        this.propertyService = propertyService;
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

}
