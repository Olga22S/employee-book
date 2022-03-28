package ru.skypro.employeebook.service;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.boot.test.context.SpringBootTest;
import ru.skypro.employeebook.exception.EmployeeExistsException;
import ru.skypro.employeebook.exception.EmployeeNotFoundException;
import ru.skypro.employeebook.exception.NotCorrectNameException;
import ru.skypro.employeebook.model.Employee;

import java.util.ArrayList;
import java.util.Collection;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
class EmployeeServiceImplTest {

    private EmployeeServiceImpl service;

    @BeforeEach
    public void setUp() {
        service = new EmployeeServiceImpl();
        service.addEmployee("Olga", "Gontsova", 1, 500000);
        service.addEmployee("Kirill", "Kachalov", 2, 1000);
        service.addEmployee("Nata", "Kukushka", 2, 25000);
    }

    @Test
    public void whenAddNewEmployeeThenReturnMapWithNewEmployee() {
        assertEquals(service.getEmployees().size(), 3);
        service.addEmployee("Petr", "Petrov", 1, 5000);
        assertEquals(service.getEmployees().size(), 4);
    }

    @Test
    public void whenAddNewEmployeeThenCapitalizeEmployeeName() {
        Employee expected = new Employee("Aleksey", "Smirnov", 1, 5000);
        service.addEmployee("aleksey", "smirnov", 1, 5000);
        assertTrue(service.getEmployees().contains(expected));
    }

    @Test
    public void shouldThrowEmployeeExistsExceptionWhenAddExistingEmployee() {
        assertThrows(EmployeeExistsException.class,
                () -> service.addEmployee("Nata", "Kukushka", 2, 25000));
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenAddNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.addEmployee("123", "Ivanov", 1, 4500));
    }

    @Test
    public void whenRemoveEmployeeThenRemoveEmployeeByName() {
        assertEquals(service.getEmployees().size(), 3);
        service.removeEmployee("Kirill", "Kachalov");
        assertEquals(service.getEmployees().size(), 2);
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenRemoveNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.removeEmployee("Ivan", "@123"));
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenRemoveNotExistEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> service.removeEmployee("Oleg", "olegovich"));
    }

    @Test
    public void shouldThrowNotCorrectNameExceptionWhenGetNotCharacterEmployeeName() {
        assertThrows(NotCorrectNameException.class,
                () -> service.getEmployee("Ivan", "@123"));
    }

    @Test
    public void whenGetEmployeeThenReturnEmployee() {
        Employee result = service.getEmployee("Olga", "Gontsova");

        Employee expected = new Employee("Olga", "Gontsova", 1, 500000);

        assertEquals(result, expected);
    }

    @Test
    public void shouldThrowEmployeeNotFoundExceptionWhenGetNotExistEmployee() {
        assertThrows(EmployeeNotFoundException.class,
                () -> service.getEmployee("vladimir", "putin"));
    }

    @Test
    public void whenGetEmployeesThenReturnEmployees() {
        Collection<Employee> result = service.getEmployees();

        Collection<Employee> expected = getExpectedEmployees();

        assertTrue(result.containsAll(expected));
    }

    private Collection<Employee> getExpectedEmployees() {
        Collection<Employee> expected = new ArrayList<>();
        expected.add(new Employee("Nata", "Kukushka", 2, 25000));
        expected.add(new Employee("Olga", "Gontsova", 1, 500000));
        expected.add(new Employee("Kirill", "Kachalov", 2, 1000));
        return expected;
    }

}