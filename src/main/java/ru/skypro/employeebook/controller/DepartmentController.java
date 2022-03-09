package ru.skypro.employeebook.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.skypro.employeebook.model.Employee;
import ru.skypro.employeebook.service.DepartmentService;

import java.util.Collection;

@RestController
@RequestMapping("/departments")
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("/min-salary")
    public Employee getDepartmentMinSalaryEmployee(@RequestParam int department) {
        return departmentService.getDepartmentMinSalaryEmployee(department);
    }

    @GetMapping("/max-salary")
    public Employee getDepartmentMaxSalaryEmployee(@RequestParam int department) {
        return departmentService.getDepartmentMaxSalaryEmployee(department);
    }

    @GetMapping(value = "/all", params = {"departmentId"})
    public Collection<Employee> getDepartmentEmployees(@RequestParam(value = "departmentId") int department) {
        return departmentService.getDepartmentEmployees(department);
    }

    @GetMapping("/all")
    public String getEmployee() {
        return departmentService.getStringEmployeesByDepartments();
    }
}
