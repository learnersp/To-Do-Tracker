package com.ToDoTracker.TaskArchive.domain;

import jakarta.annotation.sql.DataSourceDefinitions;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.List;
@Document
@Data
@NoArgsConstructor
@AllArgsConstructor

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

}
