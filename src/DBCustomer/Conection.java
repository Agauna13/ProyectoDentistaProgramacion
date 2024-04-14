package DBCustomer;

import java.sql.*;

public class Conection {
    // Definir la URL de conexión a la base de datos
    static final String DB_URL = "jdbc:mariadb://localhost:3306/clinica_dental";

    // Credenciales de la base de datos
    static final String USER = "root";
    static final String PASS = "mypass";

    // Método para insertar un nuevo cliente en la tabla clientes
    public static void insertarCliente(String dni, String nombre, String telefono, String direccion) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO clientes (dni, nombre, telefono, direccion) VALUES (?, ?, ?, ?)")) {
                stmt.setString(1, dni);
                stmt.setString(2, nombre);
                stmt.setString(3, telefono);
                stmt.setString(4, direccion);
                stmt.executeUpdate();
                System.out.println("Cliente insertado correctamente.");
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para mostrar todos los clientes de la tabla clientes
    public static void mostrarClientes(String dni) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS);
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM clientes WHERE dni = '" + dni + "'")) {
            while (rs.next()) {
                System.out.println("DNI: " + rs.getString("dni") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Teléfono: " + rs.getString("telefono") +
                        ", Dirección: " + rs.getString("direccion"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Agrega aquí otros métodos para actualizar y eliminar clientes si es necesario
}


