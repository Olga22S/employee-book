package ru.skypro.employeebook.service;

import org.springframework.stereotype.Service;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.model.Employee;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Employee getDepartmentMinSalaryEmployee(int dep) {
        return getEmployeesByDepartment().get(dep)
                .stream()
                .min(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Employee getDepartmentMaxSalaryEmployee(int dep) {
        return getEmployeesByDepartment().get(dep)
                .stream()
                .max(Comparator.comparingDouble(Employee::getSalary))
                .orElseThrow(EmployeeNotFoundException::new);
    }

    @Override
    public Collection<Employee> getDepartmentEmployees(int dep) {
        return Collections.unmodifiableCollection(getEmployeesByDepartment().get(dep));
    }

    @Override
    public String getEmployeesSeparatedByDepartments() {
        StringBuilder result = new StringBuilder();
        for (Map.Entry<Integer, List<Employee>> item : getEmployeesByDepartment().entrySet()) {
            result.append("Department " + item.getKey() + ": ");
            for (Employee employee : item.getValue()) {
                result.append(employee.toString());
            }
        }
        return result.toString();
    }

    private Map<Integer, List<Employee>> getEmployeesByDepartment() {
        return employeeService.getEmployees()
                .stream()
                .collect(Collectors.groupingBy(Employee::getDepartment));
    }
}
