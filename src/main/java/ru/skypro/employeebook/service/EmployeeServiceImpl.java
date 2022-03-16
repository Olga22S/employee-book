package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.exception.NotCorrectNameException;
import ru.skypro.employeebook.model.Employee;

import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

import static java.util.Objects.isNull;
import static org.apache.commons.lang3.StringUtils.isAlpha;
import static org.springframework.util.StringUtils.capitalize;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private final Map<String, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName, int dep, double salary) {
        String key = getConcatenatedNameAsKey(firstName, lastName);
        if (employees.containsKey(key)) {
            throw new EmployeeExistsException();
        }
        Employee employee = new Employee(capitalize(firstName), capitalize(lastName), dep, salary);
        employees.put(key, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        String key = getConcatenatedNameAsKey(firstName, lastName);
        if (employees.containsKey(key)) {
            return employees.remove(key);
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        Employee employee = employees.get(getConcatenatedNameAsKey(firstName, lastName));
        if (isNull(employee)) {
            throw new EmployeeNotFoundException();
        }
        return employee;
    }

    @Override
    public Collection<Employee> getEmployees() {
        return Collections.unmodifiableCollection(employees.values());
    }

    private String getConcatenatedNameAsKey(String firstName, String lastName) {
        if (!isAlpha(firstName) || !isAlpha(lastName)) {
            throw new NotCorrectNameException();
        }
        return (firstName + " " + lastName).toUpperCase();
    }
}
