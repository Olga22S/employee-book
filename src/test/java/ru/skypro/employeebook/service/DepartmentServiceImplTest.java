package ru.skypro.employeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import ru.skypro.employeebook.constants.Constants;
import ru.skypro.employeebook.model.Employee;

import java.util.Collection;
import java.util.List;
import java.util.Map;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceImplTest {

    @Mock
    private EmployeeService employeeService;
    @InjectMocks
    private DepartmentServiceImpl out;

    @BeforeEach
    public void initMock() {
        when(employeeService.getEmployees())
                .thenReturn(Constants.EMPLOYEES);
    }

    @Test
    public void whenGetDepartmentMinSalaryEmployeeThenReturnEmployeeWithMinSalary() {
        Employee expected = new Employee("Kirill", "Kachalov", 2, 1000);

        Employee result = out.getDepartmentMinSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentMaxSalaryEmployeeThenReturnEmployeeWithMaxSalary() {
        Employee expected = new Employee("Natasha", "Kukushkina", 2, 25000);

        Employee result = out.getDepartmentMaxSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentEmployeesThenReturnEmployeesOfDepartment() {
        Collection<Employee> expected = Constants.EMPLOYEES_OF_SECOND_DEP;

        Collection<Employee> result = out.getDepartmentEmployees(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetEmployeesThenReturnEmployeesByDepartments() {
        Map<Integer, List<Employee>> expected = Constants.EMPLOYEES_BY_DEPARTMENTS;

        Map<Integer, List<Employee>> result = out.getEmployeesByDepartments();

        assertEquals(expected, result);
    }
}