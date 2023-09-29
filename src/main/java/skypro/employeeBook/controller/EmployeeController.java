package skypro.employeeBook.controller;

import org.springframework.web.bind.annotation.*;
import skypro.employeeBook.dto.Employee;
import skypro.employeeBook.service.EmployeeService;
import skypro.employeeBook.util.EmployeeNameValidator;

import java.util.Collection;

@RestController
@RequestMapping("/employee")
public class EmployeeController {
    private final EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping("/add")

    public Employee addEmployee(@RequestParam String firstname, @RequestParam String lastname,
                                int department, double salary) {
        EmployeeNameValidator.validateIsAlpha(firstname, lastname);
        return employeeService.addEmployee(firstname, lastname, department, salary);
    }

    @GetMapping("/remove")
    public Employee removeEmployee(@RequestParam String firstname, @RequestParam String lastname) {
        EmployeeNameValidator.validateIsAlpha(firstname, lastname);
        return employeeService.removeEmployee(firstname, lastname);
    }

    @GetMapping("/find")
    public Employee findEmployee(@RequestParam String firstname, @RequestParam String lastname) {
        EmployeeNameValidator.validateIsAlpha(firstname, lastname);
        return employeeService.getEmployee(firstname, lastname);
    }

    @GetMapping()
    public Collection<Employee> getAll() {

        return employeeService.findAll();
    }
}
