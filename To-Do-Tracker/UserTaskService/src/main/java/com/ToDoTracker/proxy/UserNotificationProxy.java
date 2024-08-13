package com.ToDoTracker.proxy;


import com.ToDoTracker.domain.Task;
import com.ToDoTracker.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@FeignClient(name="notification-service", url = "http://localhost:8086")


public interface UserNotificationProxy {
    @PostMapping("/api/v4/addUserInNotification")
    public ResponseEntity<?> saveUserToNotification (@RequestBody User user);
    @PutMapping("/api/v4/addTaskInNotification/{emailId}")
    public ResponseEntity<?> saveTaskDetailFromUserTask (@PathVariable String emailId, @RequestBody Task task);
    @PutMapping("/api/v4/updateTaskInNotification/{emailId}")
    public ResponseEntity<?> updateTask (@PathVariable String emailId, @RequestBody Task task) ;
//    @DeleteMapping("/api/v4/deleteFromNotification/{emailId}/{taskName}")
//    public ResponseEntity<?> deleteTaskByTaskId(@PathVariable String emailId, @PathVariable String taskName);


}
