<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 6:36 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-md navbar-dark"
     style="background-color: tomato">
    <div>
        <p class="navbar-brand"> Todo
            App</p>
    </div>

    <ul class="navbar-nav">
        <li><a href="<%=request.getContextPath()%>/list"
               class="nav-link">To Do List</a></li>
    </ul>

    <ul class="navbar-nav navbar-collapse justify-content-end">
        <li><a href="<%=request.getContextPath()%>/dashboard"
               class="nav-link">Dashboard</a></li>
    </ul>
</nav>