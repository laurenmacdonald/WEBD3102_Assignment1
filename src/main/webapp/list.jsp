<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 10:39 a.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>To Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" rel="stylesheet">
    <link href="styles.css" rel="stylesheet" type="text/css">
    <style><%@include file="styles.css"%></style>
</head>
<body>
<jsp:include page="navbar.jsp"/>

<div class="container justify-content-center p-5 min-vh-100">
    <h1 class="heading1">Next 7 Days</h1>
    <div class="row row-cols-1 row-cols-lg-4 g-2 g-lg-3">
        <%--Iterate through list of lists, when the list is not null and not empty, create a card for each list (day)--%>
        <c:forEach var="list" items="${taskLists}">
            <jsp:useBean id="dueDate" class="java.util.Date" />
            <fmt:formatDate value="${list[0].dueDate}" type="date" var="formattedDueDate"/>
            <c:choose>
                <c:when test="${list!=null && !list.isEmpty()}">
                    <div class="col">
                        <div class="day-card card">
                            <div class="card-body">
                                    <%-- Display day of the week (Monday, Friday, etc.), unless 'Today', 'Tomorrow' or 'Overdue' --%>
                                <c:choose>
                                    <c:when test="${list[0].dueDateRelative == 'Today' || list[0].dueDateRelative == 'Tomorrow' || list[0].dueDateRelative == 'Overdue'}">
                                        <h2 class="card-title ms-2">${list[0].dueDateRelative}</h2>
                                    </c:when>
                                    <c:otherwise>
                                        <h2 class="card-title ms-2">${list[0].dayOfWeek}</h2>
                                    </c:otherwise>
                                </c:choose>
                                    <%-- If the task is not overdue, display the date for the card.--%>
                                <c:if test="${list[0].dueDateRelative!='Overdue'}">
                                    <h3 class="card-subtitle ms-2 text-body-secondary">${formattedDueDate}</h3>
                                </c:if>
                                    <%-- Iterate through each task in the list to display on the card. If the task is overdue, display the date above each task. --%>
                                <c:forEach var="taskItem" items="${list}">
                                    <jsp:useBean id="dueDate2" class="java.util.Date" />
                                    <fmt:formatDate value="${taskItem.dueDate}" type="date" var="taskFormattedDueDate"/>
                                    <c:if test="${list[0].dueDateRelative =='Overdue'}">
                                        <h3 class="card-subtitle ms-2 text-body-secondary">${taskFormattedDueDate}</h3>
                                    </c:if>
                                    <div class="card p-1 m-2">
                                        <div class="row">
                                            <div class="col-2">
                                                    <%-- Checkbox logic for task completion - will change the status from true if checked --%>
                                                    <%-- JQuery logic at bottom of page --%>
                                                <c:choose>
                                                    <c:when test="${taskItem.completeStatus == true}">
                                                        <input class="form-check-input" type="checkbox"
                                                               id="taskCheckbox_${taskItem.taskId}"
                                                               onclick="updateTaskStatus('${taskItem.taskId}', this)"
                                                               checked>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <input class="form-check-input" type="checkbox"
                                                               id="taskCheckbox_${taskItem.taskId}"
                                                               onclick="updateTaskStatus('${taskItem.taskId}', this)">
                                                    </c:otherwise>
                                                </c:choose>
                                            </div>
                                            <div class="col-7">
                                                <label for="taskCheckbox_${taskItem.taskId}"><c:out
                                                        value="${taskItem.taskName}"/></label>
                                            </div>
                                                <%-- Actions delete and edit --%>
                                            <div class="col-3">
                                                <a class="text-dark"
                                                   href="delete?taskId=<c:out value='${taskItem.taskId}' />"><i
                                                        class="bi bi-trash" aria-hidden="true"></i><span
                                                        class="visually-hidden">Delete</span></a>
                                                <a class="text-dark"
                                                   href="edit?taskId=<c:out value='${taskItem.taskId}' />">
                                                    <i class="bi bi-pencil-square" aria-hidden="true"></i>
                                                    <span class="visually-hidden">Edit</span></a>
                                            </div>
                                        </div>
                                    </div>
                                </c:forEach>
                                    <%-- Add new task link --%>
                                <a href="new" class="card add-task-card p-1 m-2 link-underline link-underline-opacity-0">
                                    <div class="row">
                                        <div class="col-2">
                                            <i class="bi bi-plus-square m-1" aria-hidden="true"></i>
                                        </div>
                                        <div class="col-10">
                                            Add task
                                        </div>
                                    </div>
                                </a>
                            </div>
                        </div>
                    </div>
                </c:when>
            </c:choose>
        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp"/>

<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script type="text/javascript">
<%-- JQuery function to update completion status depending on whether checkbox is clicked. --%>
    function updateTaskStatus(taskId, checkbox) {
        // Get the checked status of the checkbox
        let checked = checkbox.checked;
        let completionStatus = !!checked;
        let url = completionStatus ? '<%=request.getContextPath()%>/updateComplete' : '<%=request.getContextPath()%>/updateIncomplete';
        console.log(url);

        // AJAX request to update the completion status
        $.ajax({
            url: url,
            type: 'POST',
            data: {taskId: taskId},
            success: function (response) {
                console.log("Task status updated successfully");
            },
            error: function (xhr, status, error) {
                console.error("Error updating task status:", error);
            }
        });
    }
</script>
</body>
</html>