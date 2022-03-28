package ru.skypro.employeebook.constants;

import ru.skypro.employeebook.model.Employee;

import java.util.List;
import java.util.Map;

public class Constants {

    public static final List<Employee> EMPLOYEES = List.of(
            new Employee("Olga", "Gontsova", 1, 500000),
            new Employee("Natasha", "Kukushkina", 2, 25000),
            new Employee("Kirill", "Kachalov", 2, 1000)
    );
    public static final List<Employee> EMPLOYEES_OF_SECOND_DEP = List.of(
            new Employee("Natasha", "Kukushkina", 2, 25000),
            new Employee("Kirill", "Kachalov", 2, 1000)
    );

    public static final Map<Integer, List<Employee>> EMPLOYEES_BY_DEPARTMENTS = Map.of(
            1, List.of(new Employee("Olga", "Gontsova", 1, 500000)),
            2, EMPLOYEES_OF_SECOND_DEP
    );
}
