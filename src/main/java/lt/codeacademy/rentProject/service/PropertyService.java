package lt.codeacademy.rentProject.service;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.exeption.PropertyNotFoundException;
import lt.codeacademy.rentProject.repository.PropertyRepository;
import lt.codeacademy.rentProject.repository.UserRepository;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

@Service
public class PropertyService {

    private final PropertyRepository propertyRepository;
    private final UserRepository userRepository;


    public PropertyService(PropertyRepository propertyRepository, UserRepository userRepository) {
        this.propertyRepository = propertyRepository;
        this.userRepository = userRepository;
    }

    public Property create(Property property){
        User principal = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        property.setUser(principal);
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
