package ru.skypro.employeebook.services;

import ru.skypro.employeebook.entities.Employee;
import ru.skypro.employeebook.exceptions.AlreadyExistsEmployeeException;
import ru.skypro.employeebook.exceptions.ArrayIsFullException;
import ru.skypro.employeebook.exceptions.EmployeeNotFoundException;

public interface EmployeeService {

    Employee addEmployee(String firstName, String lastName) throws ArrayIsFullException, AlreadyExistsEmployeeException;

    Employee removeEmployee(String firstName, String lastName) throws EmployeeNotFoundException;

    Employee getEmployee(String firstName, String lastName) throws EmployeeNotFoundException;
}
