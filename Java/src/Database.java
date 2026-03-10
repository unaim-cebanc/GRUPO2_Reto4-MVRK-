import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
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

    // *** NUEVO: obtener columnas de una tabla ***
    public static List<String> obtenerColumnas(String tabla) {
        List<String> cols = new ArrayList<>();
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            ResultSet rs = conn.createStatement().executeQuery("DESCRIBE " + tabla);
            rs.next();
            while (rs.next()) cols.add(rs.getString("Field"));
            conn.close();
        } catch (SQLException e) {
            return null;
        }
        return cols;
    }

    // *** NUEVO: insertar datos en una tabla ***
    public static boolean insertarDatosSedes(String tabla, String nombre, String ciudad, int superficie) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO " + tabla + " VALUES (?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, ciudad);
            ps.setInt(3, superficie);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            return false;
        }
        return true;
    }

}