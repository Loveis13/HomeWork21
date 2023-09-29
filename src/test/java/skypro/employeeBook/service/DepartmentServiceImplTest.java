package skypro.employeeBook.service;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import skypro.employeeBook.dto.Employee;
import skypro.employeeBook.exception.NotFoundException;

import java.util.*;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {
    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl departmentService;
    private List<Employee> employees = List.of(
            new Employee("ivan", "ivanov", 13, 50_000),
            new Employee("sara", "konor", 13, 80_000),
            new Employee("lera", "larovna", 3, 110_000));

    @Test
    void maxSalaryEmployee_shouldReturnEmployeeWithMaxSalaryWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);

        Employee result = departmentService.maxSalaryEmoloyee(employees.get(0).getDepartment());

        assertEquals(employees.get(1), result);
    }

    @Test
    void maxSalaryEmployee_shouldThrowExceptionNotEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class,
                () -> departmentService.maxSalaryEmoloyee(1));
    }

    @Test
    void minSalaryEmployee_shouldReturnEmployeeWithMinSalaryWhenEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Employee result = departmentService.minSalaryEmployee(employees.get(1).getDepartment());
        assertEquals(employees.get(0), result);
    }
    @Test
    void minSalaryEmployee_shouldThrowExceptionNotEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(Collections.emptyList());
        assertThrows(NotFoundException.class,
                () -> departmentService.maxSalaryEmoloyee(1));
    }

    @Test
    void getEmployeesInDepartment_shouldReturnEmployeesWhenEmployeesInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Collection<Employee> result = departmentService.getEmployeesInDepartment(employees.get(0).getDepartment());
        assertEquals(List.of(employees.get(0), employees.get(1)), result);
    }
    @Test
    void getEmployeeInDepartment_shouldReturnEmptyListIfNotEmployeesInDepartment() {
        int department = 1;
        List<Employee> emptyList = new ArrayList<>();
        when(employeeService.findAll()).thenReturn(emptyList);
        Collection<Employee> result = departmentService.getEmployeesInDepartment(department);
        assertEquals(0,result.size());
    }

    @Test
    void getAll_shouldReturnMapWithEmployeeWhenEmployeeInDepartment() {
        when(employeeService.findAll()).thenReturn(employees);
        Map<Integer, List<Employee>> expectedMap = Map.of(
                employees.get(1).getDepartment(), List.of(employees.get(0), employees.get(1)),
                employees.get(2).getDepartment(), List.of(employees.get(2)));
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(expectedMap, result);
    }

    @Test
    void getAll_shouldReturnEmptyMapIfNotEmployeesInDepartment() {
        List<Employee> emptyList = new ArrayList<>();
        when(employeeService.findAll()).thenReturn(emptyList);
        Map<Integer, List<Employee>> result = departmentService.getAll();
        assertEquals(0, result.size());
    }
}