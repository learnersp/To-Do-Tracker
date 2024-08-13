package com.ToDoTracker.proxy;

import com.ToDoTracker.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="user-authentication-service",url = "http://localhost:8084")
public interface UserAuthenticationProxy {
    @PostMapping("/api/v2/AddUserInUserAuth")
    public ResponseEntity<?> insertUser(@RequestBody User user);

    @PostMapping("/api/v1/AddUserInUserTask")
    public ResponseEntity<?> saveUser(@RequestBody User user);
}

