<%--
  Created by IntelliJ IDEA.
  User: laure
  Date: 2024-02-14
  Time: 6:36 p.m.
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<nav class="navbar navbar-expand-lg navbar-dark"
     style="background-color: darkcyan">
    <div class="container-fluid">
        <a class="navbar-brand" href="#">To Do App</a>
        <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNavAltMarkup" aria-controls="navbarNavAltMarkup" aria-expanded="false" aria-label="Toggle navigation">
            <span class="navbar-toggler-icon"></span>
        </button>
        <div class="collapse navbar-collapse" id="navbarNavAltMarkup">
            <div class="navbar-nav">
                <a class="nav-link" aria-current="page" href="<%=request.getContextPath()%>/list">Your To Do List</a>
                <a class="nav-link" href="<%=request.getContextPath()%>/dashboard">Dashboard</a>
                <a class="nav-link" href="#">Login</a>
            </div>
        </div>
    </div>
</nav>