package com.example.webd3102_assignment1.dao;

import com.example.webd3102_assignment1.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TasksDAO {
    int insert(Task task) throws SQLException;
    int update(Task task) throws SQLException;
    int updateCompletionStatus(int taskId) throws SQLException;
    int delete(int taskId) throws SQLException;
    Task select(int taskId) throws SQLException;
    List<Task> select() throws SQLException;
    int dueToday() throws SQLException;
    int dueThisWeek() throws SQLException;
    int numCompleted() throws SQLException;
    int numNotCompleted() throws SQLException;

}
