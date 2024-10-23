package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

import model.User;
import utils.JDBCUtils;
import utils.PasswordUtils;

public class UserDao {

	public User validateUser(String username, String password) {
	    String query = "SELECT * FROM users WHERE username = ?";
	    User user = null;

	    try (Connection conn = JDBCUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {

	        stmt.setString(1, username);
	        ResultSet rs = stmt.executeQuery();

	        if (rs.next()) {
	            String storedPassword = rs.getString("password");

	            if (PasswordUtils.verifyPassword(password, storedPassword)) {
	                user = new User(
	                    rs.getInt("id"),
	                    rs.getString("username"),
	                    rs.getString("email"),
	                    storedPassword, // Store hashed password
	                    rs.getString("role")
	                );
	            }
	        }
	    } catch (SQLException e) {
	        e.printStackTrace();
	    }
	    return user;
	}


	public void registerUser(User user) throws SQLException {
	    String query = "INSERT INTO users (username, password, email) VALUES (?, ?, ?)";

	    try (Connection conn = JDBCUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setString(1, user.getUsername());
	        stmt.setString(2, user.getPassword());
	        stmt.setString(3, user.getEmail());
	        stmt.setString(4, user.getRole());

	        stmt.executeUpdate();
	    }
	}
	public List<User> getAllUsers() throws SQLException {
	    String query = "SELECT * FROM users";
	    List<User> users = new ArrayList<>();

	    try (Connection conn = JDBCUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query);
	         ResultSet rs = stmt.executeQuery()) {

	        while (rs.next()) {
	            User user = new User(
	                rs.getInt("id"),
	                rs.getString("username"),
	                rs.getString("password"),
	                rs.getString("email"),
	                rs.getString("role")
	            );
	            users.add(user);
	        }
	    }
	    return users;
	}
    // Ajouter un utilisateur
    public void addUser(User user) throws SQLException {
        String sql = "INSERT INTO users (username, password, email, role) VALUES (?, ?, ?, ?)";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql)) {
            stmt.setString(1, user.getUsername());
            stmt.setString(2, user.getPassword());
            stmt.setString(3, user.getEmail());
            stmt.setString(4, user.getRole());
            stmt.executeUpdate();
        }
    }

	public void deleteUser(int userId) throws SQLException {
	    String query = "DELETE FROM users WHERE id = ?";
	    try (Connection conn = JDBCUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(query)) {
	        stmt.setInt(1, userId);
	        stmt.executeUpdate();
	    }
	}
	public int getTotalUsers() throws SQLException {
	    String sql = "SELECT COUNT(*) FROM users";
	    try (Connection conn = JDBCUtils.getConnection();
	         PreparedStatement stmt = conn.prepareStatement(sql);
	         ResultSet rs = stmt.executeQuery()) {
	        if (rs.next()) {
	            return rs.getInt(1);  // Retourne le total d'utilisateurs
	        }
	    }
	    return 0;  // Retourne 0 si aucun utilisateur trouvé
	}




}
