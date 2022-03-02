package ru.skypro.employeebook.service;

import ru.skypro.employeebook.model.Employee;

import java.util.Collection;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee getEmployee(String firstName, String lastName);

    Collection<Employee> getEmployees();
}
