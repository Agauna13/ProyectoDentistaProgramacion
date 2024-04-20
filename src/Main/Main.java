package Main;

import java.sql.*;
import java.util.Scanner;

import Empleados.*;
import DBCustomer.*;
import dentadura.*;
import presupuestos.*;
import Clientes.*;



public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        System.out.println("nombre de usuario: ");
        String USER = sc.nextLine();
        System.out.println("Contraseña: ");
        String PASS = sc.nextLine();

        Usuarios usuarioActual = new Usuarios(USER, PASS);

        //inicio de sesion
        for(int i  = 0; i<3; i++){
            try {
                if (!usuarioActual.getUsuario(USER, PASS))throw new excepciones("Nombre de usuario o contraseña incorrectos.");
                if(usuarioActual.getUsuario(USER, PASS)) {
                    System.out.println("bienvenido " + usuarioActual.getUsername());
                    break;
                }
            }catch (excepciones e) {
                System.err.println(e.getMessage());
                System.out.println("verifique los datos y vuelva a intentarlo: ");
                System.out.println("nombre de usuario: ");
                USER = sc.nextLine();
                System.out.println("Contraseña: ");
                PASS = sc.nextLine();
            }
        }



        /*Dentadura d = new Dentadura(new int[]{23}, "E");
        d.setDiente(11, "X");
        d.setDiente(38, "P");
        GestionDentadura presupuestoD = new GestionDentadura(d);
        d.mostrar();
        presupuestoD.calcularPresupuesto();*/


    }

}

