package Main;

import java.sql.*;
import java.util.Scanner;

import DBCustomer.*;
import DBCustomer.*;
import dentadura.*;
import presupuestos.*;
import Clientes.*;



public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        System.out.println("nombre de usuario: ");
        String USER = sc.nextLine();
        System.out.println("Contraseña: ");
        String PASS = sc.nextLine();

        Conection conection = new Conection(USER, PASS);
        Cliente cliente = new Cliente("60352725N", "Alan Gauna",
        Date.valueOf("1990-07-07"), 603807972, "amg07071990@gmail.com");

        try{
            conection.insertarCliente(cliente);
        }catch(Exception e){
            System.err.println("poronga esta....");
        }


        try {
            conection.mostrarClientes(cliente);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }


    }

}

