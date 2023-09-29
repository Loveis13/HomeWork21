package skypro.employeeBook.service;

import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;
import skypro.employeeBook.dto.Employee;
import skypro.employeeBook.exception.EmployeeAlreadyAddedException;
import skypro.employeeBook.exception.EmployeeStorageIsFullException;
import skypro.employeeBook.exception.NotFoundException;

import java.util.*;

@Service
public class EmployeeServiceImpl implements EmployeeService {


    private final Map<String, Employee> employees;
    private static final int EMPLOYEE_SIZE = 5;

    public EmployeeServiceImpl() {

        this.employees = new HashMap<>();
    }

    @Override
    public Employee addEmployee(String firstname, String lastname, int department, double salary) {
        if (employees.size() == EMPLOYEE_SIZE) {
            throw new EmployeeStorageIsFullException();
        }
        String key = generateKey(firstname, lastname);
        if (employees.containsKey(key)) {
            throw new EmployeeAlreadyAddedException();
        }
        Employee employee = new Employee(StringUtils.capitalize(firstname),
                StringUtils.capitalize(lastname),
                department,
                salary );
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstname, String lastname) {
        String key = generateKey(firstname, lastname);
        Employee employee = employees.remove(key);
        if (Objects.isNull(employee)) {
            throw new NotFoundException();
        }
        return employee;
    }
    @Override
    public Employee getEmployee(String firstname, String lastname) {
        String key = generateKey(firstname, lastname);
        Employee employee = employees.get(key);
        if (Objects.isNull(employee)) {
            throw new NotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> findAll() {

        return employees.values();
    }

    private String generateKey(String firstname, String lastname) {

        return firstname + lastname;
    }
}
