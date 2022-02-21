package ru.skypro.employeebook.service;

import ru.skypro.employeebook.model.Employee;
import ru.skypro.employeebook.exception.AlreadyExistsEmployeeException;
import ru.skypro.employeebook.exception.ArrayIsFullException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName);

    Employee removeEmployee(String firstName, String lastName);

    Employee getEmployee(String firstName, String lastName);
}
