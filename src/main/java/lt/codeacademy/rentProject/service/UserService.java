package lt.codeacademy.rentProject.service;


import lt.codeacademy.rentProject.entity.UserEntity;
import lt.codeacademy.rentProject.repository.UserRepository;

import org.springframework.stereotype.Service;

@Service
public class UserService {

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

  public UserEntity create(UserEntity userEntity){
        return userRepository.save(userEntity);
  }

}

