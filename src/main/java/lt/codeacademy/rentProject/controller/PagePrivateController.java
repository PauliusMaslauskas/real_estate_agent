package lt.codeacademy.rentProject.controller;

import lombok.AllArgsConstructor;
import lt.codeacademy.rentProject.entity.Image;
import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.ImageService;
import lt.codeacademy.rentProject.service.PropertyService;
import lt.codeacademy.rentProject.service.UserService;
import lt.codeacademy.rentProject.utility.FileUploadUtil;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.util.StringUtils;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.annotation.MultipartConfig;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.io.IOException;
import java.util.List;
import java.util.Random;

@Controller
@AllArgsConstructor
@MultipartConfig
@RequestMapping("/private/properties")
public class PagePrivateController {

    private final PropertyService propertyService;
    private final UserService userService;
    private final ImageService imageService;

    @GetMapping("/property")
    public String getPostedProperty(Model model) {
        model.addAttribute("property", new Property());
        return "propertyForm";
    }


    @PostMapping("/postproperty")
    public String postProperty(@Valid Property property , BindingResult errors, Model model, @RequestParam("image") MultipartFile[] multipartFiles) throws IOException {
        if (errors.hasErrors()) {
            return "propertyForm";
        }

        Property listedProperty = propertyService.create(property);

        for (MultipartFile multipartFile : multipartFiles) {

            Image image = new Image();

            String fileName = StringUtils.cleanPath(multipartFile.getOriginalFilename());

            if (fileName.equals("")) {
                continue;
            }

            String fileExtension = fileName.substring(fileName.lastIndexOf("."));
            Random rand = new Random();
            int int_random = rand.nextInt(999999999);
            String randomFileName = String.valueOf(int_random);

            image.setPhotos(randomFileName + fileExtension);

            imageService.create(image, property);

            String uploadDir = "public/images/propertyImages";

            FileUploadUtil.saveFile(uploadDir, randomFileName + fileExtension, multipartFile);
        }

        model.addAttribute("property", listedProperty);
        return "redirect:/public/properties/" + listedProperty.getId();
    }


    //Du kartus paspausti delete.
    //Nepavyko trinti per My properties, istrindavo tik user_id.
    @PostMapping("/delete/{id}")
    @PreAuthorize("hasRole('ADMIN')")
    public String deleteFromPropertyList(@PathVariable("id") Integer id) {
      Property property =  propertyService.findById(id);
      property.setUser(null);
      propertyService.delete(property);
        return "redirect:/public/properties";
    }


}
