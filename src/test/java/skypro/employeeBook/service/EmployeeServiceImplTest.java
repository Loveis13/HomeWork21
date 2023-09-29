package skypro.employeeBook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import skypro.employeeBook.dto.Employee;
import skypro.employeeBook.exception.EmployeeAlreadyAddedException;
import skypro.employeeBook.exception.EmployeeStorageIsFullException;
import skypro.employeeBook.exception.NotFoundException;

import java.util.Collection;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class EmployeeServiceImplTest {
    private EmployeeServiceImpl underTest;


    @BeforeEach
            void beforeEach(){

        underTest = new EmployeeServiceImpl();

    }

    private Employee expectedEmployee = new Employee("Ivan", "ivanov", 13, 50_000);

    @Test
    void addEmployee_shouldAddEmployeeToMapAndReturnEmployee() {

        Employee result = underTest.addEmployee(expectedEmployee.getFirstname(), expectedEmployee.getFirstname(),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        assertTrue(underTest.findAll().contains(expectedEmployee));
        assertEquals(expectedEmployee, result);
    }

    @Test
    void addEmployee_shouldThrowExceptionWhenNotEnoughMapSize() {
        for (int i = 0; i < 5; i++) {
            underTest.addEmployee((expectedEmployee.getFirstname() + i),
                    (expectedEmployee.getLastname() + i), expectedEmployee.getDepartment(),
                    expectedEmployee.getSalary());
        }
        assertThrows(EmployeeStorageIsFullException.class, () -> underTest.addEmployee(expectedEmployee.getFirstname()
                , expectedEmployee.getLastname(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));

    }

    @Test
    void add_Employee_shouldThrowExceptionWhenEqualEmployeeInMap() {
        underTest.addEmployee(expectedEmployee.getFirstname()
                , expectedEmployee.getLastname(), expectedEmployee.getDepartment(), expectedEmployee.getSalary());

        assertThrows(EmployeeAlreadyAddedException.class, () -> underTest.addEmployee(expectedEmployee.getFirstname()
                , expectedEmployee.getLastname(), expectedEmployee.getDepartment(), expectedEmployee.getSalary()));
    }


    @Test
    void removeEmployee_shouldAddEmployeeAndRemoveExist() {
        Employee result = underTest.addEmployee((expectedEmployee.getFirstname()),
                (expectedEmployee.getLastname()),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        underTest.removeEmployee(expectedEmployee.getFirstname(), expectedEmployee.getLastname());
        assertNotNull(result);
        assertEquals(expectedEmployee, result);
    }
    @Test
    void removeEmployee_shouldThrowExceptionWhenEmployeeIsNull() {
        assertThrows(NotFoundException.class, () -> underTest.removeEmployee(expectedEmployee.getFirstname(),
                expectedEmployee.getLastname()));
    }


    @Test
    void getEmployee_WhenEmployeeExist() {
        underTest.addEmployee((expectedEmployee.getFirstname()),
                (expectedEmployee.getLastname()),
                expectedEmployee.getDepartment(), expectedEmployee.getSalary());
        Employee result = underTest.getEmployee(expectedEmployee.getFirstname(), expectedEmployee.getLastname());
        underTest.getEmployee(expectedEmployee.getFirstname(), expectedEmployee.getLastname());
        assertNotNull(result);
        assertEquals(expectedEmployee, result);
    }
    @Test
    void getEmployee_WhenEmployeeDoesNotExist() {
        assertThrows(NotFoundException.class, () -> underTest.getEmployee(expectedEmployee.getFirstname(),
                expectedEmployee.getLastname()));
    }


    @Test
    void findAll_shouldReturnEmployeesListWhenEmployeeInMap() {
        Employee employee = new Employee("sara","konor",13,50_000);
        underTest.addEmployee(expectedEmployee.getFirstname(),expectedEmployee.getLastname(),
                expectedEmployee.getDepartment(),expectedEmployee.getSalary());
        underTest.addEmployee(employee.getFirstname(),employee.getLastname(),
                employee.getDepartment(),employee.getSalary());
        Collection<Employee> result = underTest.findAll();
            assertTrue(result.containsAll(List.of(expectedEmployee,employee)));
    }
    @Test
    void  findAll_shouldReturnEmptyListWhenEmployeeNotInMap(){
        Collection<Employee> all = underTest.findAll();
        assertTrue(all.isEmpty());
    }
}