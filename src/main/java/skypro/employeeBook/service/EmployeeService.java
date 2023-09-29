package skypro.employeeBook.service;

import skypro.employeeBook.dto.Employee;

import java.util.Collection;

public interface EmployeeService {
    Employee addEmployee(String firstname, String lastname, int department, double salary);

    Employee removeEmployee(String firstname, String lastname);

    Employee getEmployee(String firstname, String lastname);

    Collection<Employee> findAll();
}
