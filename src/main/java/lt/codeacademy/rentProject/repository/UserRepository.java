package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.UserEntity;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<UserEntity, Integer> {

}
