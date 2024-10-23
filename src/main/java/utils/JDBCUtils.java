package utils;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class JDBCUtils {

    // Informations de connexion à la base de données
    private static final String JDBC_URL = "jdbc:mysql://localhost:3306/task_manager";
    private static final String JDBC_USERNAME = "root"; 
    private static final String JDBC_PASSWORD = "";      

    /**
     * Méthode pour obtenir une connexion à la base de données.
     * @return Connection objet représentant la connexion à MySQL
     * @throws SQLException si la connexion échoue
     */
    public static Connection getConnection() throws SQLException {
        Connection connection = null;
        try {
            // Enregistrer le driver JDBC MySQL
            Class.forName("com.mysql.cj.jdbc.Driver");
            // Établir la connexion
            connection = DriverManager.getConnection(JDBC_URL, JDBC_USERNAME, JDBC_PASSWORD);
        } catch (ClassNotFoundException e) {
            System.err.println("Erreur : Le driver JDBC MySQL est introuvable.");
            e.printStackTrace();
        }
        return connection;
    }

    /**
     * Méthode utilitaire pour gérer les exceptions SQL.
     * @param ex L'exception SQL à traiter
     */
    public static void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());

                Throwable t = ex.getCause();
                while (t != null) {
                    System.err.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }
}
