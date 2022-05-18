package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.service.PropertyService;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.SessionAttributes;
import javax.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/private/favourites")
@SessionAttributes("favourites")
public class FavouritesPrivateController {

    private final PropertyService propertyService;

    public FavouritesPrivateController(PropertyService propertyService) {
        this.propertyService = propertyService;
    }

    @GetMapping
    public String getFavouritesView(@ModelAttribute("favourites") List<Property> favourites, HttpSession httpSession) {
        return "propertyFavourites";
    }

    @PostMapping("/add/{id}")
    public String addToFavourites(@PathVariable("id") Integer id, @ModelAttribute("favourites") List<Property> favourites) {
        favourites.add(propertyService.findById(id));
        return "redirect:/properties/{id}";
    }

    @PostMapping("/delete/{id}")
    public String deleteFromFavourites(@PathVariable("id") Integer id, @ModelAttribute("favourites") List<Property> favourites) {
        favourites.stream()
                .filter(p-> p.getId().equals(id))
                .findFirst()
                .ifPresent(favourites::remove);
        return "redirect:/favourites";
    }

    @ModelAttribute("favourites")
    public List<Property> createFavourites() {
        return new ArrayList<>();
    }

}

