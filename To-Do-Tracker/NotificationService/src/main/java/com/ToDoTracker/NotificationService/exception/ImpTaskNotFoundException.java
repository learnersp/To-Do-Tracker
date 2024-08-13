package com.ToDoTracker.NotificationService.exception;


import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code= HttpStatus.NOT_FOUND,reason = "Task with specific Id not Found")

public class ImpTaskNotFoundException extends Exception{

}
