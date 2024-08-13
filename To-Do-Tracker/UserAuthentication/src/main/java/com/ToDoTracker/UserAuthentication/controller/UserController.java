package com.ToDoTracker.UserAuthentication.controller;

import com.ToDoTracker.UserAuthentication.domain.LoginResponse;
import com.ToDoTracker.UserAuthentication.domain.User;
import com.ToDoTracker.UserAuthentication.exception.UserAlreadyExistsException;
import com.ToDoTracker.UserAuthentication.exception.UserNotFoundException;
import com.ToDoTracker.UserAuthentication.security.SecurityTokenGenerator;
import com.ToDoTracker.UserAuthentication.service.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.Map;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v2")
public class UserController {

    private  UserServiceImpl userService;

    private SecurityTokenGenerator securityTokenGenerator;
    @Autowired
    public UserController(UserServiceImpl userService, SecurityTokenGenerator securityTokenGenerator) {
        this.userService = userService;
        this.securityTokenGenerator = securityTokenGenerator;
    }

    @PostMapping("/AddUserInUserAuth")
    public ResponseEntity<?> insertUser(@RequestBody User user) throws UserAlreadyExistsException {
        try {
            return new ResponseEntity<>(userService.addUser(user), HttpStatus.CREATED);
        } catch (UserAlreadyExistsException e) {
            throw new UserAlreadyExistsException();
        } catch (Exception e) {
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/user/fetchAllUsersFromUserAuth")
    public ResponseEntity<?> fetchAllUsers() {
        return new ResponseEntity<>(userService.getAllUsers(), HttpStatus.OK);
    }

    @PostMapping("/login")
    public ResponseEntity<?> loginFun(@RequestBody User user) throws UserNotFoundException {
        try {

            User user1 = userService.loginUser(user.getEmailId(), user.getPassword());

            Map<String, String> secretKey = new HashMap<>();
            secretKey = securityTokenGenerator.generateToken(user);
            LoginResponse loginResponse = new LoginResponse();
            loginResponse.setUser(user1);
            loginResponse.setSecretKeyToken(secretKey);
            return new ResponseEntity<>(loginResponse, HttpStatus.OK);
        } catch (UserNotFoundException e) {
            throw new UserNotFoundException();
        } catch (Exception e) {
            return new ResponseEntity<>("Network Error", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
    @DeleteMapping("/deleteByIdInUserAuth/{emailId}")
    public ResponseEntity<?> deleteByUserId(@PathVariable String emailId) {
        userService.deleteUserById(emailId);
        return new ResponseEntity<>("User record has been deleted", HttpStatus.OK);
    }

}
