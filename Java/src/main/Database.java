package main;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import javax.swing.JOptionPane;
import javax.swing.JTextField;
import java.sql.DatabaseMetaData;
import java.io.FileWriter;
import java.io.IOException;

/**
 * Clase que controla todas las operaciones que involucren a la base de datos
 * @author Unai, Aimar, Unax, Aroa
 */
public class Database {

    private static String URL = "jdbc:mysql://localhost:3306/data_centers";
    private static String USER = "java";
    private static String PSW = "1234";
    private static boolean admin;

    /**
     * Valida que el usuario y la contraseña introducidos en el programa existen en la base de datos,
     * verifcando que la pareja de datos introducidos con correctos a partir de un SELECT.
     * @param usuario Usuario introducido en el JTextField userField en InicioFrame.java
     * @param contraseña Contraseña introducida en el JTextField pswField en InicioFrame.java
     * @return true si el SELECT devuelve algo, false si no.
     */
    public static Boolean validarLogin(String usuario, String contraseña) {
        try {
            // Conecta a la base de datos
        	Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PSW);

            // Prepara la query SELECT para recoger el usuario y contraseña
        	PreparedStatement validar = conn.prepareStatement(
                "SELECT id_usuario_admin, nombre_usuario, contraseña FROM usuarios WHERE nombre_usuario = ? AND contraseña = ?"
            );
            validar.setString(1, usuario);
            validar.setString(2, contraseña);

            // Ejecuta la query
            ResultSet resultSet = validar.executeQuery();
            
            // Revisa si resultSet no devuelve nada
            if (!resultSet.isBeforeFirst()) {
                return false;
            }
            
            // Recoge el valor de la columna "id_usuario_admin"
            resultSet.next();
            Integer valor = (Integer)resultSet.getObject("id_usuario_admin");
            
            // Establece si el usuario es administrador
            setAdmin(valor);
            
            // Cierra la conexion
            conn.close();
            resultSet.close();

        } catch (SQLException e) {
            throw new IllegalStateException("Cannot connect the database!", e);
        }
        
        
        return true;
    }


    /**
     * Exporta todas las tablas de la base de datos a ficheros CSV independientes
     * en la carpeta indicada.
     * @param carpetaDestino Ruta de la carpeta donde se guardarán los CSV.
     */
    public static void exportarBDaCSVs(String carpetaDestino) throws SQLException, IOException {
        // Establece la conexion con la base de datos
    	Connection conn = DriverManager.getConnection(Database.URL, Database.USER, Database.PSW);

        // Recoge todas las tablas de la base de datos
    	DatabaseMetaData meta = conn.getMetaData();
        ResultSet tablas = meta.getTables(null, null, "%", new String[]{"TABLE"});

        // Exporta cada tabla de la BBDD a .csv
        while (tablas.next()) {
            String nombreTabla = tablas.getString("TABLE_NAME");
            exportarTablaCSV(conn, nombreTabla, carpetaDestino);
        }
        
        // Cierra la conexion
        tablas.close();
        conn.close();
    }

    /**
     * Exporta una tabla concreta a CSV.
     * @param conn La conexion con la BBDD
     * @param tabla Nombre de tabla a exportar
     * @param carpetaDestino Nombre de la carpeta que va a alojar el .csv
     */
    private static void exportarTablaCSV(Connection conn, String tabla, String carpetaDestino)
    		throws SQLException, IOException {

        // Recoge todos los datos de la tabla
    	Statement st = conn.createStatement();
        ResultSet rs = st.executeQuery("SELECT * FROM " + tabla);

        // Cuenta el numero de columnas de la tabla
        java.sql.ResultSetMetaData rsmd = rs.getMetaData();
        int columnas = rsmd.getColumnCount();

        // Establece cual es la carpeta de destino (líneas 118 a 149 generadas con ayuda de IA)
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

        // Cierra todas la conexiones
        fw.flush();
        fw.close();
        rs.close();
        st.close();
    }
	
    /**
     * Autora: Aroa Hernández
     * Elimina datos de una tabla de la BBDD
     */
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

                    } else {
                    	// Mensaje que indica que el registro insertado por el usuario no existe
                    	JOptionPane.showMessageDialog(null, "No se encontró ningún registro con ese ID");
                    }
                    
                    // Cierra la conexion
                    ps.close();
                    conn.close();
                }
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error: " + ex.getMessage());
        }
    }
	
    /**
     * Perimete insertar datos en la tabla de Sedes de la BBDD
     * @param nombre Nombre de la sede
     * @param ciudad Ciudad donde se aloja la sede
     * @param superficie Superficie de la sede
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosSedes(String nombre, String ciudad, String superficie) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO sedes (nombre_sede, ciudad, superficie_m2) VALUES (?, ?, ?)");
            ps.setString(1, nombre);
            ps.setString(2, ciudad);
            ps.setInt(3, Integer.parseInt(superficie));
            
            // Ejecuta el INSERT 
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
        	return false;
        }
        // Mensaje que confira la insercion correcta de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Inserta datos en la tabla de Usuarios de la BBDD
     * @param admin
     * @param nombre_usuario
     * @param contraseña
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosUsuarios(int admin, String nombre_usuario, String contraseña) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement("INSERT INTO usuarios (id_usuario_admin, nombre_usuario, contraseña) VALUES ( ?, ?, ?)");
            ps.setInt(1, admin);
            ps.setString(2, nombre_usuario);
            ps.setString(3, contraseña);
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        // Confirmación de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Permite insertar datos en la tabla de residuos de la BBDD
     * @param id_sede Id de la sede que ha generado los datos
     * @param tipo_residuos Tipo de residuos generados
     * @param cantidad_t Cantidad de residuos
     * @param metodo_gestion Metodo de reciclaje
     * @param fecha Fecha en la que se ha insertado el dato
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosResiduos(int id_sede, String tipo_residuos, int cantidad_t, String metodo_gestion, String fecha) {
        try {
            // Establece la conexion
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO residuos (id_sede, tipo_residuos, cantidad_kg, metodo_gestion, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, tipo_residuos);
            ps.setInt(3, cantidad_t);
            ps.setString(4, metodo_gestion);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(fecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
       // Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
	/**
	 * Permite insertar datos a la tabla de Usuarios de la BBDD
	 * @param id_empleado_gerente Id del empleado gerente
	 * @param id_sede Id de la sede en la que trabaja el empleado
	 * @param nombre Nombre del empleado
	 * @param apellido Apellido del empleado
	 * @param textoFecha Fecha en la que el empleado firmo su contrato
	 * @return Si ha habido algún error devuelve false, si se inserta correctamente true
	 */
    public static boolean insertarDatosEmpleados(int id_empleado_gerente, int id_sede, String nombre, String apellido, String textoFecha) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO empleados (id_empleado, id_empleado_gerente, id_sede, nombre, apellido, fecha_contrato) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_empleado_gerente);
            ps.setInt(2, id_sede);
            ps.setString(3, nombre);
            ps.setString(4, apellido);
            ps.setDate(5, java.sql.Date.valueOf(textoFecha)); // formato: "yyyy-MM-dd"
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        // Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Permite insertar datos a la tabla de Consumo de energia de la BBDD
     * @param id_sede Id de la sede a la que pertenece el registro de consumo
     * @param kwh_servidores Cantidad de Kw/h que consumen los servidores
     * @param kwh_refrigeracion Cantidad de Kw/h que consumen los sistemas de refrigeracion
     * @param kwh_totales Cantidad de Kw/h totales que se han consumido
     * @param textoFecha Fecha y hora en la que se introducieron los datos
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosConsumoEnergia(int id_sede, int kwh_servidores, int kwh_refrigeracion, int kwh_totales, String textoFecha) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO consumo_energia (id_sede, kwh_servidores, kwh_refrigeracion, kwh_totales, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setInt(2, kwh_servidores);
            ps.setInt(3, kwh_refrigeracion);
            ps.setInt(4, kwh_totales);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(textoFecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        // Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Permite insertar datos la tabla de Emisiones de la base de datos
     * @param id_sede Id de la sede a la que pertenecen los datos sobre las emisiones
     * @param tipo_emision Tipo de emision que ha generado la sede
     * @param cantidad_toneladas Cantidad de emisiones
     * @param fuente Fuente de la que porvienen las emisiones
     * @param textoFecha Fecha y Hora en la que se hizo el registro 
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosEmisiones(int id_sede, String tipo_emision, int cantidad_toneladas, String fuente, String textoFecha) {
        try {
        	// Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
        	
        	//Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO emisiones (id_sede, tipo_emision, cantidad_toneladas, fuente, fecha) VALUES (?, ?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, tipo_emision);
            ps.setInt(3, cantidad_toneladas);
            ps.setString(4, fuente);
            ps.setTimestamp(5, java.sql.Timestamp.valueOf(textoFecha)); // formato: "yyyy-MM-dd HH:mm:ss"
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        //Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Permite insertar datos a la tabla de Salas de servidores de la BBDD
     * @param id_sede Id de la sede a la que pertenece la sala
     * @param nombre_sala Nombre que se le da a la sala
     * @param num_racks Numero de racks que contiene la sala
     * @param superficie_m2 Superficie que ocupa la sala
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosServidores(int id_sede, String nombre_sala, short num_racks, int superficie_m2) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO sala_servidores (id_sede, nombre_sala, num_racks, superficie_m2) VALUES (?, ?, ?, ?)");
            ps.setInt(1, id_sede);
            ps.setString(2, nombre_sala);
            ps.setShort(3, num_racks);       // SMALLINT → setShort
            ps.setInt(4, superficie_m2);
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            //Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        // Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Permite insertar datos a la tabla de Sistemas de refrigeracion de la BBDD
     * @param id_sala_servidores Id de la sala de servidores que contiene el sistema de refrigeracion
     * @param tipo_sistema Tipo de sistema que realiza la refrigeracion
     * @param eficiencia_energetica Mide la eficiencia energetica del sistema
     * @param refrigerante Tipo de refrigeracion del sistema
     * @return Si ha habido algún error devuelve false, si se inserta correctamente true
     */
    public static boolean insertarDatosRefrigeracion( int id_sala_servidores, String tipo_sistema, String eficiencia_energetica, String refrigerante) {
        try {
            // Establece la conexion con la base de datos
        	Connection conn = DriverManager.getConnection(URL, USER, PSW);
            
        	// Prepara el INSERT
        	PreparedStatement ps = conn.prepareStatement(
                "INSERT INTO refrigeracion (id_sala_servidores, tipo_sistema, eficiencia_energetica, refrigerante) VALUES (?, ?, ?, ?)");
            ps.setInt(1, id_sala_servidores);
            ps.setString(2, tipo_sistema);
            ps.setString(3, eficiencia_energetica);
            ps.setString(4, refrigerante);
            
            // Ejecuta el INSERT
            ps.executeUpdate();
            
            // Cierra la conexion
            conn.close();
        } catch (SQLException e) {
            // Mensaje de error
        	JOptionPane.showMessageDialog(null, e);
            return false;
        }
        // Confirmacion de insercion de datos
        JOptionPane.showMessageDialog(null, "Datos insertados correctamente", "Status", JOptionPane.INFORMATION_MESSAGE);
        return true;
    }
    
    /**
     * Establece si el usuario es un usuario administrador o no
     * @param valor Valor que decide si el usuario es administrador o no, si es NULL el usuario NO es administrador,
     * en cualquier otro caso, el usuario SI es administrador.
     */
    private static void setAdmin(Integer valor) {
    	if (valor == null) {
        	admin = false;
        } else {
        	admin = true;
        }
    }
    
    /**
     * Permite visualizar el valor de la variable admin
     * @return Devuelve el valor de admin
     */
    public static boolean getAdmin() {
    	return admin;
    }
}