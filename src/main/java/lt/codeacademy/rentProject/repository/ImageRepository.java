package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.Image;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageRepository extends JpaRepository<Image, Integer> {
}
