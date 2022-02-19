package ru.skypro.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.employeebook.model.Employee;
import ru.skypro.employeebook.exception.AlreadyExistsEmployeeException;
import ru.skypro.employeebook.exception.ArrayIsFullException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.service.EmployeeService;

@RestController
@RequestMapping("/employee")
public class EmployeeController {

    private EmployeeService employeeService;

    public EmployeeController(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @GetMapping(value = "/add")
    public Employee addEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws ArrayIsFullException, AlreadyExistsEmployeeException {
        return employeeService.addEmployee(firstName, lastName);
    }

    @GetMapping(value = "/remove")
    public Employee removeEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        return employeeService.removeEmployee(firstName, lastName);
    }

    @GetMapping(value = "/find")
    public Employee findEmployee(@RequestParam String firstName, @RequestParam String lastName)
            throws EmployeeNotFoundException {
        return employeeService.getEmployee(firstName, lastName);
    }
}
