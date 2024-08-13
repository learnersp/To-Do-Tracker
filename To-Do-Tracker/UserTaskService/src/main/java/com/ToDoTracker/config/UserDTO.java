//package com.ToDoTracker.config;
//
//import com.ToDoTracker.domain.Task;
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.util.Arrays;
//import java.util.List;
//
////when I try to access these in serviceimpl lombok is not working that's why I generated getter, setters and no & args constructor, toString
////@Data
////@NoArgsConstructor
////@AllArgsConstructor
//public class UserDTO {
//    private String firstName;
//    private String lastName;
//    private String file;
//    private byte[] img;
//    private String imageURL;
//    private String emailId;
//    private String password;
//    private String role;
//    private List<Task> tasks;
//
//    public UserDTO() {
//    }
//
//    public UserDTO(String firstName, String lastName, String file, byte[] img, String imageURL, String emailId, String password, String role, List<Task> tasks) {
//        this.firstName = firstName;
//        this.lastName = lastName;
//        this.file = file;
//        this.img = img;
//        this.imageURL = imageURL;
//        this.emailId = emailId;
//        this.password = password;
//        this.role = role;
//        this.tasks = tasks;
//    }
//
//    public String getFirstName() {
//        return firstName;
//    }
//
//    public void setFirstName(String firstName) {
//        this.firstName = firstName;
//    }
//
//    public String getLastName() {
//        return lastName;
//    }
//
//    public void setLastName(String lastName) {
//        this.lastName = lastName;
//    }
//
//    public String getFile() {
//        return file;
//    }
//
//    public void setFile(String file) {
//        this.file = file;
//    }
//
//    public byte[] getImg() {
//        return img;
//    }
//
//    public void setImg(byte[] img) {
//        this.img = img;
//    }
//
//    public String getImageURL() {
//        return imageURL;
//    }
//
//    public void setImageURL(String imageURL) {
//        this.imageURL = imageURL;
//    }
//
//    public String getEmailId() {
//        return emailId;
//    }
//
//    public void setEmailId(String emailId) {
//        this.emailId = emailId;
//    }
//
//    public String getPassword() {
//        return password;
//    }
//
//    public void setPassword(String password) {
//        this.password = password;
//    }
//
//    public String getRole() {
//        return role;
//    }
//
//    public void setRole(String role) {
//        this.role = role;
//    }
//
//    public List<Task> getTasks() {
//        return tasks;
//    }
//
//    public void setTasks(List<Task> tasks) {
//        this.tasks = tasks;
//    }
//
//    @Override
//    public String toString() {
//        return "UserDTO{" +
//                "firstName='" + firstName + '\'' +
//                ", lastName='" + lastName + '\'' +
//                ", file='" + file + '\'' +
//                ", img=" + Arrays.toString(img) +
//                ", imageURL='" + imageURL + '\'' +
//                ", emailId='" + emailId + '\'' +
//                ", password='" + password + '\'' +
//                ", role='" + role + '\'' +
//                ", tasks=" + tasks +
//                '}';
//    }
//}
