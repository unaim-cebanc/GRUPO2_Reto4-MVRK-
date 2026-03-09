import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

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
    }
}
