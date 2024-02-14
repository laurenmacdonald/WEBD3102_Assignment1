<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 10:39 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
</head>
<body>

<div class="row">
    <div class="container">
        <h3 class="text-center">List of Tasks</h3>
        <hr>
        <div class="container text-left">
            <a href="<%=request.getContextPath()%>/new"
               class="btn btn-success">Add Task</a>
        </div>
        <br>
        <table class="table table-bordered">
            <thead>
            <tr>
                <th>Task</th>
                <th>Due Date</th>
                <th>Category</th>
                <th>Completion Status</th>
                <th>Actions</th>
            </tr>
            </thead>
            <tbody>
            <c:forEach var="task" items="${taskList}">

                <tr>
                    <td><c:out value="${task.taskName}"/></td>
                    <td><c:out value="${task.dueDate}"/></td>
                    <td><c:out value="${task.category}"/></td>
                    <td><c:if test="${task.completeStatus == true}">
                        Complete!
                    </c:if>
                        <c:if test="${task.completeStatus == false}">
                            <a href="updateComplete?taskId=<c:out value='${task.taskId}' />">Mark Complete</a>
                        </c:if></td>

                    <td><a href="edit?taskId=<c:out value='${task.taskId}' />">Edit</a>
                        <br> <a
                                href="delete?taskId=<c:out value='${task.taskId}' />">Delete</a>
                    </td>
                </tr>
            </c:forEach>
            </tbody>
        </table>
    </div>
</div>
</body>
</html>
