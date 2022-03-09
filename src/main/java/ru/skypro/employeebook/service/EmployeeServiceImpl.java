package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import java.util.*;

import static java.util.Objects.isNull;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int dep, double salary) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            throw new EmployeeExistsException();
        }
        Employee employee = new Employee(firstName, lastName, dep, salary);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = firstName + " " + lastName;
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = employees.get(firstName + " " + lastName);
        if (isNull(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }
}
