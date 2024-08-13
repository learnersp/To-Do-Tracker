package com.ToDoTracker.TaskArchive.service;

import com.ToDoTracker.TaskArchive.domain.Task;
import com.ToDoTracker.TaskArchive.domain.User;
import com.ToDoTracker.TaskArchive.exception.TaskDoesNotExistsException;

import java.util.List;

public interface IArchieveService {
    List<Task> getAllTasks(String emailId);
    boolean deleteTaskByTaskId(String emailId, String taskName) throws TaskDoesNotExistsException;
    User saveUser (User user);
    boolean addTask(String emailId, Task task);
    Task updateTask (String emailId, Task task);
    Task getTaskByTaskId (String emailId, String taskName) throws TaskDoesNotExistsException;
}
