package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Optional;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private int uid = 1;
    private final Map<Integer, Employee> employees = new HashMap<>();

    @Override
    public Employee addEmployee(String firstName, String lastName) {
        if (getEmployeeKey(firstName, lastName) != 0) {
            throw new EmployeeExistsException("This employee already exists");
        }
        Employee employee = new Employee(firstName, lastName);
        employees.put(uid++, employee);
        return employee;
    }

    @Override
    public Employee removeEmployee(String firstName, String lastName) {
        int key = getEmployeeKey(firstName, lastName);
        if (key != 0) {
            Employee employee = employees.get(key);
            employees.remove(key);
            return employee;
        }
        throw new EmployeeNotFoundException("This employee doesn't exist");
    }

    @Override
    public Employee getEmployee(String firstName, String lastName) {
        int key = getEmployeeKey(firstName, lastName);
        if (key != 0) {
            return employees.get(key);
        }
        throw new EmployeeNotFoundException("Employee is not found!");
    }

    @Override
    public Map<Integer, Employee> getEmployees() {
        return Collections.unmodifiableMap(employees);
    }

    private int getEmployeeKey(String firstName, String lastName) {
        Optional<Entry<Integer, Employee>> employee =
                employees.entrySet()
                        .stream()
                        .filter(s -> s.getValue().getFirstName().equals(firstName)
                                && s.getValue().getLastName().equals(lastName))
                        .findFirst();
        if (employee.isPresent()) {
            return employee.get().getKey();
        }
        return 0;
    }
}
