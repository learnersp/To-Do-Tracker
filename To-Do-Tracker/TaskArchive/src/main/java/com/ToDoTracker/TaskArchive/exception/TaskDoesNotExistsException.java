package com.ToDoTracker.TaskArchive.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(code = HttpStatus.NOT_FOUND, reason = "Task Does Not Exists!!")
public class TaskDoesNotExistsException extends Exception {
}
