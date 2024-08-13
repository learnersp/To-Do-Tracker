package com.ToDoTracker.NotificationService.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;


@AllArgsConstructor
@NoArgsConstructor
@Document
@Data
public class User {

    private String firstName;
    private String lastName;
    private String file;
    private byte[] img;
    private String imageURL;
    @Id
    private String emailId;
    private String password;
//    private String role;
    private List<Task> tasks;

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }
}
