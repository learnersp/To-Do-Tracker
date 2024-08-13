package com.ToDoTracker.service;

import com.ToDoTracker.domain.Task;
import com.ToDoTracker.domain.User;
import com.ToDoTracker.exception.TaskAlreadyExistsException;
import com.ToDoTracker.exception.TaskNotFoundException;
import com.ToDoTracker.exception.UserAlreadyExistsException;
import com.ToDoTracker.exception.UserNotFoundException;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.Optional;

public interface UserTaskService {

   User saveUser (User user, MultipartFile file) throws UserAlreadyExistsException,IOException;
    //    User saveUser (User user) throws UserAlreadyExistsException;

    User saveUserWithNoImage (User user) throws UserAlreadyExistsException;
    Task addTask (String emailId, Task task) throws TaskAlreadyExistsException;
    Task updateTask (String emailId, Task task);
    List<User> getAllUsers ();
    List<Task> getAllTasksOfUser (String emailId);
    Optional<User> getUserById (String emailId) throws UserNotFoundException;
    Task getTaskByTaskId (String emailId, String taskName) throws TaskNotFoundException;
    boolean deleteAllUser ();
    boolean deleteUserById (String emailId) throws UserNotFoundException;
    boolean deleteTaskByTaskId (String emailId, String taskName) throws TaskNotFoundException;
    List<Task> getCompletedTask(String emailId);
}
