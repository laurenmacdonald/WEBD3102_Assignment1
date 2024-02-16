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
    <title>To Do List</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.2/dist/css/bootstrap.min.css" rel="stylesheet"
          integrity="sha384-T3c6CoIi6uLrA9TneNEoa7RxnatzjcDSCmG1MXxSR1GAsXEV/Dwwykc2MPK8M2HN" crossorigin="anonymous">
    <link href="https://cdn.jsdelivr.net/npm/bootstrap-icons@1.3.0/font/bootstrap-icons.css" rel="stylesheet">

</head>
<body>
<jsp:include page="navbar.jsp"/>
<div class="container justify-content-center p-5 min-vh-100">
    <h3 class="heading3">Your To Do List</h3>
    <a href="<%=request.getContextPath()%>/new" class="btn btn-outline-primary mb-3">Add Task</a>
    <div class="row row-cols-2 row-cols-lg-3 g-2 g-lg-3">
        <c:forEach var="task" items="${taskList}">
            <div class="col">
                <div class="card" style="max-width: 18rem; border-radius: 30px">
                    <div class="card-body">
                        <div class="row">
                            <h5 class="card-title my-2 text-body-primary"><c:out value="${task.taskName}"/></h5>
                        </div>
                        <div class="row">
                            <div class="col-5">
                                <c:choose>
                                    <c:when test="${task.completeStatus == true}">
                                        <p class="badge rounded-pill text-bg-success">
                                            <i class="bi bi-check-circle"></i> Complete
                                        </p>
                                    </c:when>
                                    <c:otherwise>
                                        <p class="badge rounded-pill text-bg-warning"><a
                                                class="text-dark text-decoration-none"
                                                href="updateComplete?taskId=<c:out value='${task.taskId}' />">
                                            <i class="bi bi-circle"></i>
                                            Incomplete
                                        </a>
                                        </p>
                                    </c:otherwise>
                                </c:choose>
                            </div>
                            <div class="col-7">
                                <c:choose>
                                    <c:when test="${task.category == 'high'}">
                                        <p class="badge rounded-pill bg-danger-subtle text-dark">
                                            <c:out value='${task.category}'/>
                                        </p>
                                    </c:when>
                                    <c:when test="${task.category == 'medium'}">
                                        <p class="badge rounded-pill bg-warning-subtle text-dark">
                                            <c:out value='${task.category}'/>
                                        </p>
                                    </c:when>
                                    <c:when test="${task.category == 'low'}">
                                        <p class="badge rounded-pill bg-success-subtle text-dark">
                                            <c:out value='${task.category}'/>
                                        </p>
                                    </c:when>
                                </c:choose>
                            </div>
                        </div>
                        <div class="row">
                            <div class="col-8">
                                <small class="card-text pt-3">
                                    <i class="bi bi-calendar-event"></i>
                                    <c:out value="${task.dueDate}"/>
                                </small>
                            </div>
                            <div class="col-2">
                                <p class="btn badge rounded-pill bg-info-subtle text-dark"><a class="text-dark"
                                                                                              href="edit?taskId=<c:out value='${task.taskId}' />">
                                    <i class="bi bi-pencil-square"></i>
                                </a>
                                </p>
                            </div>
                            <div class="col-2">
                                <p class="btn badge rounded-pill bg-danger-subtle"><a class="text-dark"
                                                                                                href="delete?taskId=<c:out value='${task.taskId}' />">
                                    <i class="bi bi-trash"></i>
                                </a>
                                </p>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>