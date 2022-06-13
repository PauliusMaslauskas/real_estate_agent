package lt.codeacademy.rentProject.service;

import lombok.RequiredArgsConstructor;
import lt.codeacademy.rentProject.entity.Role;
import lt.codeacademy.rentProject.entity.RolesAuthority;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.exeption.UserNotFoundException;
import lt.codeacademy.rentProject.repository.UserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;


@Service
@RequiredArgsConstructor
public class UserService implements UserDetailsService {

    private final UserRepository userRepository;

    public User create(User user) {
        setUserRole(user);
        return userRepository.save(user);
    }

    public User setUserRole(User user) {
        Role role = new Role();
        role.setRole(RolesAuthority.USER);
        role.setId(2);
        Set<Role> userRole = new HashSet<>();
        userRole.add(role);
        user.setRoles(userRole);

        return user;
    }

    public User findById(int id) {
        return userRepository.findById(id).orElseThrow();
    }

    public User findByUsername(String username) {
        return userRepository.findByUsername(username).orElseThrow(UserNotFoundException::new);
    }


    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        return userRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException((username + "was not found!")));

    }

}


