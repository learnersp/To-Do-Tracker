package com.ToDoTracker.controller;


import com.fasterxml.jackson.databind.ObjectMapper;
import com.ToDoTracker.domain.Task;
import com.ToDoTracker.domain.User;
import com.ToDoTracker.exception.TaskAlreadyExistsException;
import com.ToDoTracker.exception.TaskNotFoundException;
import com.ToDoTracker.exception.UserAlreadyExistsException;
import com.ToDoTracker.exception.UserNotFoundException;
import com.ToDoTracker.service.UserTaskServiceImpl;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.logging.Logger;

@RestController
//@CrossOrigin("http://localhost:4200")

@RequestMapping("/api/v1")
public class UserController {

    private final UserTaskServiceImpl userTaskService;



    @Autowired
    public UserController(UserTaskServiceImpl userTaskService) {
        this.userTaskService = userTaskService;
    }
//Use this method when image is not working
//    @PostMapping("/AddUserInUserTask")
//    public ResponseEntity<?> saveUser(@RequestBody User user) {
//        try {
//            // Common logic for both cases
//            System.out.println(user);
//            User savedUser = userTaskService.saveUser(user);
//            return new ResponseEntity<>(savedUser, HttpStatus.CREATED);
//        } catch (UserAlreadyExistsException e) {
//            // Log the error and return a user-friendly error message to the client
////            log.error("Error saving user", e);
//            return new ResponseEntity<>("User with this email ID already exists", HttpStatus.CONFLICT);
//        }
//    }

    @PostMapping("/AddUserInUserTask")
    public ResponseEntity<?> saveUser (@RequestParam("file") MultipartFile file, @RequestParam("user") String user) throws UserAlreadyExistsException, IOException {
        User user1 = new ObjectMapper().readValue(user, User.class);

        try{
            System.out.println(user1);
            return new ResponseEntity<>(userTaskService.saveUser(user1,file), HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PostMapping("/AddUserInUserTaskNoImage")
    public ResponseEntity<?> saveUserWithImage (@RequestBody User user) throws UserAlreadyExistsException {
        try{
            System.out.println(user);
            return new ResponseEntity<>(userTaskService.saveUserWithNoImage(user), HttpStatus.CREATED);
        }catch(UserAlreadyExistsException e){
            throw new UserAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @PutMapping("/task/addTaskInUserTask/{emailId}")
    public ResponseEntity<?> addTask (@PathVariable String emailId, @RequestBody Task task) throws TaskAlreadyExistsException {
        try{
            return new ResponseEntity<>(userTaskService.addTask(emailId, task), HttpStatus.CREATED);
        }catch(TaskAlreadyExistsException e){
            throw new TaskAlreadyExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }

    }

    @PutMapping("/task/updateTaskInUserTask/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(userTaskService.updateTask(emailId, task), HttpStatus.OK);
    }

    @GetMapping("/task/getAllUsersFromUserTask")
    public ResponseEntity<?> getAllUsers (){
        return new ResponseEntity<>(userTaskService.getAllUsers(), HttpStatus.OK);
    }

    @GetMapping("/task/getAllTasksOfUserFromUserTask/{emailId}")
    public ResponseEntity<?> getAllTasksOfUser (@PathVariable String emailId) {
        return new ResponseEntity<>(userTaskService.getAllTasksOfUser(emailId), HttpStatus.OK);
    }

    @GetMapping("/task/getUserByIdInUserTask/{emailId}")
    public ResponseEntity<?> getUserById (@PathVariable String emailId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getUserById(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @GetMapping("/task/getByTaskIdInUserTask/{emailId}/{taskName}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.getTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    //to set priority of task
    @GetMapping("/completed/{emailId}")
    public ResponseEntity<?> getAllImpTasks(@PathVariable String emailId){
        return new ResponseEntity<>(userTaskService.getCompletedTask(emailId),HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteAllUserFromUserTask")
    public ResponseEntity<?> deleteAllUser () {
        return new ResponseEntity<>(userTaskService.deleteAllUser(), HttpStatus.OK);
    }

    @DeleteMapping("/task/deleteUserByIdInUserTask/{emailId}")
    public ResponseEntity<?> deleteUserById (@PathVariable String emailId) throws UserNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteUserById(emailId), HttpStatus.OK);
        }catch(UserNotFoundException e){
            throw new UserNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

    @DeleteMapping("/task/deleteTaskByTaskIdInUserTask/{emailId}/{taskName}")
    public ResponseEntity<?> deleteTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskNotFoundException {
        try{
            return new ResponseEntity<>(userTaskService.deleteTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskNotFoundException e){
            throw new TaskNotFoundException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }
}
