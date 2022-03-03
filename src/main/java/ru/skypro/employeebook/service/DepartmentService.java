package ru.skypro.employeebook.service;

import ru.skypro.employeebook.model.Employee;

import java.util.Collection;

public interface DepartmentService {

    Employee getDepartmentMinSalaryEmployee(int dep);

    Employee getDepartmentMaxSalaryEmployee(int dep);

    Collection<Employee> getDepartmentEmployees(int dep);

    String getEmployeesSeparatedByDepartments();
}
