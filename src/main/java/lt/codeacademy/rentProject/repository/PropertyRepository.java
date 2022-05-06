package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;


public interface PropertyRepository extends JpaRepository<Property, Integer> {



}
