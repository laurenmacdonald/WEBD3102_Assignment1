<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ page import="com.example.webd3102_assignment1.database.UserDatabase" %>
<%@ page import="com.example.webd3102_assignment1.model.User" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.webd3102_assignment1.database.TaskDatabase" %>
<%@ page import="com.example.webd3102_assignment1.model.Task" %>
<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
  <title>JSP To Do List App</title>
  <link rel="stylesheet"
        href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
        integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
        crossorigin="anonymous">
</head>
<body>
<c:redirect url="/dashboard"/>
</body>
</html>