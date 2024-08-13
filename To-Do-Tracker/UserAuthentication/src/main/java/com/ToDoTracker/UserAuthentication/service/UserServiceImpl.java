package com.ToDoTracker.UserAuthentication.service;


import com.ToDoTracker.UserAuthentication.domain.User;
import com.ToDoTracker.UserAuthentication.exception.UserAlreadyExistsException;
import com.ToDoTracker.UserAuthentication.exception.UserNotFoundException;
import com.ToDoTracker.UserAuthentication.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class UserServiceImpl implements IUserService {
    private UserRepository userRepository;

    @Autowired
    public UserServiceImpl(UserRepository userRepository, JavaMailSender mailSender, EmailService emailService) {
        this.userRepository = userRepository;
        this.mailSender = mailSender;
        this.emailService = emailService;
    }

    @Autowired
    private JavaMailSender mailSender;

    @Autowired
    private EmailService emailService;

    @Override
    public User addUser(User user) throws UserAlreadyExistsException {
        if (userRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        return userRepository.save(user);
    }
    @Override
    public User loginUser(String emailId, String password) throws UserNotFoundException {
        // Retrieve the user from the repository based on the provided emailId
        User user = userRepository.findByEmailId(emailId);

        // Check if the user exists
        if (user == null) {
            throw new UserNotFoundException();
        }

        // Check if the provided password matches the user's password
        if (!user.getPassword().equals(password)) {
            throw new UserNotFoundException();
        }

        // Send email notification on successful login
        String subject = "Login Notification";
        String message = "Hello, " +"  😊\n\n" +
                "Thank you for logging in.\n\n" +
                "Take the first step towards productivity by creating your tasks.\n\n" +
                "Utilize our priority-based filtering feature to stay organized and focused on what matters most. 🚀\n\n" +
                "";

        emailService.sendEmail(user.getEmailId(), subject, message);

        return user;
    }

    @Override
    public List<User> getAllUsers() {
        return userRepository.findAll();
    }

    @Override
    public boolean deleteUserById(String emailId) {
        userRepository.deleteById(emailId);
        return true;
    }
}