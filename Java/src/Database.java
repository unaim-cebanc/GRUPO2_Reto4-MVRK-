import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.swing.JOptionPane;
import javax.swing.JTextField;

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
	
	// Eliminar datos de BD (llamado por el menú y el botón)
    public static void eliminarDatos() {

        try {
        	// Campos de texto donde el usuario introducirá los datos
        	JTextField tablaField = new JTextField();
        	JTextField columnaField = new JTextField();
        	JTextField idField = new JTextField();
        	
        	// Campos de texto para que el usuario introduzca los datos
        	Object[] message = {
        		    "Tabla:", tablaField,
        		    "Columna:", columnaField,
        		    "ID:", idField
        		    };
        	
        	// Muestra una ventana de diálogo con los campos anteriores
            int option = JOptionPane.showConfirmDialog(
                    null,
                    message,
                    "Eliminar registro",
                    JOptionPane.OK_CANCEL_OPTION  //Crea los botones OK y Cancelar
            );

            // Al pulsar OK se ejecuta el siguiente código
            if (option == JOptionPane.OK_OPTION) {

            	String tabla = tablaField.getText();
            	String columna = columnaField.getText();
            	String id = idField.getText();
            	
            	// Segunda ventana de confirmación para evitar borrar datos por error
                int confirm = JOptionPane.showConfirmDialog(
                        null,
                        "¿Seguro que quieres eliminar el registro con ID " + id + " de la tabla " + tabla + "?",
                        "Confirmar eliminación",
                        JOptionPane.YES_NO_OPTION
                );

                if (confirm == JOptionPane.YES_OPTION) {
                	
                	// SENTENCIA SQL
                	Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PSW);
                    String sql = "DELETE FROM " + tabla + " WHERE " + columna + " = ?";

                    PreparedStatement ps = conn.prepareStatement(sql);
                    ps.setInt(1, Integer.parseInt(id));

                    int filasAfectadas = ps.executeUpdate();
                    
                 // Mensaje que indica que el registro ha sido eliminado
                    if (filasAfectadas > 0) {
                        JOptionPane.showMessageDialog(null, "Registro eliminado correctamente");
                        
                 // Mensaje que indica que el registro insertado por el usuario no existe
                    } else {
                        JOptionPane.showMessageDialog(null, "No se encontró ningún registro con ese ID");
                    }

                    ps.close();
                    conn.close();
                }
                	
                
                }
           

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
	
}
