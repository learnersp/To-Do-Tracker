package com.ToDoTracker.NotificationService.service;

import com.ToDoTracker.NotificationService.domain.Task;
import com.ToDoTracker.NotificationService.domain.User;
import com.ToDoTracker.NotificationService.exception.ImpTaskAlreadyExistsException;
import com.ToDoTracker.NotificationService.exception.ImpTaskNotFoundException;

import java.util.List;

public interface NotificationService {


    List<Task> getAllTask(String emailId);
    User saveUser (User user) ;
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws ImpTaskNotFoundException;
    public List<Task> getAllImpTask(String emailId);
    public boolean addTask(String emailId, Task task) throws ImpTaskAlreadyExistsException;
    Task updateTask (String emailId, Task task);
    List<User> getAllUsers();

}
