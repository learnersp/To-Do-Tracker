//package com.ToDoTracker.config;
//
//import lombok.AllArgsConstructor;
//import lombok.Data;
//import lombok.NoArgsConstructor;
//
//import java.time.LocalDate;
//
////when I try to access these in serviceimpl lombok is not working that's why I generated getter, setters and no & args constructor, toString
////@Data
////@NoArgsConstructor
////@AllArgsConstructor
//public class TaskDTO {
//    private String taskName;
//    private String taskContent;
//    private String imageURL;
//    private LocalDate taskDeadline;
//    private String taskCategory;
//    private String taskPriorityLevel;
//    private boolean isTaskCompleted;
//
//    public TaskDTO() {
//    }
//
//    public TaskDTO(String taskName, String taskContent, String imageURL, LocalDate taskDeadline, String taskCategory, String taskPriorityLevel, boolean isTaskCompleted) {
//        this.taskName = taskName;
//        this.taskContent = taskContent;
//        this.imageURL = imageURL;
//        this.taskDeadline = taskDeadline;
//        this.taskCategory = taskCategory;
//        this.taskPriorityLevel = taskPriorityLevel;
//        this.isTaskCompleted = isTaskCompleted;
//    }
//
//    public String getTaskName() {
//        return taskName;
//    }
//
//    public void setTaskName(String taskName) {
//        this.taskName = taskName;
//    }
//
//    public String getTaskContent() {
//        return taskContent;
//    }
//
//    public void setTaskContent(String taskContent) {
//        this.taskContent = taskContent;
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
//    public LocalDate getTaskDeadline() {
//        return taskDeadline;
//    }
//
//    public void setTaskDeadline(LocalDate taskDeadline) {
//        this.taskDeadline = taskDeadline;
//    }
//
//    public String getTaskCategory() {
//        return taskCategory;
//    }
//
//    public void setTaskCategory(String taskCategory) {
//        this.taskCategory = taskCategory;
//    }
//
//    public String getTaskPriorityLevel() {
//        return taskPriorityLevel;
//    }
//
//    public void setTaskPriorityLevel(String taskPriorityLevel) {
//        this.taskPriorityLevel = taskPriorityLevel;
//    }
//
//    public boolean isTaskCompleted() {
//        return isTaskCompleted;
//    }
//
//    public void setTaskCompleted(boolean taskCompleted) {
//        isTaskCompleted = taskCompleted;
//    }
//
//    @Override
//    public String toString() {
//        return "TaskDTO{" +
//                "taskName='" + taskName + '\'' +
//                ", taskContent='" + taskContent + '\'' +
//                ", imageURL='" + imageURL + '\'' +
//                ", taskDeadline=" + taskDeadline +
//                ", taskCategory='" + taskCategory + '\'' +
//                ", taskPriorityLevel='" + taskPriorityLevel + '\'' +
//                ", isTaskCompleted=" + isTaskCompleted +
//                '}';
//    }
//}
