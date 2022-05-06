package lt.codeacademy.rentProject.service;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.exeption.PropertyNotFoundException;
import lt.codeacademy.rentProject.repository.PropertyRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;

    public PropertyService(PropertyRepository propertyRepository) {
        this.propertyRepository = propertyRepository;
    }

    public Property create(Property property){
        return propertyRepository.save(property);
    }

    public Property findById(int id){
        return propertyRepository
                .findById(id)
                .orElseThrow(PropertyNotFoundException::new);
    }

    public Page<Property> findAllPagable(int pageSize, int pageNumber){
        Pageable pageable = Pageable
                .ofSize(pageSize)
                .withPage(pageNumber);

        return propertyRepository.findAll(pageable);
    }

}
