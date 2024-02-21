package com.example.webd3102_assignment1.model;

import java.sql.Date;

public class Task {
    private int taskId;
    private String taskName;
    private Date dueDate;
    private String category;
    private boolean completeStatus;
    private String dueDateRelative;
    private String dayOfWeek;

    // Constructor: includes all attributes
    public Task(int taskId, String taskName, Date dueDate, String category, boolean completeStatus, String dueDateRelative) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.category = category;
        this.completeStatus = completeStatus;
        this.dueDateRelative = dueDateRelative;
    }

    // Constructor: does not include taskId
    public Task(String taskName, Date dueDate, String category, boolean completeStatus, String dueDateRelative) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.category = category;
        this.completeStatus = completeStatus;
        this.dueDateRelative = dueDateRelative;
    }

    // Constructor: does not include relative due date
    public Task(int taskId, String taskName, Date dueDate, String category, boolean completeStatus) {
        this.taskId = taskId;
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.category = category;
        this.completeStatus = completeStatus;
    }

    // Constructor: does not include relative due date or task Id
    public Task(String taskName, Date dueDate, String category, boolean completeStatus) {
        this.taskName = taskName;
        this.dueDate = dueDate;
        this.category = category;
        this.completeStatus = completeStatus;
    }

    // Constructor: default
    public Task() {
    }

    public int getTaskId() {
        return taskId;
    }

    public void setTaskId(int taskId) {
        this.taskId = taskId;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public Date getDueDate() {
        return dueDate;
    }

    public void setDueDate(Date dueDate) {
        this.dueDate = dueDate;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public boolean getCompleteStatus() {
        return completeStatus;
    }

    public void setCompleteStatus(boolean completeStatus) {
        this.completeStatus = completeStatus;
    }

    public String getDueDateRelative() {
        return dueDateRelative;
    }

    public void setDueDateRelative(String dueDateRelative) {
        this.dueDateRelative = dueDateRelative;
    }

    public String getDayOfWeek() {
        return dayOfWeek;
    }

    public void setDayOfWeek(String dayOfWeek) {
        this.dayOfWeek = dayOfWeek;
    }
}
