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
    
    // *** NUEVO: insertar datos en una tabla ***
    public static boolean insertarDatosUsuarios( int admin, String nombre_usuario, String contraseña) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (id_usuario_admin, nombre_usuario, contraseña) VALUES ( ?, ?, ?)");
            ps.setInt(1, admin);
            ps.setString(2, nombre_usuario);
            ps.setString(3, contraseña);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosResiduos(int id_sede, String tipo_residuos, int cantidad_kg, String metodo_gestion, String fecha) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO residuos (id_sede, tipo_residuos, cantidad_kg, metodo_gestion, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, tipo_residuos);
            ps.setInt(3, cantidad_kg);
            ps.setString(4, metodo_gestion);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(fecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosEmpleados(int id_empleado_gerente, int id_sede, String nombre, String apellido, String textoFecha) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO empleados (id_empleado, id_empleado_gerente, id_sede, nombre, apellido, fecha_contrato) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_empleado_gerente);
            ps.setInt(2, id_sede);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.setDate(5, java.sql.Date.valueOf(textoFecha)); // formato: "yyyy-MM-dd"
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosConsumoEnergia(int id_sede, int kwh_servidores, int kwh_refrigeracion, int kwh_totales, String textoFecha) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO consumo_energia (id_sede, kwh_servidores, kwh_refrigeracion, kwh_totales, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setInt(2, kwh_servidores);
            ps.setInt(3, kwh_refrigeracion);
            ps.setInt(4, kwh_totales);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(textoFecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosEmisiones(int id_sede, String tipo_emision, int cantidad_toneladas, String fuente, String textoFecha) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO emisiones (id_sede, tipo_emision, cantidad_toneladas, fuente, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, tipo_emision);
            ps.setInt(3, cantidad_toneladas);
            ps.setString(4, fuente);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(textoFecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosServidores(int id_sede, String nombre_sala, short num_racks, int superficie_m2) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO sala_servidores (id_sede, nombre_sala, num_racks, superficie_m2) VALUES (?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, nombre_sala);
            ps.setShort(3, num_racks);       // SMALLINT → setShort
            ps.setInt(4, superficie_m2);
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, e);
            return false;
        }
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    public static boolean insertarDatosRefrigeracion( int id_sala_servidores, String tipo_sistema, String eficiencia_energetica, String refrigerante) {
        try {
            Connection conn = DriverManager.getConnection(URL, USER, PSW);
            PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO refrigeracion (id_sala_servidores, tipo_sistema, eficiencia_energetica, refrigerante) VALUES (?, ?, ?, ?)");
            ps.setInt(1, id_sala_servidores);
            ps.setString(2, tipo_sistema);
            ps.setString(3, eficiencia_energetica);
            ps.setString(4, refrigerante);
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