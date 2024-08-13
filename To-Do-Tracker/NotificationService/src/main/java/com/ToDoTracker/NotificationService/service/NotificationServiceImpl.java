package com.ToDoTracker.NotificationService.service;

import com.ToDoTracker.NotificationService.domain.Task;
import com.ToDoTracker.NotificationService.domain.User;
import com.ToDoTracker.NotificationService.exception.ImpTaskAlreadyExistsException;
import com.ToDoTracker.NotificationService.exception.ImpTaskNotFoundException;
import com.ToDoTracker.NotificationService.repository.NotificationRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
public class NotificationServiceImpl implements  NotificationService{
    @Autowired
    private NotificationRepository notificationRepository;


//    @Autowired
//    public NotificationServiceImpl(NotificationRepository notificationRepository) {
//        this.notificationRepository = notificationRepository;
//    }


    @Override
    public List<Task> getAllTask(String emailId) {
        List<Task> tasks = notificationRepository.findById(emailId).get().getTasks();
        return tasks;
    }

    @Override
    public User saveUser(User user) {
        return notificationRepository.save(user);
    }

    @Override
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws ImpTaskNotFoundException {
//        User user = notificationRepository.findById(emailId).get();
//        List<Task> tasks = user.getTasks();
//        Task task = tasks.stream()
//                .filter(obj -> taskName==(obj.getTaskName()))
//                .findAny().orElse(null);
//        if(tasks == null || !tasks.contains(task)){
//            throw new ImpTaskNotFoundException();
//        }
//        tasks.remove(task);
//        user.setTasks(tasks);
//        notificationRepository.save(user);
//        return true;
//    }
        Optional<User> userOptional = notificationRepository.findById(emailId);
        if (userOptional.isEmpty()) {
            throw new ImpTaskNotFoundException();
        }

        User user = userOptional.get();
        List<Task> tasks = user.getTasks();
        if (tasks == null) {
            // Handle case where tasks list is null
            throw new ImpTaskNotFoundException();
        }

        // Find the task by name
        Task taskToRemove = tasks.stream()
                .filter(obj -> taskName.equals(obj.getTaskName()))
                .findAny()
                .orElse(null);

        if (taskToRemove == null) {
            // Handle case where task with given name is not found
            throw new ImpTaskNotFoundException();
        }

        // Remove the task
        tasks.remove(taskToRemove);
        user.setTasks(tasks);
        notificationRepository.save(user);
        return true;
    }


    @Override
    public List<Task> getAllImpTask(String emailId) {
        List<Task> tasks = notificationRepository.findById(emailId).get().getTasks();
        List<Task> impTasks = new ArrayList<>();
        tasks.forEach(e->{
            if(e.getTaskPriorityLevel().equals("high")) {
                impTasks.add(e);
            } else if (e.getTaskPriorityLevel().equals("medium")) {
                impTasks.add(e);
            }
        });
        return impTasks;
    }

    @Override
    public boolean addTask(String emailId, Task task) throws ImpTaskAlreadyExistsException {
        User user1 = notificationRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        if(tasks == null){
            tasks = new ArrayList<>();
        }
        for (Task t : tasks) {
            if (t.getTaskName().equalsIgnoreCase(task.getTaskName())) {
                throw new ImpTaskAlreadyExistsException();
            }
        }
        tasks.add(task);
        user1.setTasks(tasks);

        notificationRepository.save(user1);
        return true;
    }

    @Override
    public Task updateTask(String emailId, Task task) {
        User user1 = notificationRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        for (Task taskToUpdate: tasks) {
            if (taskToUpdate.getTaskName().equalsIgnoreCase(task.getTaskName())){
                taskToUpdate.setTaskName(task.getTaskName());
                taskToUpdate.setTaskContent(task.getTaskContent());
                taskToUpdate.setImageURL(task.getImageURL());
                taskToUpdate.setTaskDeadline(task.getTaskDeadline());
                taskToUpdate.setTaskCategory(task.getTaskCategory());
                taskToUpdate.setTaskPriorityLevel(task.getTaskPriorityLevel());
                taskToUpdate.setTaskCompleted(task.isTaskCompleted());
            }
        }
        notificationRepository.save(user1);
        return task;
    }

    @Override
    public List<User> getAllUsers() {
        return notificationRepository.findAll();
    }
}
