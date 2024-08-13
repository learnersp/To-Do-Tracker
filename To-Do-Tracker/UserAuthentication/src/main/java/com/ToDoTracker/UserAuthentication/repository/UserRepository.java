package com.ToDoTracker.UserAuthentication.repository;

import com.ToDoTracker.UserAuthentication.domain.User;
import com.ToDoTracker.UserAuthentication.exception.UserNotFoundException;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepository extends JpaRepository<User, String> {
    User findByEmailId(String emailId) throws UserNotFoundException;
}
