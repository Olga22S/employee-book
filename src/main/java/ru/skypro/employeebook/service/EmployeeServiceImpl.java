package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.ArrayIsFullException;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

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
        if (getEmployeeIndex(firstName, lastName) != -1) {
            throw new EmployeeExistsException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName);
        employees[counter++] = employee;
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                Employee employee = employees[i];
                System.arraycopy(employees, i + 1, employees, i, counter - i);
                counter--;
                return employee;
            }
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        int employeeIndex = getEmployeeIndex(firstName, lastName);
        if (employeeIndex != -1) {
            return employees[employeeIndex];
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    private int getEmployeeIndex(String firstName, String lastName) {
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                return i;
            }
        }
        return -1;
    }
}
