package com.ToDoTracker.NotificationService.repository;


import com.ToDoTracker.NotificationService.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface  NotificationRepository extends MongoRepository<User,String> {


}
