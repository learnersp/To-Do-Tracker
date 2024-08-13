package com.ToDoTracker.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.CONFLICT, reason="Task with specified detail does not exist.")
public class TaskNotFoundException extends Exception{
}
