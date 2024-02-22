package com.example.webd3102_assignment1.dao;

import com.example.webd3102_assignment1.model.Task;

import java.sql.SQLException;
import java.util.List;

public interface TasksDAO {
    int insert(Task task) throws SQLException;
    int update(Task task) throws SQLException;
    int updateToComplete(int taskId) throws SQLException;
    int updateToIncomplete(int taskId) throws SQLException;
    int delete(int taskId) throws SQLException;
    Task select(int taskId) throws SQLException;
    List<Task> selectToday() throws SQLException;
    List<Task> selectDueRelative() throws SQLException;

}
