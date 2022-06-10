package lt.codeacademy.rentProject.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User create(User user){
        return userRepository.save(user);
  }


    public User findById(int id){
        return userRepository.findById(id).orElseThrow();
    }

    public User findUsername(String username){
        return userRepository.findByUsername(username).orElseThrow();
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(( username + "was not found!")));

    }

}


