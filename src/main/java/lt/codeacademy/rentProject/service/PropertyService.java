package lt.codeacademy.rentProject.service;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.repository.PropertyRepository;

import java.util.List;

public class PropertyService {

    private PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }
    public List<Property> printProducts(Property property){
        return propertyRepository.findByTitle(property.getTitle());
    }
}
