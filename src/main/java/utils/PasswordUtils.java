package utils;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

public class PasswordUtils {

    // Method to hash the password using SHA-256
    public static String hashPassword(String password) {
        StringBuilder hashedPassword = new StringBuilder();
        try {
            MessageDigest md = MessageDigest.getInstance("SHA-256");
            byte[] hashBytes = md.digest(password.getBytes());

            for (byte b : hashBytes) {
                hashedPassword.append(String.format("%02x", b));
            }
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return hashedPassword.toString();
    }

    // Method to verify if an input password matches the stored hashed password
    public static boolean verifyPassword(String inputPassword, String storedHashedPassword) {
        String hashedInput = hashPassword(inputPassword);  // Hash the input password
        return hashedInput.equals(storedHashedPassword);   // Compare with the stored hash
    }
}
