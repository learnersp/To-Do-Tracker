package com.ToDoTracker.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;



@Document
//@Data
//@NoArgsConstructor
//@AllArgsConstructor
//@ToString
public class Task {

    private String taskName;
    private String taskContent;
    private String imageURL;
    private LocalDate taskDeadline;
    private String taskCategory;
    private String taskPriorityLevel;
    private boolean isTaskCompleted;

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getTaskContent() {
        return taskContent;
    }

    public void setTaskContent(String taskContent) {
        this.taskContent = taskContent;
    }

    public String getImageURL() {
        return imageURL;
    }

    public void setImageURL(String imageURL) {
        this.imageURL = imageURL;
    }

    public LocalDate getTaskDeadline() {
        return taskDeadline;
    }

    public void setTaskDeadline(LocalDate taskDeadline) {
        this.taskDeadline = taskDeadline;
    }

    public String getTaskCategory() {
        return taskCategory;
    }

    public void setTaskCategory(String taskCategory) {
        this.taskCategory = taskCategory;
    }

    public String getTaskPriorityLevel() {
        return taskPriorityLevel;
    }

    public void setTaskPriorityLevel(String taskPriorityLevel) {
        this.taskPriorityLevel = taskPriorityLevel;
    }

    public boolean isTaskCompleted() {
        return isTaskCompleted;
    }

    public void setTaskCompleted(boolean taskCompleted) {
        isTaskCompleted = taskCompleted;
    }

    public Task(String taskName, String taskContent, String imageURL, LocalDate taskDeadline, String taskCategory, String taskPriorityLevel, boolean isTaskCompleted) {
        this.taskName = taskName;
        this.taskContent = taskContent;
        this.imageURL = imageURL;
        this.taskDeadline = taskDeadline;
        this.taskCategory = taskCategory;
        this.taskPriorityLevel = taskPriorityLevel;
        this.isTaskCompleted = isTaskCompleted;
    }

    public Task() {
    }

    @Override
    public String toString() {
        return "Task{" +
                "taskName='" + taskName + '\'' +
                ", taskContent='" + taskContent + '\'' +
                ", imageURL='" + imageURL + '\'' +
                ", taskDeadline=" + taskDeadline +
                ", taskCategory='" + taskCategory + '\'' +
                ", taskPriorityLevel='" + taskPriorityLevel + '\'' +
                ", isTaskCompleted=" + isTaskCompleted +
                '}';
    }
}
