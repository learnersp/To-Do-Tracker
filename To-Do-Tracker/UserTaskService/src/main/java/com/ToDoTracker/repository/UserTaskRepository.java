package com.ToDoTracker.repository;

import com.ToDoTracker.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

public interface UserTaskRepository extends MongoRepository<User, String> {

    @Query("{'tasks.taskId': ?0}")
    User findByTaskId (String taskId);

}
