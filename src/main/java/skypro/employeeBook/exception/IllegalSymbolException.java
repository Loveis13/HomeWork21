package skypro.employeeBook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.BAD_REQUEST, reason = "Вы ввели неверные данные")
public class IllegalSymbolException extends IllegalArgumentException{
}
