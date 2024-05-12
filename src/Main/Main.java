package Main;

import java.sql.*;
import java.util.Scanner;

import DBCustomer.*;
import DBCustomer.*;
import dentadura.*;
import presupuestos.*;
import Clientes.*;



public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.println("Bienvenido a la Clinica Dental: \n Inicie sesión para continuar");
        System.out.println("nombre de usuario: ");
        String USER = sc.nextLine();
        System.out.println("Contraseña: ");
        String PASS = sc.nextLine();
        Conection conection = new Conection(USER, PASS);

        System.out.println("pulse 1 para Crear un cliente. \n" +
                "Pulse 2 para Buscar un cliente. \n" +
                "Pulse 3 para Actualizar un cliente. \n" +
                "pulse 4 para Crear una Dentadura. \n" +
                "pulse 5 para Buscar una Dentadura. \n" +
                "pulse 6 para Actualizar una Dentadura. \n" +
                "pulse 7 para Crear un presupuesto. \n" +
                "pulse 8 para Buscar un presupuesto. \n" +
                "pulse 9 para Borrar un presupuesto. \n");


        switch(sc.nextInt()) {
            case 1:
                crearCliente(conection);

        }
        /*

        Dentadura d = new Dentadura(new int[]{11, 22, 33, 44}, "E");

        d.zonas();
        System.out.println();
        System.out.println(d.Zona1);
        System.out.println(d.Zona2);
        System.out.println(d.Zona3);
        System.out.println(d.Zona4);

        Conection conection = new Conection(USER, PASS);
        Cliente cliente = new Cliente("60352725N", "Alan Gauna",
        Date.valueOf("1990-07-07"), 603807972, "amg07071990@gmail.com");

        try{
            conection.insertarCliente(cliente);
        }catch(Exception e){
            System.out.println(e);
        }


        try {
            conection.mostrarClientes(cliente);
        } catch (SQLException e) {
            System.out.println(e);
        }

        try{
            conection.insertarDentadura(cliente, d);
        }catch(Exception e){
            System.err.println(e);
        }

        /*try{
            conection.borrarCliente(cliente);
        }catch(Exception e){
            System.err.println(e);
        }*/


    }

    private static void crearCliente(Conection conection) {
        Scanner sc = new Scanner(System.in);
        String dni, nombreYapellidos, fecha, email;
        int telefono;
        System.out.println("introduzca el dni del cliente: ");
        dni = sc.next();
        System.out.println("introduzca el nombre y apellido del cliente: ");
        nombreYapellidos = sc.next();
        System.out.println("introduzca el fecha de nacimiento del cliente en formato AAAA-MM-DD: ");
        fecha = sc.next();
        System.out.println("introduzca el telefono del cliente: ");
        telefono = sc.nextInt();
        System.out.println("introduzca el email del cliente");
        email=sc.next();

        Cliente cliente = new Cliente(dni, nombreYapellidos,
                Date.valueOf(fecha), telefono, email);

        try{
            conection.insertarCliente(cliente);
        }catch(Exception e){
            System.out.println(e);
        }
    }

}

