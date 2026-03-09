import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Database {
	
	private static String URL = "jdbc:mysql://localhost:3306/data_centers";
	private static String USER = "java";
	private static String PSW = "1234";
	//(Nota: El usuario de MySQL "java" solo tiene privilegios para hacer SELECT)
	
	/**
	 * Valida que el usuario y la contraseña introducidos en el programa existen en la base de datos,
	 * verifcando que la pareja de datos introducidos con correctos a partir de un SELECT.
	 * @param usuario Usuario introducido en el JTextField userField en InicioFrame.java
	 * @param contraseña Contraseña introducida en el JTextField pswField en InicioFrame.java
	 * @return true si el SELECT devuelve algo, false si no.
	 */
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
	
}
