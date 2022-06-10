package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.Property;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;


public interface PropertyRepository extends JpaRepository<Property, Integer> {

}
