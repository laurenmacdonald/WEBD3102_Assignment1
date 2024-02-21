package com.example.webd3102_assignment1.database;

import com.example.webd3102_assignment1.dao.TasksDAO;
import com.example.webd3102_assignment1.util.DueDateDefiner;
import com.example.webd3102_assignment1.model.Task;

import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import static com.example.webd3102_assignment1.database.MySQLConnection.getConnection;

public class TaskDatabase implements TasksDAO {

    //DueDateDefiner dueDateDefiner = new DueDateDefiner();
    private static final String SQL_SELECT = "SELECT taskId, taskName, dueDate, category, completeStatus FROM TASKS ORDER BY dueDate";
    private static final String SQL_SELECT_ONE = "SELECT taskId, taskName, dueDate, category, completeStatus FROM TASKS WHERE taskId=?";
    private static final String SQL_SELECT_BY_DUE = "SELECT taskId, taskName, dueDate, category, completeStatus FROM TASKS WHERE dueDateRelative =?";
    private static final String SQL_INSERT = "INSERT INTO TASKS(taskName, dueDate, category, completeStatus) VALUES (?, ?, ?, ?)";
    private static final String SQL_UPDATE = "UPDATE TASKS SET taskName=?, dueDate=?, category=?, completeStatus=? WHERE taskId=?";
    private static final String SQL_UPDATE_STATUS = "UPDATE TASKS SET completeStatus=? WHERE taskId=?";
    private static final String SQL_DELETE = "DELETE FROM TASKS WHERE taskID=?";
    private static final String SQL_UPDATE_RELATIVE_DUE_DATE = "UPDATE tasks SET dueDateRelative = CASE WHEN dueDate < CURDATE() THEN 'Overdue' WHEN dueDate = CURDATE() THEN 'Today' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 1 DAY) THEN 'Tomorrow' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 2 DAY) THEN '2 Days' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 3 DAY) THEN '3 Days' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 4 DAY) THEN '4 Days' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 5 DAY) THEN '5 Days' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 6 DAY) THEN '6 Days' WHEN dueDate = DATE_ADD(CURDATE(), INTERVAL 7 DAY) THEN '7 Days' ELSE CONCAT(DATEDIFF(dueDate, CURDATE()), ' days') END;";
    @Override
    public int insert(Task task) throws SQLException {
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
            preparedStatement = conn.prepareStatement(SQL_UPDATE_RELATIVE_DUE_DATE);
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
    public int updateToComplete(int taskId) throws SQLException {
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
            preparedStatement.close();
            conn.close();
        }
        return rs;
    }
    @Override
    public int updateToIncomplete(int taskId) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;
        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_UPDATE_STATUS);

            preparedStatement.setBoolean(1, false);
            preparedStatement.setInt(2, taskId);
            // executeQuery is a result set, executeUpdate is used for updating the database.
            // rs will return 0 if unsuccessful, 1 if successful
            rs = preparedStatement.executeUpdate();
        } catch (SQLSyntaxErrorException ex){
            System.err.println("Error:" + ex.getMessage());
        } catch (Exception genericException){
            System.err.println("Exception:" + genericException.getMessage());
        } finally {
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
                        rs.getBoolean("completeStatus"));
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
            for(int i=0; i< tasks.size(); i++){
                LocalDate localDate = tasks.get(i).getDueDate().toLocalDate();
                String dueDateRelative = DueDateDefiner.defineRelativeDueDate(localDate, tasks.get(i).getCompleteStatus());
                tasks.get(i).setDueDateRelative(dueDateRelative);
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasks;
    }

    @Override
    public List<Task> selectDueRelative() throws SQLException {
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
            for (Task task : tasks) {
                LocalDate localDate = task.getDueDate().toLocalDate();
                String dueDateRelative = DueDateDefiner.defineRelativeDueDate(localDate, task.getCompleteStatus());
                String dayOfWeek = DueDateDefiner.defineDayOfWeek(localDate, Locale.CANADA);
                task.setDueDateRelative(dueDateRelative);
                task.setDayOfWeek(dayOfWeek);
            }
        } catch (Exception ex){
            System.out.println("Error: " + ex.getMessage());
        }
        return tasks;
    }

}
