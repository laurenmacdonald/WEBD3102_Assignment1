<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 7:12 p.m.
  To change this template use File | Settings | File Templates.
--%>
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
<div class="container-sm p-5 min-vh-100">
    <h1 class="heading1 mb-2">Dashboard</h1>
    <div class="row row-cols-1 row-cols-md-2 g-4">
        <div class="col">
            <div class="card text-bg-primary mb-3" style="max-width: 18rem;">
                <div class="card-header">Today</div>
                <div class="card-body">
                    <h5 class="card-title">Tasks Due Today</h5>
                    <p class="card-text">You have ${tasksDueToday} tasks due today. You got this!</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card text-bg-primary mb-3" style="max-width: 18rem;">
                <div class="card-header">Next 7 Days</div>
                <div class="card-body">
                    <h5 class="card-title">Tasks Due This Week</h5>
                    <p class="card-text">You have ${tasksDueThisWeek} tasks in the next 7 days. You got this!</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card text-bg-primary mb-3" style="max-width: 18rem;">
                <div class="card-header">Completed</div>
                <div class="card-body">
                    <h5 class="card-title">Total Tasks Completed</h5>
                    <p class="card-text">You have completed ${numCompleted} tasks so far. Good job!</p>
                </div>
            </div>
        </div>
        <div class="col">
            <div class="card text-bg-primary mb-3" style="max-width: 18rem;">
                <div class="card-header">On the Horizon</div>
                <div class="card-body">
                    <h5 class="card-title">Total Tasks Left</h5>
                    <p class="card-text">You have ${numNotCompleted} left on your list. You got this!</p>
                </div>
            </div>
        </div>
    </div>
</div>
<jsp:include page="footer.jsp"/>
</body>
</html>
