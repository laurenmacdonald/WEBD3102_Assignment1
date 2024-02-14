package com.example.webd3102_assignment1;

import com.example.webd3102_assignment1.dao.TasksDAO;
import com.example.webd3102_assignment1.database.TaskDatabase;
import com.example.webd3102_assignment1.model.Task;
import jakarta.servlet.RequestDispatcher;
import jakarta.servlet.ServletException;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;

import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.util.List;

/**
 * Controller servlet controls the pages for the web app and handles all requests from the tasks.
 */

@WebServlet("/")
public class TaskController extends HttpServlet {
    private TaskDatabase taskDatabase;

    public void init() {
        taskDatabase = new TaskDatabase();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        doGet(request, response);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/new":
                    addTaskForm(request, response);
                    break;
                case "/insert":
                    insertTask(request, response);
                    break;
                case "/delete":
                    deleteTask(request, response);
                    break;
                case "/edit":
                    editTaskForm(request, response);
                    break;
                case "/update":
                    updateTask(request, response);
                    break;
                case "/list":
                    listTasks(request, response);
                    break;
                case "/updateComplete":
                    updateCompletion(request, response);
                    break;
                default:
                    RequestDispatcher dispatcher = request.getRequestDispatcher("/index.jsp");
                    dispatcher.forward(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listTasks(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Task> taskList = taskDatabase.select();
        request.setAttribute("taskList", taskList);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }

    private void addTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
        dispatcher.forward(request, response);
    }

    private void editTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        System.out.println("taskId param: " + taskId);
        Task taskToEdit = taskDatabase.select(taskId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
        request.setAttribute("task", taskToEdit);
        dispatcher.forward(request, response);
    }

    private void insertTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        String taskName = request.getParameter("taskName");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        String category = request.getParameter("category");
        boolean completeStatus = Boolean.parseBoolean(request.getParameter("completeStatus"));

        Task newTask = new Task(taskName, dueDate, category, completeStatus);
        taskDatabase.insert(newTask);
        response.sendRedirect("list");
    }

    private void updateCompletion(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.updateCompletionStatus(taskId);
        response.sendRedirect("list");
    }
    private void updateTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        String taskName = request.getParameter("taskName");
        Date dueDate = Date.valueOf(request.getParameter("dueDate"));
        String category = request.getParameter("category");
        boolean completeStatus = Boolean.parseBoolean(request.getParameter("completeStatus"));

        Task updateTask = new Task(taskId, taskName, dueDate, category, completeStatus);
        taskDatabase.update(updateTask);
        response.sendRedirect("list");
    }

    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.delete(taskId);
        response.sendRedirect("list");
    }
}
