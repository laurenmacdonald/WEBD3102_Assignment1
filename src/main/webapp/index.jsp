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
<jsp:include page="navbar.jsp"/>
<h1><%= "Welcome to the to do list app" %></h1>
<br/>
<a href="hello-servlet">Hello Servlet</a>
<a href="form.jsp">Form</a>
<a href="list">List</a>
<a href="dashboard">Dashboard</a>

<jsp:include page="footer.jsp"/>
</body>
</html>