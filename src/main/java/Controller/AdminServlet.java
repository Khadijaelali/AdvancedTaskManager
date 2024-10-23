package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import dao.UserDao;
import dao.TaskDao;
import model.User;
import utils.PasswordUtils;

@WebServlet({"/admin/dashboard", "/admin/addUser", "/admin/deleteUser"})
public class AdminServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private TaskDao taskDao;


    @Override
    public void init() {
        userDao = new UserDao(); // Initialisation du DAO
        taskDao = new TaskDao();  // Initialisation du TaskDao

    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getServletPath();

        try {
            switch (action) {
                case "/admin/addUser":
                    showAddUserForm(request, response);
                    break;
                case "/admin/deleteUser":
                    deleteUser(request, response);
                    break;
                default:
                    listUsers(request, response);
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
                case "/admin/addUser":
                    addUser(request, response);
                    break;
                default:
                    listUsers(request, response);
                    break;
            }
        } catch (SQLException e) {
            throw new ServletException(e);
        }
    }

    // Affiche le formulaire d'ajout d'utilisateur
    private void showAddUserForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/add-user.jsp");
        dispatcher.forward(request, response);
    }

    // Ajouter un utilisateur dans la base de données
    private void addUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String username = request.getParameter("username");
        String email = request.getParameter("email");
        String password = request.getParameter("password");
        String role = request.getParameter("role");

        // Hashage du mot de passe
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Créer l'utilisateur
        User newUser = new User(0, username, hashedPassword, email, role);

        // Ajouter l'utilisateur à la base de données
        userDao.addUser(newUser);

        // Redirection vers le tableau de bord
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }

    // Supprimer un utilisateur
    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int userId = Integer.parseInt(request.getParameter("id"));
        userDao.deleteUser(userId);
        response.sendRedirect(request.getContextPath() + "/admin/dashboard");
    }

    // Lister les utilisateurs
    private void listUsers(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        HttpSession session = request.getSession(false);

        // Vérification du rôle d'admin
        if (session == null || !"admin".equals(session.getAttribute("role"))) {
            response.sendRedirect(request.getContextPath() + "/views/login.jsp");  // Redirection si non admin
            return;
        }

        // Récupération des statistiques
        int totalUsers = userDao.getTotalUsers();
        int totalTasks = taskDao.getTotalTasks();
        int completedTasks = taskDao.getCompletedTasks();
        int inProgressTasks = taskDao.getInProgressTasks();  // Ajout de la méthode

        // Debugging: Vérifier les valeurs
        System.out.println("Total Users: " + totalUsers);
        System.out.println("Total Tasks: " + totalTasks);
        System.out.println("Completed Tasks: " + completedTasks);
        System.out.println("In Progress Tasks: " + inProgressTasks);

        // Ajouter les statistiques à la requête
        request.setAttribute("totalUsers", totalUsers);
        request.setAttribute("totalTasks", totalTasks);
        request.setAttribute("completedTasks", completedTasks);
        request.setAttribute("inProgressTasks", inProgressTasks);

        // Charger les utilisateurs
        List<User> users = userDao.getAllUsers();
        request.setAttribute("users", users);

        // Redirection vers le dashboard admin
        RequestDispatcher dispatcher = request.getRequestDispatcher("/views/admin-dashboard.jsp");
        dispatcher.forward(request, response);
    }



}
