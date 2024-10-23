package Controller;

import jakarta.servlet.*;
import jakarta.servlet.annotation.WebServlet;
import jakarta.servlet.http.*;
import java.io.IOException;
import java.sql.SQLException;
import dao.UserDao;
import model.User;
import utils.PasswordUtils;

@WebServlet("/register")
public class RegisterServlet extends HttpServlet {
    private static final long serialVersionUID = 1L;
    private UserDao userDao;

    @Override
    public void init() {
        userDao = new UserDao();
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
        dispatcher.forward(request, response);
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String username = request.getParameter("username");
        String password = request.getParameter("password");
        String email = request.getParameter("email");

        // Hash the password
        String hashedPassword = PasswordUtils.hashPassword(password);

        // Set default role to 'user'
        String role = "user";

        // Create a new User object
        User user = new User(0, username, hashedPassword, email, role);

        try {
            userDao.registerUser(user);
            response.sendRedirect("views/login.jsp");
        } catch (SQLException e) {
            e.printStackTrace();
            request.setAttribute("errorMessage", "Erreur lors de l'inscription.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("views/register.jsp");
            dispatcher.forward(request, response);
        }
    }
}
