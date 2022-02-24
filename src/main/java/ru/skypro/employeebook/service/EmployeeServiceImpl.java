package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final List<Employee> employees = new ArrayList<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (getEmployeeIndex(firstName, lastName) != -1) {
            throw new EmployeeExistsException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.add(employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        int employeeIndex = getEmployeeIndex(firstName, lastName);
        if (employeeIndex != -1) {
            return employees.remove(employeeIndex);
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        int employeeIndex = getEmployeeIndex(firstName, lastName);
        if (employeeIndex != -1) {
            return employees.get(employeeIndex);
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    @Override
    public List<Employee> getEmployees() {
        return Collections.unmodifiableList(employees);
    }

    private int getEmployeeIndex(String firstName, String lastName) {
        for (Employee employee : employees) {
            if (employee.getFirstName().equals(firstName)
                    && employee.getLastName().equals(lastName)) {
                return employees.indexOf(employee);
            }
        }
        return -1;
    }
}
