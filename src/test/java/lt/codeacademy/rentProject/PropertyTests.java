package lt.codeacademy.rentProject;


import lt.codeacademy.rentProject.entity.Property;
import lt.codeacademy.rentProject.entity.User;
import lt.codeacademy.rentProject.repository.PropertyRepository;
import lt.codeacademy.rentProject.repository.UserRepository;
import lt.codeacademy.rentProject.service.PropertyService;
import lt.codeacademy.rentProject.service.UserService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.test.web.servlet.MockMvc;

import javax.persistence.EntityManager;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;


@SpringBootTest
@AutoConfigureMockMvc
public class PropertyTests {


    @Autowired
    private MockMvc mvc;

    @Autowired
    private EntityManager entityManager;

    @Autowired
    PropertyService propertyService;

    @Autowired
    UserService userService;

    @MockBean
    UserRepository userRepository;

    @MockBean
    PropertyRepository propertyRepository;



    @Test
    void userCreationCheck() {
        User user = testUser();
        userService.setUserRole(user);
        userService.create(user);

        int id = 9999;

        assertThat(testUser().getId()).isEqualTo(id);


    }


private User testUser(){
        User testUser = new User();
        testUser.setId(9999);
        testUser.setFirstname("Jonas");
        testUser.setLastname("Jonaitis");
        testUser.setUsername("jonas123");
        testUser.setEmail("test@test.com");
        testUser.setPhonenumber("+3709999999");
        testUser.setPassword("$2a$12$DzoVZ.JrXoGo1REcUXs0SerKsIL9gbwCFBojfulEDJRjXPZ9G2VZ2");
            return testUser;
    }
}
