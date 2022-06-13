package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.transaction.annotation.Transactional;

import java.awt.print.Pageable;
import java.util.List;
import java.util.Optional;


public interface PropertyRepository extends JpaRepository<Property, Integer> {

    Optional<Property> findByAdress(String adress);



}
