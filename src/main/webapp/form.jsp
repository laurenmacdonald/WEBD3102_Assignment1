<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 9:14 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To Do List</title>
    <link rel="stylesheet"
          href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
          integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
          crossorigin="anonymous">
    <style><%@include file="styles.css"%></style>
</head>
<body class="form-body">
<jsp:include page="navbar.jsp"/>
<div class="container col-md-5 min-vh-100">
    <div class="card m-4">
        <div class="card-body">
            <%-- If task is not null, then edit logic, else add new task --%>
            <c:if test="${task != null}">
            <form action="update" method="post">
                </c:if>
                <c:if test="${task == null}">
                <form action="insert" method="post">
                    </c:if>
                    <caption>
                        <h1>
                            <c:if test="${task != null}">
                                Edit Task
                            </c:if>
                            <c:if test="${task == null}">
                                Add New Task
                            </c:if>
                        </h1>
                    </caption>
                    <c:if test="${task != null}">
                        <input type="hidden" name="taskId" value="<c:out value='${task.taskId}' />"/>
                    </c:if>

                    <fieldset class="form-group">
                        <label for="taskName">Task Name</label>
                            <input type="text" value="<c:out value='${task.taskName}' />" class="form-control"
                                   name="taskName" required="required" minlength="5" id="taskName">
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="taskPriority">Priority</label>
                            <select class="form-control" name="category" id="taskPriority">
                                <option value="high">high</option>
                                <option value="medium">medium</option>
                                <option value="low">low</option>
                            </select>
                    </fieldset>
                    <fieldset class="form-group">
                        <label for="taskDueDate">
                            Task Due Date
                        </label>
                            <input type="date"
                                   value="<c:out value='${task.dueDate}'/>"
                                   class="form-control"
                                   name="dueDate" required="required"
                            id="taskDueDate">
                    </fieldset>
                    <button type="submit" class="btn btn-primary">Save</button>
                </form>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
