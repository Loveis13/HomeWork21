package skypro.employeeBook.dto;

import java.util.Objects;

public class Employee {
    private String firstname;
    private String lastname;
    private int department;
    private double salary;

    public Employee(String firstname, String lastname, int department, double salary) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.department = department;
        this.salary = salary;
    }

    public String getFirstname() {

        return firstname;
    }

    public String getLastname() {

        return lastname;
    }

    public int getDepartment() {

        return department;
    }

    public double getSalary() {

        return salary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Employee employee = (Employee) o;
        return department == employee.department && Double.compare(salary, employee.salary) == 0
                && Objects.equals(firstname, employee.firstname)
                && Objects.equals(lastname, employee.lastname);
    }

    @Override
    public int hashCode() {
        return Objects.hash(firstname, lastname, department, salary);
    }

    @Override
    public String toString() {
        return "Employee{" +
                "firstname='" + firstname + '\'' +
                ", lastname='" + lastname + '\'' +
                ", department=" + department +
                ", salary=" + salary +
                '}';
    }
}
