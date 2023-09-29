package skypro.employeeBook.util;

import org.apache.commons.lang3.StringUtils;
import skypro.employeeBook.exception.IllegalSymbolException;

import java.util.List;

public class EmployeeNameValidator {
    public static void validateIsAlpha(String... names) {
        for (String name : names) {
            if (!StringUtils.isAlpha(name)) {
                throw new IllegalSymbolException();
            }
        }
    }
}
