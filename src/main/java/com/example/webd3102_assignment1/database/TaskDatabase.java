package com.example.webd3102_assignment1.database;

import com.example.webd3102_assignment1.dao.TasksDAO;
import com.example.webd3102_assignment1.model.Task;
import com.example.webd3102_assignment1.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.webd3102_assignment1.database.MySQLConnection.getConnection;

public class TaskDatabase implements TasksDAO {

    private static final String SQL_SELECT = "SELECT taskId, taskName, dueDate, category, completeStatus FROM TASKS ORDER BY dueDate";
    private static final String SQL_SELECT_ONE = "SELECT taskId, taskName, dueDate, category, completeStatus FROM TASKS WHERE taskId=?";
    private static final String SQL_INSERT = "INSERT INTO TASKS(taskName, dueDate, category, completeStatus) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE TASKS SET taskName=?, dueDate=?, category=?, completeStatus=? WHERE taskId=?";
    private static final String SQL_UPDATE_STATUS = "UPDATE TASKS SET completeStatus=? WHERE taskId=?";
    private static final String SQL_DELETE = "DELETE FROM TASKS WHERE taskID=?";
    private static final String SQL_COUNT_TODAY = "SELECT COUNT(taskId) FROM tasks WHERE completeStatus=0 && dueDate= DATE(NOW());";
    private static final String SQL_COUNT_THIS_WEEK = "SELECT COUNT(taskId) FROM tasks WHERE completeStatus=0 && dueDate= DATE(NOW() + 7);";
    private static final String SQL_COUNT_COMPLETE = "SELECT COUNT(taskId) FROM TASKS WHERE completeStatus=?";
    @Override
    public int insert(Task task) throws SQLException {
        // TODO: check to see if the task information is already there
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            // Fetching the values from the user object that we're passing to this method
            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setDate(2, task.getDueDate());
            preparedStatement.setString(3, task.getCategory());
            preparedStatement.setBoolean(4, task.getCompleteStatus());
            // executeQuery is a result set, executeUpdate is used for updating the database.
            // rs will return 0 if unsuccessful, 1 if successful
            rs = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException ex){
            System.err.println("Error:" + ex.getMessage());
        } catch (Exception genericException){
            System.err.println("Exception:" + genericException.getMessage());
        } finally {
            // will execute irrespective if anything happens, need to set connection to null
            // or can add in stuff for the logs here
            preparedStatement.close();
            conn.close();
        }
        return rs;
    }


    @Override
    public int update(Task task) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_UPDATE);

            preparedStatement.setString(1, task.getTaskName());
            preparedStatement.setDate(2, task.getDueDate());
            preparedStatement.setString(3, task.getCategory());
            preparedStatement.setBoolean(4, task.getCompleteStatus());
            preparedStatement.setInt(5, task.getTaskId());
            // executeQuery is a result set, executeUpdate is used for updating the database.
            // rs will return 0 if unsuccessful, 1 if successful
            rs = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException ex){
            System.err.println("Error:" + ex.getMessage());
        } catch (Exception genericException){
            System.err.println("Exception:" + genericException.getMessage());
        } finally {
            // will execute irrespective if anything happens, need to set connection to null
            // or can add in stuff for the logs here
            preparedStatement.close();
            conn.close();
        }
        return rs;
    }

    @Override
    public int updateCompletionStatus(int taskId) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_UPDATE_STATUS);

            preparedStatement.setBoolean(1, true);
            preparedStatement.setInt(2, taskId);
            // executeQuery is a result set, executeUpdate is used for updating the database.
            // rs will return 0 if unsuccessful, 1 if successful
            rs = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException ex){
            System.err.println("Error:" + ex.getMessage());
        } catch (Exception genericException){
            System.err.println("Exception:" + genericException.getMessage());
        } finally {
            // will execute irrespective if anything happens, need to set connection to null
            // or can add in stuff for the logs here
            preparedStatement.close();
            conn.close();
        }
        return rs;
    }

    @Override
    public int delete(int taskId) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_DELETE);

            preparedStatement.setInt(1, taskId);
            // executeQuery is a result set, executeUpdate is used for updating the database.
            // rs will return 0 if unsuccessful, 1 if successful
            rs = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException ex){
            System.err.println("Error:" + ex.getMessage());
        } catch (Exception genericException){
            System.err.println("Exception:" + genericException.getMessage());
        } finally {
            // will execute irrespective if anything happens, need to set connection to null
            // or can add in stuff for the logs here
            preparedStatement.close();
            conn.close();
        }
        return rs;
    }

    @Override
    public Task select(int taskId) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        Task task = new Task();
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT_ONE);
            preparedStatement.setInt(1, taskId);
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                task = new Task(
                        rs.getInt("taskId"),
                        rs.getString("taskName"),
                        rs.getDate("dueDate"),
                        rs.getString("category"),
                        rs.getBoolean("completeStatus")
                );

            }
        } catch (Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return task;
    }

    @Override
    public List<Task> select() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<Task> tasks = new ArrayList<>();

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                tasks.add(new Task(
                        rs.getInt("taskId"),
                        rs.getString("taskName"),
                        rs.getDate("dueDate"),
                        rs.getString("category"),
                        rs.getBoolean("completeStatus")
                ));
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasks;
    }

    @Override
    public int dueToday() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tasksDueToday = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_COUNT_TODAY);
            rs = preparedStatement.executeQuery();
            while(rs.next()){
                tasksDueToday = rs.getInt("COUNT(taskId)");
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasksDueToday;
    }
    @Override
    public int dueThisWeek() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tasksDueThisWeek = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_COUNT_THIS_WEEK);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                tasksDueThisWeek = rs.getInt("COUNT(taskId)");
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasksDueThisWeek;
    }
    @Override
    public int numCompleted() throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tasksCompleted = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_COUNT_COMPLETE);
            preparedStatement.setInt(1, 1);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                tasksCompleted = rs.getInt("COUNT(taskId)");
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasksCompleted;
    };
    @Override
    public int numNotCompleted() throws SQLException{
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        int tasksNotCompleted = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_COUNT_COMPLETE);
            preparedStatement.setInt(1, 0);
            rs = preparedStatement.executeQuery();
            while(rs.next()) {
                tasksNotCompleted = rs.getInt("COUNT(taskId)");
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasksNotCompleted;
    };
}
