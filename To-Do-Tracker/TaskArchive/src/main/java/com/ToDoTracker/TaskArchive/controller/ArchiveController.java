package com.ToDoTracker.TaskArchive.controller;

import com.ToDoTracker.TaskArchive.domain.Task;
import com.ToDoTracker.TaskArchive.domain.User;
import com.ToDoTracker.TaskArchive.exception.TaskDoesNotExistsException;
import com.ToDoTracker.TaskArchive.service.IArchieveService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
//@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/api/v3")
public class ArchiveController {

    private final IArchieveService iArchieveService;
    @Autowired
    public ArchiveController(IArchieveService iArchieveService) {
        this.iArchieveService = iArchieveService;
    }
    @PostMapping("/addUserInArchive")
    public ResponseEntity<?> insertUser(@RequestBody User user){
        return new ResponseEntity<>(iArchieveService.saveUser(user),HttpStatus.OK);
    }
    @PutMapping("/addTaskInArchive/{emailId}")
    public ResponseEntity<?> addTask(@PathVariable String emailId, @RequestBody Task task){

        return new ResponseEntity<>(iArchieveService.addTask(emailId, task),HttpStatus.OK);

    }
    @PutMapping("/updateTaskInArchive/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) {
        return new ResponseEntity<>(iArchieveService.updateTask(emailId, task), HttpStatus.OK);
    }
    @GetMapping("/getAllTasksFromArchive/{emailId}")
    public ResponseEntity<?> getAllTasks(@PathVariable String emailId){
        return new ResponseEntity<>(iArchieveService.getAllTasks(emailId),HttpStatus.OK);
    }
    @DeleteMapping("/deleteTaskFromArchive/{emailId}/{taskName}")
    public ResponseEntity<?> deleteTask(@PathVariable String emailId, @PathVariable String taskName) throws TaskDoesNotExistsException {
        iArchieveService.deleteTaskByTaskId(emailId,taskName);
        return new ResponseEntity<>("Task deleted",HttpStatus.OK);

    }
    @GetMapping("/getByTaskIdFromArchive/{emailId}/{taskName}")
    public ResponseEntity<?> getTaskByTaskId (@PathVariable String emailId, @PathVariable String taskName) throws TaskDoesNotExistsException {
        try{
            return new ResponseEntity<>(iArchieveService.getTaskByTaskId(emailId, taskName), HttpStatus.OK);
        }catch(TaskDoesNotExistsException e){
            throw new TaskDoesNotExistsException();
        }catch(Exception e){
            return new ResponseEntity<>("Server error, try after sometime", HttpStatus.INTERNAL_SERVER_ERROR);
        }
    }

}
