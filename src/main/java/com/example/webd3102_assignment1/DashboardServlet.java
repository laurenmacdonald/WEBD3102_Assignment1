package com.example.webd3102_assignment1;

import com.example.webd3102_assignment1.database.TaskDatabase;
import com.example.webd3102_assignment1.model.Task;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;

public class DashboardServlet extends HttpServlet {
    private TaskDatabase taskDatabase;
    public void init() {
        taskDatabase = new TaskDatabase();
    }
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        try {
            dashboardData(request, response);
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
    protected void dashboardData(HttpServletRequest request, HttpServletResponse response) throws ServletException, SQLException, IOException {
        int tasksDueToday = taskDatabase.dueToday();
        int tasksDueThisWeek = taskDatabase.dueThisWeek();
        int numCompleted = taskDatabase.numCompleted();
        int numNotCompleted = taskDatabase.numNotCompleted();
        request.setAttribute("tasksDueToday", tasksDueToday);
        request.setAttribute("tasksDueThisWeek", tasksDueThisWeek);
        request.setAttribute("numCompleted", numCompleted);
        request.setAttribute("numNotCompleted", numNotCompleted);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/dashboard.jsp");
        dispatcher.forward(request, response);
    }
}
