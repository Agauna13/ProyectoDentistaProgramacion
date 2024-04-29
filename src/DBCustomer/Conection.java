package DBCustomer;

import java.sql.*;
import Clientes.Cliente;
import dentadura.*;

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
    public void mostrarClientes(Cliente cliente) throws SQLException {
        String sql = "SELECT * FROM clientes where dni = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)){
             pstmt.setString(1, cliente.getDni());
             ResultSet rs = pstmt.executeQuery();
             while(rs.next()){
                 System.out.print(rs.getString("dni") + ", ");
                 System.out.print(rs.getString("nombre_apellidos") + ", ");
                 System.out.print(rs.getDate("fecha_nacimiento") + ", ");
                 System.out.print(rs.getInt("telefono") + ", ");
                 System.out.print(rs.getString("email"));
             }

        }catch (SQLException e) {
        e.printStackTrace();
        }
    }

    public void insertarDentadura(Cliente cliente, Dentadura dentadura) throws SQLException{
        String sql = "INSERT INTO dentaduras (dni_cliente, Zona1, Zona2, Zona3, Zona4) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, cliente.getDni());
            pstmt.setString(2, dentadura.Zona1);
            pstmt.setString(3, dentadura.Zona2);
            pstmt.setString(4, dentadura.Zona3);
            pstmt.setString(5, dentadura.Zona4);

            pstmt.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void mostrarDentadura(Cliente cliente){

    }

    public void insertarPresupuesto(Cliente cliente){

    }

    public void imprimirPresupuesto(Cliente cliente){

    }

}


