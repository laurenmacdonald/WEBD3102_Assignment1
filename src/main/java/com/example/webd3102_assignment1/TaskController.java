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
import java.sql.Date;
import java.sql.SQLException;
import java.util.ArrayList;
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
                    listsByDue(request,response);
                    break;
                case "/updateComplete":
                    updateToComplete(request, response);
                    break;
                case "/updateIncomplete":
                    updateToIncomplete(request, response);
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
    private void listsByDue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        List<Task> totalTasks = taskDatabase.selectDueRelative();
        List<List<Task>> taskLists = new ArrayList<>();
        List<Task> dueToday = new ArrayList<>();
        List<Task> dueTomorrow = new ArrayList<>();
        List<Task> due2Days = new ArrayList<>();
        List<Task> due3Days = new ArrayList<>();
        List<Task> due4Days = new ArrayList<>();
        List<Task> due5Days = new ArrayList<>();
        List<Task> due6Days = new ArrayList<>();
        List<Task> due7Days = new ArrayList<>();
        List<Task> overdue = new ArrayList<>();

        for (Task task : totalTasks) {
            String dueDateRelative = task.getDueDateRelative();
            switch (dueDateRelative) {
                case "Today":
                    dueToday.add(task);
                    break;
                case "Tomorrow":
                    dueTomorrow.add(task);
                    break;
                case "2 Days":
                    due2Days.add(task);
                    break;
                case "3 Days":
                    due3Days.add(task);
                    break;
                case "4 Days":
                    due4Days.add(task);
                    break;
                case "5 Days":
                    due5Days.add(task);
                    break;
                case "6 Days":
                    due6Days.add(task);
                    break;
                case "7 Days":
                    due7Days.add(task);
                    break;
                case "Overdue":
                    overdue.add(task);
                    break;
                default:

                    break;
            }
        }
        taskLists.add(dueToday);
        taskLists.add(dueTomorrow);
        taskLists.add(due2Days);
        taskLists.add(due3Days);
        taskLists.add(due4Days);
        taskLists.add(due5Days);
        taskLists.add(due6Days);
        taskLists.add(due7Days);
        taskLists.add(overdue);

        request.setAttribute("dueToday", dueToday);
        request.setAttribute("dueTomorrow", dueTomorrow);
        request.setAttribute("due2Days", due2Days);
        request.setAttribute("due3Days", due3Days);
        request.setAttribute("due4Days", due4Days);
        request.setAttribute("due5Days", due5Days);
        request.setAttribute("due6Days", due6Days);
        request.setAttribute("due7Days", due7Days);
        request.setAttribute("overdue", overdue);
        request.setAttribute("taskLists", taskLists);
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

    private void updateToComplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.updateToComplete(taskId);
        response.sendRedirect("list");
    }
    private void updateToIncomplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.updateToIncomplete(taskId);
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
