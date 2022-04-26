package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;

public interface PropertyRepository extends JpaRepository<Property, Integer> {

   // List<Property> findByTitle(String title);

}
