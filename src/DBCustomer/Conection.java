package DBCustomer;

import dentadura.*;
import presupuestos.*;
import java.sql.*;

public class Conection {
    // Definir la URL de conexión a la base de datos
    static final String DB_URL = "jdbc:mariadb://localhost:3306/clinica_dental";

    // Credenciales de la base de datos
    static final String USER = "root";
    static final String PASS = "mypass";

    public static void main(String[] args) {
        try (Connection conn = DriverManager.getConnection(DB_URL, USER, PASS)) {
            // Insertar datos en la tabla usuarios
            insertarUsuario(conn, "Nuevo Usuario", "nuevo_usuario@example.com");

            // Leer datos de la tabla usuarios
            System.out.println("Usuarios:");
            mostrarUsuarios(conn);

            // Actualizar datos en la tabla usuarios
            //actualizarUsuario(conn, 1, "Usuario Actualizado", "usuario_actualizado@example.com");

            // Eliminar datos de la tabla usuarios
            //eliminarUsuario(conn, 3);

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para insertar un nuevo usuario en la tabla usuarios
    static void insertarUsuario(Connection conn, String nombre, String email) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("INSERT INTO usuarios (nombre, email) VALUES (?, ?)")) {
            stmt.setString(1, nombre);
            stmt.setString(2, email);
            stmt.executeUpdate();
            System.out.println("Usuario insertado correctamente.");
        }
    }

    // Método para mostrar todos los usuarios de la tabla usuarios
    static void mostrarUsuarios(Connection conn) throws SQLException {
        try (Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery("SELECT * FROM usuarios")) {
            while (rs.next()) {
                System.out.println("ID: " + rs.getInt("id") +
                        ", Nombre: " + rs.getString("nombre") +
                        ", Email: " + rs.getString("email"));
            }
        }
    }

    // Método para actualizar un usuario en la tabla usuarios
    static void actualizarUsuario(Connection conn, int id, String nuevoNombre, String nuevoEmail) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("UPDATE usuarios SET nombre = ?, email = ? WHERE id = ?")) {
            stmt.setString(1, nuevoNombre);
            stmt.setString(2, nuevoEmail);
            stmt.setInt(3, id);
            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas > 0) {
                System.out.println("Usuario actualizado correctamente.");
            } else {
                System.out.println("No se encontró ningún usuario con el ID proporcionado.");
            }
        }
    }

    // Método para eliminar un usuario de la tabla usuarios
    static void eliminarUsuario(Connection conn, int id) throws SQLException {
        try (PreparedStatement stmt = conn.prepareStatement("DELETE FROM usuarios WHERE id = ?")) {
            stmt.setInt(1, id);
            int filasEliminadas = stmt.executeUpdate();
            if (filasEliminadas > 0) {
                System.out.println("Usuario eliminado correctamente.");
            } else {
                System.out.println("No se encontró ningún usuario con el ID proporcionado.");
            }
        }
    }
}
