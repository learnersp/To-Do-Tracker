package com.ToDoTracker.proxy;

import com.ToDoTracker.domain.User;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@FeignClient(name="archive-service", url ="http://localhost:8081")
public interface UserArchiveProxy {

    @PostMapping("/api/v3/addUserInArchive")
    public ResponseEntity<?> saveUserToArchive (@RequestBody User user);
}
