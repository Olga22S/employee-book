package ru.skypro.employeebook.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.BAD_REQUEST)
public class NotCorrectNameException extends RuntimeException {
    public NotCorrectNameException() {
        super("Name must contain only characters!");
    }
}
