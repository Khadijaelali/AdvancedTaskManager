package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.TaskDao;
import model.Task;

@WebServlet({"/dashboard", "/addTask", "/editTask", "/deleteTask", "/logout"})
public class TaskServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private TaskDao taskDao;

    @Override
    public void init() {
        taskDao = new TaskDao(); // Initialisation du DAO
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/addTask":
                    showAddTaskForm(request, response);
                    break;
                case "/editTask":
                    showEditTaskForm(request, response);
                    break;
                case "/deleteTask":
                    deleteTask(request, response);
                    break;
                case "/logout":
                    logout(request, response);
                    break;
                default:
                    listTasks(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/addTask":
                    addTask(request, response);
                    break;
                case "/editTask":
                    updateTask(request, response);
                    break;
                default:
                    response.sendRedirect("dashboard");
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Afficher le formulaire d'ajout de tâche
    private void showAddTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/add-task.jsp");
        dispatcher.forward(request, response);
    }

    // Ajouter une tâche
    private void addTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("views/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");

        Task task = new Task(0, title, description, status, userId);
        taskDao.addTask(task);
        response.sendRedirect("dashboard");
    }

    // Afficher le formulaire de modification de tâche
    private void showEditTaskForm(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        Task existingTask = taskDao.getTaskById(id);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/edit-task.jsp");
        request.setAttribute("task", existingTask);
        dispatcher.forward(request, response);
    }

    // Mettre à jour une tâche
    private void updateTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        String title = request.getParameter("title");
        String description = request.getParameter("description");
        String status = request.getParameter("status");
        int userId = Integer.parseInt(request.getParameter("userId"));

        Task task = new Task(id, title, description, status, userId);
        taskDao.updateTask(task);
        response.sendRedirect("dashboard");
    }

    // Supprimer une tâche
    private void deleteTask(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id = Integer.parseInt(request.getParameter("id"));
        taskDao.deleteTask(id);
        response.sendRedirect("dashboard");
    }

    // Lister les tâches d'un utilisateur
    private void listTasks(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);

        if (session == null || session.getAttribute("userId") == null) {
            response.sendRedirect("views/login.jsp");
            return;
        }

        int userId = (int) session.getAttribute("userId");

        List<Task> tasks = taskDao.getTasksByUserId(userId);
        request.setAttribute("tasks", tasks);

        RequestDispatcher dispatcher = request.getRequestDispatcher("views/dashboard.jsp");
        dispatcher.forward(request, response);
    }

    // Méthode de déconnexion
    private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
        HttpSession session = request.getSession(false); // Récupère la session existante sans en créer une nouvelle
        if (session != null) {
            session.invalidate();  // Invalide la session
        }
        response.sendRedirect("views/login.jsp"); // Redirection vers la page de login
    }
}
