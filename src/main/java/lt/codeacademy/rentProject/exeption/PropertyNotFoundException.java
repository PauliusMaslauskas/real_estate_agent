package lt.codeacademy.rentProject.exeption;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Property was not found")
public class PropertyNotFoundException extends RuntimeException {
}
