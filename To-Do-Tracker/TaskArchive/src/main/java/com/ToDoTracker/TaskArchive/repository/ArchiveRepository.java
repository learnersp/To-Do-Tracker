package com.ToDoTracker.TaskArchive.repository;

import com.ToDoTracker.TaskArchive.domain.User;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ArchiveRepository extends MongoRepository<User, String> {
}
