package Clientes;

import DBCustomer.*;
import java.sql.Date;

public class Cliente{
    private String dni;
    private String nombreApellidos;
    private java.sql.Date fechaNacimiento;
    private int telefono;
    private String email;


    public Cliente(String dni, String nombreApellidos, java.sql.Date fechaNacimiento, int telefono, String email){
        this.dni = dni;
        this. nombreApellidos = nombreApellidos;
        this.fechaNacimiento=fechaNacimiento;
        this.telefono = telefono;
        this.email = email;
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

    public void setDni(String dni) {
        this.dni = dni;
    }

    public void setNombreApellidos(String nombreApellidos) {
        this.nombreApellidos = nombreApellidos;
    }

    public void setFechaNacimiento(Date fechaNacimiento) {
        this.fechaNacimiento = fechaNacimiento;
    }

    public void setTelefono(int telefono) {
        this.telefono = telefono;
    }

    public void setEmail(String email) {
        this.email = email;
    }





}
