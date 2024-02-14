package com.example.webd3102_assignment1.database;

import com.example.webd3102_assignment1.dao.UserDAO;
import com.example.webd3102_assignment1.model.User;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import static com.example.webd3102_assignment1.database.MySQLConnection.getConnection;

public class UserDatabase implements UserDAO {
    private static final String SQL_SELECT = "SELECT userId, userName, userCity FROM USERS";
    private static final String SQL_SELECT_ONE = "SELECT userId, userName, userCity FROM USERS WHERE userId =?";
    private static final String SQL_INSERT = "INSERT INTO(userId, userName, userCity) VALUES (?,?,?)";
    private static final String SQL_UPDATE = "UPDATE USERS SET userName=?, userCity=? WHERE userId = ?";
    private static final String SQL_DELETE = "DELETE FROM USERS WHERE userId=?";


    @Override
    public int insert(User user) throws SQLException {
        // check to see if the user information is already there
        if(user.getUserName() == null || user.getUserCity() == null){
            return 0;
        }
        // sometimes better to have local connections rather than global in case there are multiple
        // connections being used at the same time.
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_INSERT);
            // Fetching the values from the user object that we're passing to this method
            preparedStatement.setInt(1, user.getUserId());
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserCity());
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
    public int update(User user) throws SQLException {
        if(user.getUserName() == null || user.getUserCity() == null){
            return 0;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_UPDATE);
            // update user city, username
            preparedStatement.setString(2, user.getUserName());
            preparedStatement.setString(3, user.getUserCity());
            // where id = ?
            preparedStatement.setInt(1, user.getUserId());

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
    public int delete(int userId) throws SQLException {
        if(userId == 0){
            return 0;
        }
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        int rs = 0;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_DELETE);
            preparedStatement.setInt(1, userId);
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
    public int select(int userId) throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        User user;

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT);
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                user = new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userCity")
                );

            }
        } catch (Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return 0;
    }

    @Override
    public List<User> select() throws SQLException {
        Connection conn = null;
        PreparedStatement preparedStatement = null;
        ResultSet rs = null;
        List<User> users = new ArrayList<>();

        try{
            conn = getConnection();
            preparedStatement = conn.prepareStatement(SQL_SELECT);
            rs = preparedStatement.executeQuery();
            while(rs.next()){

                users.add(new User(
                        rs.getInt("userId"),
                        rs.getString("userName"),
                        rs.getString("userCity")
                ));

            }
        } catch (Exception ex){
            System.out.println("Error:" + ex.getMessage());
        }
        return users;
    }
}
