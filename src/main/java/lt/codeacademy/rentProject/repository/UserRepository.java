package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer> {

}
