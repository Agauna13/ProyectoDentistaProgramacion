package DBCustomer;

import java.sql.*;
import Clientes.Cliente;

public class Conection {
    // Definir la URL de conexión a la base de datos
    static final String DB_URL = "jdbc:mariadb://localhost:3306/clinica_dental";

    private String USER;

    private String PASS;

    // Método para establecer la conexión con la base de datos


    public  Conection(String USER, String PASS) {
        this.USER = USER;
        this.PASS = PASS;
    }
    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }

    // Método para insertar un nuevo cliente en la tabla clientes
    public void insertarCliente(Cliente cliente) {
        String sql = "INSERT INTO clientes (dni, nombre_apellidos, fecha_nacimiento, telefono, email) VALUES (?, ?, ?, ?, ?)";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
                pstmt.setString(1, cliente.getDni());
                pstmt.setString(2, cliente.getNombreApellidos());
                pstmt.setDate(3, cliente.getFechaNacimiento());
                pstmt.setString(4, cliente.getTelefono());
                pstmt.setString(5, cliente.getEmail());

                pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    // Método para obtener todos los clientes de la tabla clientes
    public void mostrarClientes() throws SQLException {
        String sql = "SELECT * FROM clientes";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
            ResultSet resultSet = pstmt.executeQuery();

        }catch (SQLException e) {
        e.printStackTrace();
    }
    }



    // Agrega aquí otros métodos para actualizar y eliminar clientes si es necesario
}


