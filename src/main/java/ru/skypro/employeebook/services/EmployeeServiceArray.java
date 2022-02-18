package ru.skypro.employeebook.services;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.entities.Employee;
import ru.skypro.employeebook.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.employeebook.exceptions.ArrayIsFullException;
import ru.skypro.employeebook.exceptions.EmployeeNotFoundException;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceArray implements EmployeeService {

    private Employee[] employees = new Employee[10];
    private int counter = 0;

    @Override
    public Employee addEmployee(String firstName, String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        if (counter == 10) {
            throw new ArrayIsFullException("Array is full!");
        }
        if (!isNull(findEmployee(firstName, lastName))) {
            throw new AlreadyExistsEmployeeException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName);
        employees[counter++] = employee;
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException("This employee doesn't exist");
        }
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                employees[i] = null;
                moveArray(i);
                counter--;
                break;
            }
        }
        return employee;
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) throws EmployeeNotFoundException {
        Employee employee = findEmployee(firstName, lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException("Employee is not found!");
        }
        return employee;
    }

    private Employee findEmployee(String firstName, String lastName) {
        for (int i = 0; i < counter; i++) {
            if (employees[i].getFirstName().equals(firstName)
                    && employees[i].getLastName().equals(lastName)) {
                return employees[i];
            }
        }
        return null;
    }

    private void moveArray(int i) {
        for (int j = i; j < counter - 1; j++) {
            employees[j] = employees[j + 1];
        }
        if (counter != 10) {
            employees[counter] = null;
        }
    }
}
