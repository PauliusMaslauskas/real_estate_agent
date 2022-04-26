package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.exeption.PropertyNotFoundException;
import lt.codeacademy.rentProject.repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@Controller
@RequestMapping(path = "/properties")
public class PropertyController {

    public PropertyRepository propertyRepository;

    public PropertyController(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    @GetMapping
    public String getPropertyList(
            @RequestParam(name = "page", defaultValue = "0") int pageNumber,
            Model model) {
        Pageable pageable = Pageable.ofSize(20).withPage(pageNumber);

        Page<Property> propertyPage = propertyRepository.findAll(pageable);

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
        Optional<Property> foundProperty = propertyRepository.findById(id);

        if (foundProperty.isEmpty()) {
            throw new PropertyNotFoundException();
        }
        Property property = foundProperty.get();

        model.addAttribute("showPrice", showPrice);
        model.addAttribute("property", property);
        return "propertyPage";
    }

    @GetMapping("/property")
    public String getPostedProperty(Model model){
        model.addAttribute("property", new Property());
        return "propertyForm";
    }

    @PostMapping("/postproperty")
    public String postProperty(Property property, Model model){
        Property listedProperty = propertyRepository.save(property);

        model.addAttribute("property", listedProperty);
        return "redirect:/properties/" + listedProperty.getId();
    }

}
