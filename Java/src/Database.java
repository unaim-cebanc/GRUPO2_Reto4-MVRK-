import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;

import javax.swing.JOptionPane;

import java.util.ArrayList;

public class Database {

    private static String URL = "jdbc:mysql://localhost:3306/data_centers";
    private static String USER = "java";
    private static String PSW = "1234";

    public static Boolean validarLogin(String usuario, String contraseña) {
        try {
            Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PSW);
            PreparedStatement validar = conn.prepareStatement("SELECT nombre_usuario, contraseña FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?");
            validar.setString(1, usuario);
            validar.setString(2, contraseña);
            ResultSet resultSet = validar.executeQuery();
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        return true;
    }

    // *** NUEVO: insertar datos en una tabla ***
    public static boolean insertarDatosSedes(String nombre, String ciudad, String superficie) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO sedes (nombre_sede, ciudad, superficie_m2) VALUES (?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, ciudad);
            ps.setInt(3, Integer.parseInt(superficie));
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
        	return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }

}