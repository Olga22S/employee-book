package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.ArrayIsFullException;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final int ARRAY_SIZE = 10;
    private final Employee[] employees = new Employee[ARRAY_SIZE];
    private int counter = 0;

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (counter == ARRAY_SIZE) {
            throw new ArrayIsFullException("Array is full!");
        }
        try {
            getEmployee(firstName, lastName);
            throw new EmployeeExistsException("This employee already exists");
        } catch (EmployeeNotFoundException e) {
            Employee employee = new Employee(firstName, lastName);
            employees[counter++] = employee;
            return employee;
        }
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        Employee employee;
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                employee = employees[i];
                System.arraycopy(employees, i + 1, employees, i, counter - i);
                counter--;
                return employee;
            }
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (!isNull(employee) && employee.getFirstName().equals(firstName)
                    && employee.getLastName().equals(lastName)) {
                return employee;
            }
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }
}
