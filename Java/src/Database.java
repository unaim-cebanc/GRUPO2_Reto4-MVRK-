import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.util.ArrayList;

// NUEVOS IMPORTS PARA EXPORTAR
import java.sql.DatabaseMetaData;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase de acceso a base de datos
 */
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

            PreparedStatement validar = conn.prepareStatement(
                "SELECT nombre_usuario, contraseña FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?"
            );
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

    // ========= MÉTODOS PARA EXPORTAR TODAS LAS TABLAS A CSV ========= //

    /**
     * Exporta todas las tablas de la base de datos a ficheros CSV independientes
     * en la carpeta indicada.
     *
     * @param carpetaDestino Ruta de la carpeta donde se guardarán los CSV.
     */
    public static void exportarBDaCSVs(String carpetaDestino) throws SQLException, IOException {
        Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PSW);

        DatabaseMetaData meta = conn.getMetaData();
        ResultSet tablas = meta.getTables(null, null, "%", new String[]{"TABLE"});

        while (tablas.next()) {
            String nombreTabla = tablas.getString("TABLE_NAME");
            exportarTablaCSV(conn, nombreTabla, carpetaDestino);
        }

        tablas.close();
        conn.close();
    }

    /**
     * Exporta una tabla concreta a CSV.
     */
    private static void exportarTablaCSV(Connection conn, String tabla, String carpetaDestino)
            throws SQLException, IOException {

        Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + tabla);

        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int columnas = rsmd.getColumnCount();

        String ruta = carpetaDestino;
        if (!ruta.endsWith("/") && !ruta.endsWith("\\")) {
            ruta += System.getProperty("file.separator");
        }
        ruta += tabla + ".csv";

        FileWriter fw = new FileWriter(ruta, false);

        // Cabeceras
        for (int i = 1; i <= columnas; i++) {
            fw.append(rsmd.getColumnName(i));
            if (i < columnas) fw.append(";");
        }
        fw.append("\n");

        // Filas
        while (rs.next()) {
            for (int i = 1; i <= columnas; i++) {
                String valor = rs.getString(i);
                if (valor == null) valor = "";
                // por si hay ; en el contenido, lo cambiamos a ,
                valor = valor.replace(";", ",");
                fw.append(valor);
                if (i < columnas) fw.append(";");
            }
            fw.append("\n");
        }

        fw.flush();
        fw.close();
        rs.close();
        st.close();
	
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
