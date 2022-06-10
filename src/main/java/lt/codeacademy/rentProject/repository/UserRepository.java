package lt.codeacademy.rentProject.repository;

import lt.codeacademy.rentProject.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;



import java.util.Optional;

public interface UserRepository extends JpaRepository<User, Integer> {

   Optional<User> findByUsername(String username);


}
