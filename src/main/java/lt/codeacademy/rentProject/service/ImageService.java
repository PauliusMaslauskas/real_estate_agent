package lt.codeacademy.rentProject.service;

import lt.codeacademy.rentProject.entity.Image;
import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.repository.ImageRepository;
import org.springframework.stereotype.Service;

@Service
public class ImageService {

    private final ImageRepository imageRepository;

    public ImageService(ImageRepository imageRepository) {
        this.imageRepository = imageRepository;
    }

    public Image create(Image image, Property property){
        image.setProperty(property);
        return imageRepository.save(image);
    }


}
