package com.ToDoTracker.service;

//import com.ToDoTracker.config.Producer;
//import com.ToDoTracker.config.UserDTO;
import com.ToDoTracker.domain.Task;
import com.ToDoTracker.domain.User;
import com.ToDoTracker.exception.TaskAlreadyExistsException;
import com.ToDoTracker.exception.TaskNotFoundException;
import com.ToDoTracker.exception.UserAlreadyExistsException;
import com.ToDoTracker.exception.UserNotFoundException;
import com.ToDoTracker.proxy.UserArchiveProxy;
import com.ToDoTracker.proxy.UserAuthenticationProxy;
import com.ToDoTracker.proxy.UserNotificationProxy;
import com.ToDoTracker.repository.UserTaskRepository;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class UserTaskServiceImpl implements UserTaskService{

    private final UserTaskRepository userTaskRepository;
    private UserNotificationProxy userNotificationProxy;
    private UserArchiveProxy archiveProxy;
    private UserAuthenticationProxy userAuthenticationProxy;


    @Autowired
    public UserTaskServiceImpl(UserTaskRepository userTaskRepository, UserNotificationProxy userNotificationProxy, UserArchiveProxy userArchiveProxy, UserAuthenticationProxy userAuthenticationProxy) {
        this.userTaskRepository = userTaskRepository;
        this.userNotificationProxy = userNotificationProxy;
        this.archiveProxy = userArchiveProxy;
        this.userAuthenticationProxy = userAuthenticationProxy;
    }

    @Override
    public User saveUser(User user, MultipartFile file) throws UserAlreadyExistsException, IOException{
        // Check if the user already exists
        if (userTaskRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }
        user.setImg(file.getBytes());
       user.setFile(file.getOriginalFilename());
        // Save the user in the main repository
        User savedUser = userTaskRepository.save(user);
        if(!(savedUser.getEmailId().isEmpty()))
        {
            ResponseEntity r=userAuthenticationProxy.insertUser(savedUser);
            System.out.println("Authentication response: " + r.getBody());

        }

        // Notify the user through the Notification proxy
        ResponseEntity<?> notificationResponse = userNotificationProxy.saveUserToNotification(user);

//         Save the user in the Authentication proxy
//        ResponseEntity<?> authenticationResponse = userAuthenticationProxy.saveUser(savedUser);

        // Archive the user through the Archive proxy
        ResponseEntity<?> archiveResponse = archiveProxy.saveUserToArchive(user);

        // Print responses (you can handle these responses according to your requirements)
        System.out.println("Notification response: " + notificationResponse.getBody());
//        System.out.println("Authentication response: " + authenticationResponse.getBody());
        System.out.println("Archive response: " + archiveResponse.getBody());

        return savedUser;
    }
    //Use this method if image is not working
    //    @Override
//    public User saveUser(User user) throws UserAlreadyExistsException {
//        // Check if the user already exists
//        if (userTaskRepository.findById(user.getEmailId()).isPresent()) {
//            throw new UserAlreadyExistsException();
//        }
//
//        // Save the user in the main repository
//        User savedUser = userTaskRepository.save(user);
//        if(!(savedUser.getEmailId().isEmpty()))
//        {
//            ResponseEntity r=userAuthenticationProxy.insertUser(savedUser);
//            System.out.println("Authentication response: " + r.getBody());
//
//        }
//
//        // Notify the user through the Notification proxy
//        ResponseEntity<?> notificationResponse = userNotificationProxy.saveUserToNotification(user);
//
//        // Save the user in the Authentication proxy
//        //ResponseEntity<?> authenticationResponse = userAuthenticationProxy.saveUser(savedUser);
//
//        // Archive the user through the Archive proxy
//        ResponseEntity<?> archiveResponse = archiveProxy.saveUserToArchive(user);
//
//        // Print responses (you can handle these responses according to your requirements)
//        System.out.println("Notification response: " + notificationResponse.getBody());
//        //System.out.println("Authentication response: " + authenticationResponse.getBody());
//        System.out.println("Archive response: " + archiveResponse.getBody());
//
//        return savedUser;
//    }





    @Override
    public User saveUserWithNoImage(User user) throws UserAlreadyExistsException {
        if (userTaskRepository.findById(user.getEmailId()).isPresent()) {
            throw new UserAlreadyExistsException();
        }

        // Save the user to Archive through the Archive proxy
        archiveProxy.saveUserToArchive(user);

        // Save the user to Notification through the Notification proxy
        userNotificationProxy.saveUserToNotification(user);

        // Save the user to the main repository
        return userTaskRepository.save(user);
    }


    @Override
    public Task addTask(String emailId, Task task) throws TaskAlreadyExistsException {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();

        if (tasks == null) {
            tasks = new ArrayList<>();
        }

        for (Task t : tasks) {
            if (t.getTaskName().equalsIgnoreCase(task.getTaskName())) {
                throw new TaskAlreadyExistsException();
            }
        }

        tasks.add(task);
        user1.setTasks(tasks);
        userTaskRepository.save(user1);

        // Notify the user through the Notification proxy
        ResponseEntity<?> notificationResponse = userNotificationProxy.saveTaskDetailFromUserTask(emailId, task);

        // Print the notification response (you can handle it based on your requirements)
        System.out.println("Notification response: " + notificationResponse.getBody());

        return task;
    }

    @Override
    public Task updateTask(String emailId, Task task) {
        User user1 = userTaskRepository.findById(emailId).orElse(null);

        if (user1 != null) {
            List<Task> tasks = user1.getTasks();

            for (Task taskToUpdate : tasks) {
                // Check for null before invoking equalsIgnoreCase
                if (taskToUpdate.getTaskName() != null && taskToUpdate.getTaskName().equalsIgnoreCase(task.getTaskName())) {
                    taskToUpdate.setTaskName(task.getTaskName());
                    taskToUpdate.setTaskContent(task.getTaskContent());
                    taskToUpdate.setImageURL(task.getImageURL());
                    taskToUpdate.setTaskDeadline(task.getTaskDeadline());
                    taskToUpdate.setTaskCategory(task.getTaskCategory());
                    taskToUpdate.setTaskPriorityLevel(task.getTaskPriorityLevel());
                    taskToUpdate.setTaskCompleted(task.isTaskCompleted());
                }
            }

            userTaskRepository.save(user1);

            // Notify the user through the Notification proxy
            ResponseEntity<?> notificationResponse = userNotificationProxy.updateTask(emailId, task);

            // Print the notification response (you can handle it based on your requirements)
            System.out.println("Notification response: " + notificationResponse.getBody());
        }

        return task;
    }


    @Override
    public List<User> getAllUsers() {
        return userTaskRepository.findAll();
    }

    @Override
    public List<Task> getAllTasksOfUser(String emailId) {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        return tasks;
    }

    @Override
    public Optional<User> getUserById(String emailId) throws UserNotFoundException {
        if (userTaskRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        return userTaskRepository.findById(emailId);
    }

    @Override
    public Task getTaskByTaskId(String emailId, String taskName) throws TaskNotFoundException {
        User user1 = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        userTaskRepository.save(user1);

        return task;
    }


    @Override
    public boolean deleteAllUser() {
        userTaskRepository.deleteAll();
        return true;
    }

    @Override
    public boolean deleteUserById(String emailId) throws UserNotFoundException{
        if (userTaskRepository.findById(emailId).isEmpty()){
            throw new UserNotFoundException();
        }
        userTaskRepository.deleteById(emailId);
        return true;
    }

    @Override
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws TaskNotFoundException {
        User user = userTaskRepository.findById(emailId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskNotFoundException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        userTaskRepository.save(user);

        return true;
    }

    @Override
    public List<Task> getCompletedTask(String emailId) {
        List<Task> tasks = userTaskRepository.findById(emailId).get().getTasks();
        List<Task> completed = new ArrayList<>();
        tasks.forEach(e->{if(e.isTaskCompleted())completed.add(e);});
        return completed;
    }
}
