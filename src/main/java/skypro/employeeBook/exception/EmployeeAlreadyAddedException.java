package skypro.employeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Уже есть такой сотрудник в фирме")
public class EmployeeAlreadyAddedException extends RuntimeException {
}
