package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import java.util.Collection;
import java.util.Comparator;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getDepartmentMinSalaryEmployee(int dep) {
        Collection<Employee> employees = getDepartmentEmployees(dep);
        return employees.stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getDepartmentMaxSalaryEmployee(int dep) {
        Collection<Employee> employees = getDepartmentEmployees(dep);
        return employees.stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getDepartmentEmployees(int dep) {
        return employeeService.getEmployees().stream()
                .filter(employee -> employee.getDepartment() == dep)
                .collect(Collectors.toUnmodifiableList());
    }

    @Override
    public String getStringEmployeesByDepartments() {
        StringBuilder result = new StringBuilder();
        for (Integer department : getDepartments()) {
            result.append("Department " + department + ": ");
            result.append(getDepartmentEmployees(department).stream()
                    .map(String::valueOf)
                    .collect(Collectors.joining()));
        }
        return result.toString();
    }

    private Collection<Integer> getDepartments() {
        return employeeService.getEmployees().stream()
                .map(Employee::getDepartment)
                .collect(Collectors.toSet());
    }
}
