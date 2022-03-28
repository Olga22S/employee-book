package ru.skypro.employeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.employeebook.model.Employee;

import java.util.*;

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
                .thenReturn(getExpectedListAllEmployees());
    }

    @Test
    public void whenGetDepartmentMinSalaryEmployeeThenReturnEmployeeWithMinSalary() {
        Employee expected = new Employee("Kirill", "Kachalov", 2, 1000);

        Employee result = out.getDepartmentMinSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentMaxSalaryEmployeeThenReturnEmployeeWithMaxSalary() {
        Employee expected = new Employee("Nata", "Kukushka", 2, 25000);

        Employee result = out.getDepartmentMaxSalaryEmployee(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetDepartmentEmployeesThenReturnEmployeesOfDepartment() {
        Collection<Employee> expected = getExpectedListEmployees();

        Collection<Employee> result = out.getDepartmentEmployees(2);

        assertEquals(expected, result);
    }

    @Test
    public void whenGetEmployeesThenReturnEmployeesByDepartments() {
        Map<Integer, List<Employee>> expected = getExpectedEmployeesByDepartments();

        Map<Integer, List<Employee>> result = out.getEmployeesByDepartments();

        assertEquals(expected, result);
    }

    private Collection<Employee> getExpectedListAllEmployees() {
        Collection<Employee> expected = new ArrayList<>();
        expected.add(new Employee("Olga", "Gontsova", 1, 500000));
        expected.add(new Employee("Kirill", "Kachalov", 2, 1000));
        expected.add(new Employee("Nata", "Kukushka", 2, 25000));
        return expected;
    }

    private Collection<Employee> getExpectedListEmployees() {
        List<Employee> expected = new ArrayList<>();
        expected.add(new Employee("Kirill", "Kachalov", 2, 1000));
        expected.add(new Employee("Nata", "Kukushka", 2, 25000));
        return expected;
    }

    private Map<Integer, List<Employee>> getExpectedEmployeesByDepartments() {
        Map<Integer, List<Employee>> expected = new HashMap<>();
        List<Employee> employees1 = new ArrayList<>();
        List<Employee> employees2 = new ArrayList<>();
        employees1.add(new Employee("Olga", "Gontsova", 1, 500000));
        expected.put(1, employees1);
        employees2.add(new Employee("Kirill", "Kachalov", 2, 1000));
        employees2.add(new Employee("Nata", "Kukushka", 2, 25000));
        expected.put(2, employees2);
        return expected;
    }
}