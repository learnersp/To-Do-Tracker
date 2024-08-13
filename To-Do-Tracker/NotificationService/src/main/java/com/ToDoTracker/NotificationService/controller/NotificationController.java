package com.ToDoTracker.NotificationService.controller;


import com.ToDoTracker.NotificationService.domain.Task;
import com.ToDoTracker.NotificationService.domain.User;
import com.ToDoTracker.NotificationService.exception.ImpTaskAlreadyExistsException;
import com.ToDoTracker.NotificationService.exception.ImpTaskNotFoundException;
import com.ToDoTracker.NotificationService.service.NotificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v4")

public class NotificationController {
      private ResponseEntity responseEntity;

      private NotificationService notificationService;

      @Autowired
      public NotificationController(NotificationService notificationService) {
          this.notificationService = notificationService;

      }
      @PostMapping("/addUserInNotification")
      public ResponseEntity<?> saveUser(@RequestBody User user) {
          return new ResponseEntity<>(notificationService.saveUser(user), HttpStatus.OK);
      }

      @PutMapping("/addTaskInNotification/{emailId}")
      public ResponseEntity<?> addTask(@PathVariable String emailId, @RequestBody Task task) throws ImpTaskAlreadyExistsException {
          try {
              return new ResponseEntity<>(notificationService.addTask(emailId, task), HttpStatus.OK);
          } catch (ImpTaskAlreadyExistsException e) {
              throw new ImpTaskAlreadyExistsException();
          } catch (Exception e) {
              return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
          }

      }

      @PutMapping("/updateTaskInNotification/{emailId}")
      public ResponseEntity<?> updateTask(@PathVariable String emailId, @RequestBody Task task) {
          return new ResponseEntity<>(notificationService.updateTask(emailId, task), HttpStatus.OK);
      }

      @GetMapping("/notification/impTasks/{emailId}")
      public ResponseEntity<?> getAllImpTasks(@PathVariable String emailId) {
          return new ResponseEntity<>(notificationService.getAllImpTask(emailId), HttpStatus.OK);
      }

      @GetMapping("/notification/getAllTasksFromNotification/{emailId}")
      public ResponseEntity<?> getAllTasks(@PathVariable String emailId) {
          return new ResponseEntity<>(notificationService.getAllTask(emailId), HttpStatus.OK);
      }


      @GetMapping("/notification/getAllUser")
      public ResponseEntity<?> getAllUser() {
          return new ResponseEntity<>(notificationService.getAllUsers(), HttpStatus.OK);
      }

      @DeleteMapping("/deleteFromNotification/{emailId}/{taskName}")
      public ResponseEntity<?> deleteTaskByTaskId(@PathVariable String emailId, @PathVariable String taskName) throws ImpTaskNotFoundException {
          try {
              return new ResponseEntity<>(notificationService.deleteTaskByTaskId(emailId, taskName), HttpStatus.OK);
          } catch (ImpTaskNotFoundException e) {
              throw new ImpTaskNotFoundException();
          } catch (Exception e) {
              return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
          }

      }

  }

