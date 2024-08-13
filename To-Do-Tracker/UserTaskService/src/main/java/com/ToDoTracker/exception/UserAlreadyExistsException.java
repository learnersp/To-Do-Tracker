package com.ToDoTracker.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="Registration failed! User Already Exists")
public class UserAlreadyExistsException extends Exception{
}
