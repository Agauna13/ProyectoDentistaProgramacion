package Empleados;


public class Dentista extends Empleado{
    static String USER = "dentista";
    static String PASS = "rafa69conBernat";

    public Dentista() {
        super(Dentista.USER, Dentista.PASS);

    }

    public static String getPass(){
        return PASS;
    }

    public String getUser(){
        return USER;
    }

}


