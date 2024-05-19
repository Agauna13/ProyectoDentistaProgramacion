package DBCustomer;

import java.sql.*;

import Clientes.Cliente;
import dentadura.*;

public class Conection {
    // Definir la URL de conexión a la base de datos
    static final String DB_URL = "jdbc:mariadb://localhost:3312/clinica_dental";

    private final String USER;

    private final String PASS;

    // Método para establecer la conexión con la base de datos


    public Conection (String USER, String PASS) throws SQLException {
        this.USER = USER;
        this.PASS = PASS;
    }

    public Connection getConnection() throws SQLException {
        return DriverManager.getConnection(DB_URL, USER, PASS);
    }


    // Método para obtener todos los clientes de la tabla clientes


    public String buscarClientes(String dni) throws SQLException {
        String sql = "SELECT * FROM clientes where dni = ?";
        String busqueda = "";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {

            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            int count = 0;

            while (rs.next()) {
                count++;
                dni = rs.getString("dni");
                String nombre = rs.getString("nombre_apellidos");
                java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String telefono = String.valueOf(rs.getInt("telefono"));
                String email = rs.getString("email");
                System.out.print(dni + ", ");
                System.out.print(nombre + ", ");
                System.out.print(fechaNacimiento + ", ");
                System.out.print(telefono + ", ");
                System.out.print(email);
                /*System.out.print(rs.getString("dni") + ", ");
                System.out.print(rs.getString("nombre_apellidos") + ", ");
                System.out.print(rs.getDate("fecha_nacimiento") + ", ");
                System.out.print(rs.getInt("telefono") + ", ");
                System.out.print(rs.getString("email"));*/
                System.out.println();
            }
            if (count == 0) {
                busqueda = "Cliente no encontrado";
            } else {
                busqueda = dni;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return busqueda;
    }

    // Método para insertar un nuevo cliente en la tabla clientes
    public void insertarCliente(Cliente cliente) {
        String sqlBuscar = "SELECT * FROM clientes WHERE dni = ?";
        String sqlInsertar = "INSERT INTO clientes (dni, nombre_apellidos, fecha_nacimiento, telefono, email) VALUES (?, ?, ?, ?, ?)";
        String sqlActualizar = "UPDATE clientes SET nombre_apellidos = ?, fecha_nacimiento = ?, telefono = ?, email = ? WHERE dni = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmtBuscar = conn.prepareStatement(sqlBuscar);
             PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertar);
             PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar)) {

            // Verificar si el cliente ya existe en la base de datos
            pstmtBuscar.setString(1, cliente.getDni());
            ResultSet rs = pstmtBuscar.executeQuery();
            int count = 0;
            while(rs.next()){
                count++;
                if (count > 0) {
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
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public void insertarDentadura(Cliente cliente, Dentadura dentadura) {
        String sqlBuscar = "SELECT * FROM dentaduras WHERE dni_cliente = ?";
        String sqlActualizar = "UPDATE dentaduras SET Zona1 = ?, Zona2 = ?, Zona3 = ?, Zona4 = ? WHERE dni_cliente = ?";
        String sqlInsertar = "INSERT INTO dentaduras (dni_cliente, Zona1, Zona2, Zona3, Zona4) VALUES (?, ?, ?, ?, ?)";
        try (Connection conn = getConnection();
             PreparedStatement pstmtBuscar = conn.prepareStatement(sqlBuscar);
             PreparedStatement pstmtInsertar = conn.prepareStatement(sqlInsertar);
             PreparedStatement pstmtActualizar = conn.prepareStatement(sqlActualizar)) {

            pstmtBuscar.setString(1, cliente.getDni());
            ResultSet rs = pstmtBuscar.executeQuery();
            int count = 0;
            if(rs.next()){
                count = 1;
            }
            if (count > 0) {
                pstmtActualizar.setString(1, dentadura.getZona1());
                pstmtActualizar.setString(2, dentadura.getZona2());
                pstmtActualizar.setString(3, dentadura.getZona3());
                pstmtActualizar.setString(4, dentadura.getZona4());
                pstmtActualizar.setString(5, cliente.getDni());
                pstmtActualizar.executeUpdate();
            } else {
                pstmtInsertar.setString(1, cliente.getDni());
                pstmtInsertar.setString(2, dentadura.getZona1());
                pstmtInsertar.setString(3, dentadura.getZona2());
                pstmtInsertar.setString(4, dentadura.getZona3());
                pstmtInsertar.setString(5, dentadura.getZona4());
                pstmtInsertar.executeUpdate();
            }

        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public  Cliente crearObjetoCliente(String dni, Cliente cliente) {
        String sql = "SELECT * FROM clientes where dni = ?";

        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
             pstmt.setString(1, dni);
             ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dni = rs.getString("dni");
                String nombre = rs.getString("nombre_apellidos");
                java.sql.Date fechaNacimiento = rs.getDate("fecha_nacimiento");
                String telefono = String.valueOf(rs.getInt("telefono"));
                String email = rs.getString("email");

                cliente = new Cliente(dni, nombre, fechaNacimiento, telefono, email);
            }
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }

        return cliente;
    }


}