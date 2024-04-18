package Empleados;

import java.sql.*;
import DBCustomer.Conection;



public class Empleado {

    static final String DB_URL = "jdbc:mariadb://localhost:3306/clinica_dental";
    protected String USER;
    protected String PASS;

    public Empleado(String USER, String PASS){
        DBConnection(this.USER, this.PASS);
    }

    public void DBConnection(String USER, String PASS) {
        try {
            // Creas una instancia de DatabaseManager con el usuario y la contraseña
            Conection dbManager = new Conection(USER, PASS);

            // Llamas al método getConnection para obtener la conexión
            Connection conn = dbManager.getConnection();
        } catch (SQLException e) {
            System.out.println("Revise sus datos de conexión y vuelva a intentarlo");
            e.printStackTrace();

        }
    }

}



