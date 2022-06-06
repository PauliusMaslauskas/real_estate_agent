package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.service.PropertyService;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/public/properties")
public class PagePublicController {

    private final PropertyService propertyService;


    public PagePublicController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String getPropertyList(@RequestParam(name = "page", defaultValue = "0") int pageNumber, Model model) {

        Page<Property> propertyPage = propertyService.findAllPagable(15, pageNumber);

        List<Property> properties = propertyPage.getContent();

        for (Property property : properties){
            if (property.getImages().isEmpty()){
                property.setFirstImagePath("");
            }else {
                property.setFirstImagePath(property.getImages().stream().sorted((i1, i2)-> i1.getId() - i2.getId()).findFirst().get().getPhotosImagePath());
            }
        }

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

        property.getImages().stream().findFirst().get().setFirstImage(true);


        model.addAttribute("showPrice", showPrice);
        model.addAttribute("property", property);

        return "propertyPage";
    }


}
