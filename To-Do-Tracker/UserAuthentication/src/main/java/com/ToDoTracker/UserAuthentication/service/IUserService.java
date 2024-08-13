package com.ToDoTracker.UserAuthentication.service;

import com.ToDoTracker.UserAuthentication.domain.User;
import com.ToDoTracker.UserAuthentication.exception.UserAlreadyExistsException;
import com.ToDoTracker.UserAuthentication.exception.UserNotFoundException;

import java.util.List;

public interface IUserService {
    User addUser(User user) throws UserAlreadyExistsException;

    User loginUser(String emailId, String password) throws UserNotFoundException;

    List<User> getAllUsers();

    public boolean deleteUserById(String emailId);
}
