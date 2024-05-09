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

    // Método para insertar un nuevo cliente en la tabla clientes
    public void insertarCliente(Cliente cliente) {
        String sqlBuscar = "SELECT COUNT(*) AS cantidad FROM clientes WHERE dni = ?";
        String sqlInsertar = "INSERT INTO clientes (dni, nombre_apellidos, fecha_nacimiento, telefono, email) VALUES (?, ?, ?, ?, ?)";
        String sqlActualizar = "UPDATE clientes SET nombre_apellidos = ?, fecha_nacimiento = ?, telefono = ?, email = ? WHERE dni = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmtBuscar = conn.prepareStatement(sqlBuscar);
             PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertar);
             PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar)) {

            // Verificar si el cliente ya existe en la base de datos
            pstmtBuscar.setString(1, cliente.getDni());
            ResultSet rs = pstmtBuscar.executeQuery();
            rs.next();
            int cantidad = rs.getInt("cantidad");

            if (cantidad > 0) {
                // El cliente ya existe, entonces actualiza sus datos
                pstmtActualizar.setString(1, cliente.getNombreApellidos());
                pstmtActualizar.setDate(2, cliente.getFechaNacimiento());
                pstmtActualizar.setString(3, cliente.getTelefono());
                pstmtActualizar.setString(4, cliente.getEmail());
                pstmtActualizar.setString(5, cliente.getDni());

                pstmtActualizar.executeUpdate();
            } else {
                // El cliente no existe, entonces inserta un nuevo registro
                pstmtInsertar.setString(1, cliente.getDni());
                pstmtInsertar.setString(2, cliente.getNombreApellidos());
                pstmtInsertar.setDate(3, cliente.getFechaNacimiento());
                pstmtInsertar.setString(4, cliente.getTelefono());
                pstmtInsertar.setString(5, cliente.getEmail());

                pstmtInsertar.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarDentadura(Cliente cliente, Dentadura dentadura) throws SQLException{
        String sqlBuscar = "SELECT COUNT(*) AS cantidad FROM dentaduras WHERE dni_cliente = ?";
        String sqlActualizar = "UPDATE dentaduras SET Zona1 = ?, Zona2 = ?, Zona3 = ?, Zona4 = ? WHERE dni_clieste = ?";
        String sqlInsertar = "INSERT INTO dentaduras (dni_cliente, Zona1, Zona2, Zona3, Zona4) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmtBuscar = conn.prepareStatement(sqlBuscar);
             PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertar);
             PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar)) {

            pstmtBuscar.setString(1, cliente.getDni());
            ResultSet rs = pstmtBuscar.executeQuery();
            rs.next();
            int cantidad = rs.getInt("cantidad");

            if (cantidad >0){
                pstmtActualizar.setString(1, cliente.getDni());
                pstmtActualizar.setString(2, dentadura.Zona1);
                pstmtActualizar.setString(3, dentadura.Zona2);
                pstmtActualizar.setString(4, dentadura.Zona3);
                pstmtActualizar.setString(5, dentadura.Zona4);
                pstmtActualizar.executeUpdate();

            }else {

                pstmtInsertar.setString(1, cliente.getDni());
                pstmtInsertar.setString(2, dentadura.Zona1);
                pstmtInsertar.setString(3, dentadura.Zona2);
                pstmtInsertar.setString(4, dentadura.Zona3);
                pstmtInsertar.setString(5, dentadura.Zona4);
                pstmtInsertar.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void borrarCliente(Cliente cliente){
        String sqlBorrarCliente = "DELETE FROM clientes where clientes.dni = ?";
        String sqlBorrarDentadura = "DELETE FROM dentaduras WHERE dentaduras.dni_cliente = ?";
        try (Connection conn = getConnection();
             PreparedStatement pstmtBorrarCliente = conn.prepareStatement(sqlBorrarCliente);
             PreparedStatement pstmtBorrarDentadura = conn.prepareStatement(sqlBorrarDentadura);

        ){
            pstmtBorrarDentadura.setString(1, cliente.getDni());
            pstmtBorrarDentadura.executeUpdate();
            pstmtBorrarCliente.setString(1, cliente.getDni());
            pstmtBorrarCliente.executeUpdate();

        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public void mostrarDentadura(Cliente cliente){

    }

    public void insertarPresupuesto(Cliente cliente){

    }

    public void imprimirPresupuesto(Cliente cliente){

    }

}


