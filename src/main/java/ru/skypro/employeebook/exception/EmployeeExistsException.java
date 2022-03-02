package ru.skypro.employeebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class EmployeeExistsException extends RuntimeException {

    public EmployeeExistsException() {
        super("This employee already exists");
    }
}
