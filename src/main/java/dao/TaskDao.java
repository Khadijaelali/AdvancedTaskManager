package dao;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import model.Task;
import utils.JDBCUtils;

public class TaskDao {

    // Méthode pour ajouter une nouvelle tâche
    public void addTask(Task task) throws SQLException {
        String query = "INSERT INTO tasks (title, description, status, user_id) VALUES (?, ?, ?, ?)";
        
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.setInt(4, task.getUserId()); // Ajout de l'userId
            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer toutes les tâches d'un utilisateur
    public List<Task> getTasksByUserId(int userId) throws SQLException {
        List<Task> tasks = new ArrayList<>();
        String query = "SELECT * FROM tasks WHERE user_id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, userId);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Task task = new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getInt("user_id")
                );
                tasks.add(task);
            }
        }
        return tasks;
    }


    // Méthode pour mettre à jour une tâche
    public void updateTask(Task task) throws SQLException {
        String query = "UPDATE tasks SET title = ?, description = ?, status = ? WHERE id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setString(1, task.getTitle());
            stmt.setString(2, task.getDescription());
            stmt.setString(3, task.getStatus());
            stmt.setInt(4, task.getId());
            stmt.executeUpdate();
        }
    }

    // Méthode pour supprimer une tâche par ID
    public void deleteTask(int id) throws SQLException {
        String query = "DELETE FROM tasks WHERE id = ?";

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            stmt.executeUpdate();
        }
    }

    // Méthode pour récupérer une tâche par son ID
    public Task getTaskById(int id) throws SQLException {
        String query = "SELECT * FROM tasks WHERE id = ?";
        Task task = null;

        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(query)) {
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                task = new Task(
                    rs.getInt("id"),
                    rs.getString("title"),
                    rs.getString("description"),
                    rs.getString("status"),
                    rs.getInt("user_id")
                );
            }
        }
        return task;
    }
    public int getTotalTasks() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tasks";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);  // Retourne le total de tâches
            }
        }
        return 0;
    }

    public int getCompletedTasks() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tasks WHERE status = 'Terminé'";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);  // Retourne le nombre de tâches terminées
            }
        }
        return 0;
    }
    public int getInProgressTasks() throws SQLException {
        String sql = "SELECT COUNT(*) FROM tasks WHERE status = 'En cours'";
        try (Connection conn = JDBCUtils.getConnection();
             PreparedStatement stmt = conn.prepareStatement(sql);
             ResultSet rs = stmt.executeQuery()) {
            if (rs.next()) {
                return rs.getInt(1);  // Retourne le nombre de tâches en cours
            }
        }
        return 0;  
    }


}
