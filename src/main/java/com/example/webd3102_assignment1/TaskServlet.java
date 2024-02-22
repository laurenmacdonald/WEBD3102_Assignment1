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
public class TaskServlet extends HttpServlet {
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
            // switch statement for servlet path, when user goes to the url, the method will be called.
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

    /**
     *
     * @param request
     * @param response
     * @throws ServletException
     * @throws IOException
     * @throws SQLException
     * <br>
     * Gets list of all tasks from database connection. Divides this list into separate lists dependant on relative due date.
     * Then all lists are added to a list of lists to be iterated through in the jsp file.
     */
    private void listsByDue(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // get data
        List<Task> totalTasks = taskDatabase.selectDueRelative();
        // initialize list to hold all lists of tasks
        List<List<Task>> taskLists = new ArrayList<>();
        // initialize lists of tasks by relative due date
        List<Task> dueToday = new ArrayList<>();
        List<Task> dueTomorrow = new ArrayList<>();
        List<Task> due2Days = new ArrayList<>();
        List<Task> due3Days = new ArrayList<>();
        List<Task> due4Days = new ArrayList<>();
        List<Task> due5Days = new ArrayList<>();
        List<Task> due6Days = new ArrayList<>();
        List<Task> due7Days = new ArrayList<>();
        List<Task> overdue = new ArrayList<>();

        // for loop to iterate through total tasks list and assign tasks to appropriate relative due date lists via switch statement
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
        // Add lists to list of lists
        taskLists.add(dueToday);
        taskLists.add(dueTomorrow);
        taskLists.add(due2Days);
        taskLists.add(due3Days);
        taskLists.add(due4Days);
        taskLists.add(due5Days);
        taskLists.add(due6Days);
        taskLists.add(due7Days);
        taskLists.add(overdue);

        // Set the attributes that are associated with the request (lists of tasks and list of lists)
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
        // forward to list page (reload)
        RequestDispatcher dispatcher = request.getRequestDispatcher("/list.jsp");
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to redirect to form page when requested.
     */
    private void addTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to redirect to the form page when requested, but takes the request parameter of taskId provided in url
     * to provide to the select statement, pre-loading the applicable task information to the form.
     */
    private void editTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        System.out.println("taskId param: " + taskId);
        Task taskToEdit = taskDatabase.select(taskId);
        RequestDispatcher dispatcher = request.getRequestDispatcher("/form.jsp");
        request.setAttribute("task", taskToEdit);
        dispatcher.forward(request, response);
    }

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to insert new record into task table. Takes parameters from request (form submission), creates new task
     * object and supplies it as the parameter to the insert method from the database class.
     */
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

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to update the task status to complete. Takes id parameter from request.
     */
    private void updateToComplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.updateToComplete(taskId);
        response.sendRedirect("list");
    }

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to update task status to incomplete. Takes id parameter from request.
     */
    private void updateToIncomplete(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.updateToIncomplete(taskId);
        response.sendRedirect("list");
    }

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <br>
     * Method to update entire task information when edited.
     */
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

    /**
     *
     * @param request
     * @param response
     * @throws SQLException
     * @throws ServletException
     * @throws IOException
     * <Br>
     * Method to delete a task from the database.
     */
    private void deleteTask(HttpServletRequest request, HttpServletResponse response) throws SQLException, ServletException, IOException {
        int taskId = Integer.parseInt(request.getParameter("taskId"));
        taskDatabase.delete(taskId);
        response.sendRedirect("list");
    }
}
