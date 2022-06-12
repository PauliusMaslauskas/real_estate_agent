package lt.codeacademy.rentProject.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "User was not found")
public class UserNotFoundException extends  RuntimeException{
}
