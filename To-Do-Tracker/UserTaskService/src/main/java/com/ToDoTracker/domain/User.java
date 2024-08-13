package com.ToDoTracker.domain;


import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.Arrays;
import java.util.List;


@Document
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
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

    public User() {
    }

    public User(String firstName, String lastName, String file, byte[] img, String imageURL, String emailId, String password,  List<Task> tasks) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.file = file;
        this.img = img;
        this.imageURL = imageURL;
        this.emailId = emailId;
        this.password = password;
//        this.role = role;
        this.tasks = tasks;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public byte[] getImg() {
        return img;
    }

    public void setImg(byte[] img) {
        this.img = img;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public String getEmailId() {
        return emailId;
    }

    public void setEmailId(String emailId) {
        this.emailId = emailId;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }

    public List<Task> getTasks() {
        return tasks;
    }

    public void setTasks(List<Task> tasks) {
        this.tasks = tasks;
    }

    @Override
    public String toString() {
        return "User{" +
                "firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", file='" + file + '\'' +
                ", img=" + Arrays.toString(img) +
                ", imageURL='" + imageURL + '\'' +
                ", emailId='" + emailId + '\'' +
                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
                ", tasks=" + tasks +
                '}';
    }
}

