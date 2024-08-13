package com.ToDoTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="User with specified detail does not exist.")
public class UserNotFoundException extends Exception{
}
