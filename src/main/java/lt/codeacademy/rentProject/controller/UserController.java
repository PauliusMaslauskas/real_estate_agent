package lt.codeacademy.rentProject.controller;

import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.service.ImageService;
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
    private final ImageService imageService;

    public UserController(PropertyService propertyService, UserService userService, ImageService imageService) {
        this.propertyService = propertyService;
        this.userService = userService;
        this.imageService = imageService;
    }

    @GetMapping
    public String getUserPropertiesView(Model model) {

        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        model.addAttribute("user", principal);
        return "userSettings";
    }

    @PostMapping("/update")
    public String getUpdateForm(Model model, User user) {

/*
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

            imageService.createUserImage(image, user);

            String uploadDir = "public/images/userImages";

            FileUploadUtil.saveFile(uploadDir, randomFileName + fileExtension, multipartFile);
*/

        model.addAttribute("user", userService.create(user));
        return "redirect:/private/user";
    }
}
