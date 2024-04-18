package Clientes;

import java.sql.Date;

public class Cliente {
    private int idCliente;
    private String dni;
    private String nombreApellidos;
    private java.sql.Date fechaNacimiento;
    private int edadMenor6;
    private int telefono;
    private String email;


    private Cliente(String dni, String nombreApellidos, java.sql.Date fechaNacimiento, int telefono, String email){

    }

    public String getDni() {
        return dni;
    }

    public String getNombreApellidos() {
        return nombreApellidos;
    }

    public Date getFechaNacimiento() {
        return fechaNacimiento;
    }


    public String getTelefono() {
        return String.valueOf(telefono);
    }

    public String getEmail() {
        return email;
    }
}
