<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 9:14 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<link rel="stylesheet"
      href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
      integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
      crossorigin="anonymous">

<html>
<head>
    <title>Using GET Method to Read Form Data</title>
</head>

<body>
<div class="container col-md-5">
    <div class="card">
        <div class="card-body">
            <c:if test="${task != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${task == null}">
                <form action="insert" method="post">
                    </c:if>

                    <caption>
                        <h2>
                            <c:if test="${task != null}">
                                Edit Task
                            </c:if>
                            <c:if test="${task == null}">
                                Add New Task
                            </c:if>
                        </h2>
                    </caption>

                    <c:if test="${task != null}">
                        <input type="hidden" name="taskId" value="<c:out value='${task.taskId}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label>Task Title</label> <input type="text"
                                                         value="<c:out value='${task.taskName}' />" class="form-control"
                                                         name="taskName" required="required" minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Task Category</label> <input type="text"
                                                            value="<c:out value='${task.category}' />"
                                                            class="form-control"
                                                            name="category" minlength="5">
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Task Status</label> <select class="form-control"
                                                           name="completeStatus">
                        <option value="false">In Progress</option>
                        <option value="true">Complete</option>
                    </select>
                    </fieldset>

                    <fieldset class="form-group">
                        <label>Task Due Date</label> <input type="date"
                                                            value="<c:out value='${task.dueDate}' />"
                                                            class="form-control"
                                                            name="dueDate" required="required">
                    </fieldset>

                    <button type="submit" class="btn btn-success">Save</button>
                </form>
        </div>
    </div>
</div>
</body>
</html>
