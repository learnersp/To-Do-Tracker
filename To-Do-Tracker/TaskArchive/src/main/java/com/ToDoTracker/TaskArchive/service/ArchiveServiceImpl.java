package com.ToDoTracker.TaskArchive.service;

import com.ToDoTracker.TaskArchive.domain.Task;
import com.ToDoTracker.TaskArchive.domain.User;
import com.ToDoTracker.TaskArchive.exception.TaskDoesNotExistsException;
import com.ToDoTracker.TaskArchive.repository.ArchiveRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;
@Service
public class ArchiveServiceImpl implements IArchieveService{

    private ArchiveRepository archiveRepository;
    @Autowired
    public ArchiveServiceImpl(ArchiveRepository archiveRepository) {
        this.archiveRepository = archiveRepository;
    }

    @Override
    public List<Task> getAllTasks(String emailId) {
        List<Task> tasks = archiveRepository.findById(emailId).get().getTasks();
        return tasks;
    }

    @Override
    public boolean deleteTaskByTaskId(String emailId, String taskName) throws TaskDoesNotExistsException {
        User user = archiveRepository.findById(emailId).get();
        List<Task> tasks = user.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskDoesNotExistsException();
        }
        tasks.remove(task);
        user.setTasks(tasks);
        archiveRepository.save(user);
        return true;
    }

    @Override
    public User saveUser(User user) {
        return archiveRepository.save(user);
    }

    @Override
    public boolean addTask(String emailId, Task task) {
        User user1 = archiveRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();

        if(user1.getTasks()==null){
            user1.setTasks(Arrays.asList(task));
        }
        else {
            tasks.add(task);
            user1.setTasks(tasks);
        }
        archiveRepository.save(user1);

        return true;
    }

    @Override
    public Task updateTask(String emailId, Task task) {
        User user1 = archiveRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        for (Task taskToUpdate: tasks) {
            if (taskToUpdate.getTaskName().equalsIgnoreCase(task.getTaskName())){
                taskToUpdate.setTaskName(task.getTaskName());
                taskToUpdate.setTaskContent(task.getTaskContent());
                taskToUpdate.setImageURL(task.getImageURL());
                taskToUpdate.setTaskDeadline(task.getTaskDeadline());
                taskToUpdate.setTaskPriorityLevel(task.getTaskPriorityLevel());
                taskToUpdate.setTaskCompleted(task.isTaskCompleted());
            }
        }
        archiveRepository.save(user1);
        return task;
    }

    @Override
    public Task getTaskByTaskId(String emailId, String taskName) throws TaskDoesNotExistsException {
        User user1 = archiveRepository.findById(emailId).get();
        List<Task> tasks = user1.getTasks();
        Task task = tasks.stream()
                .filter(obj -> taskName.equalsIgnoreCase(obj.getTaskName()))
                .findAny().orElse(null);
        if(tasks == null || !tasks.contains(task)){
            throw new TaskDoesNotExistsException();
        }
        archiveRepository.save(user1);

        return task;
    }
}
