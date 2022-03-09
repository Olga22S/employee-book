package ru.skypro.employeebook.service;

import ru.skypro.employeebook.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

public interface DepartmentService {

    Employee getDepartmentMinSalaryEmployee(int dep);

    Employee getDepartmentMaxSalaryEmployee(int dep);

    Collection<Employee> getDepartmentEmployees(int dep);

    Map<Integer, List<Employee>> getEmployeesByDepartments();
}
