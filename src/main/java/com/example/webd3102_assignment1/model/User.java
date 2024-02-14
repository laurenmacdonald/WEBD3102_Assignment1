package com.example.webd3102_assignment1.model;

public class User {

    private int userId;
    private String userName;
    private String userCity;

    public User(String userName, String userCity) {
        this.userName = userName;
        this.userCity = userCity;
    }

    public User(int userId, String userName, String userCity) {
        this.userId = userId;
        this.userName = userName;
        this.userCity = userCity;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getUserCity() {
        return userCity;
    }

    public void setUserCity(String userCity) {
        this.userCity = userCity;
    }

    @Override
    public String toString() {
        return "User{" +
                "userId=" + userId +
                ", userName='" + userName + '\'' +
                ", userCity='" + userCity + '\'' +
                '}';
    }
}
