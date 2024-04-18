package Empleados;

import java.sql.*;
public class Recepcionista extends Empleado {
    static String USER = "recepcionista";
    static String PASS = "rafa69conJoaquin";
    public Recepcionista(){
        super(Recepcionista.USER, Recepcionista.PASS);
    }

    public static String getPass() {
        return PASS;
    }

    public String getUser(){
        return USER;
    }
}
