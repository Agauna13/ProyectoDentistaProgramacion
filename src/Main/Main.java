package Main;

import java.io.IOException;
import java.sql.*;
import java.util.HashMap;
import java.util.Scanner;

import DBCustomer.*;
import dentadura.*;
import Clientes.*;
import presupuestos.Presupuesto;


public class Main {

    public static void main(String[] args) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        Dentadura dentadura = null;
        Conection conection = null;
        System.out.println("Bienvenido a la Clinica Dental: \n Inicie sesión para continuar");

        conection = databaseLogin(conection);


        System.out.println("pulse 1 para Crear un cliente. \n" +
                "Pulse 2 para Buscar un cliente. \n" +
                "Pulse 3 para Actualizar un cliente. \n" +
                "pulse 4 para Crear o actualizar una Dentadura. \n" +
                "pulse 0 para salir");

        int choice = sc.nextInt();

        while (choice != 0) {
            switch (choice) {
                case 1:
                    cliente = crearCliente(conection, cliente);
                    break;
                case 2:
                    buscarCliente(conection);
                    break;
                case 3:
                    cliente = actualizarCliente(conection, cliente);
                    break;
                case 4:
                    dentadura = crearDentadura(conection, dentadura, cliente);
                    dentadura.mostrar();
                    break;
                default:
                    if (choice == 0) {
                        break;
                    } else {
                        throw new IllegalStateException("Por favor, introduzca un valor válido: " + sc.nextInt());
                    }

            }
            System.out.println("pulse 1 para Crear un cliente. \n" +
                    "Pulse 2 para Buscar un cliente. \n" +
                    "Pulse 3 para Actualizar un cliente. \n" +
                    "pulse 4 para Crear o actualizar una Dentadura. \n" +
                    "pulse 0 para salir");
            choice = sc.nextInt();
        }

        System.out.println("¡Hasta Pronto!");


    }

    private static Conection databaseLogin(Conection conection) throws SQLException {
        boolean conexion = false;
        Scanner sc = new Scanner(System.in);
        String USER;
        String PASS;

        while (!conexion) {
            System.out.println("nombre de usuario: ");
            USER = sc.nextLine();
            System.out.println("Contraseña: ");
            PASS = sc.nextLine();
            conection = new Conection(USER, PASS);
            try {
                conection.getConnection();
                conexion = true;
            } catch (Exception e) {
                conexion = false;
                USER = "";
                PASS = "";
                System.out.println("Nombre de usuario o contraseña Incorrectos.\n Vuelva a intentarlo");
            }
        }


        return conection;
    }

    private static Dentadura crearDentadura(Conection conection, Dentadura dentadura, Cliente cliente) throws SQLException {
        dentadura = menuDentadura(dentadura);
        //dentadura.mostrar();
        System.out.println("elija el cliente al que quiere asociar la dentadura");
        cliente = buscarCliente(conection);

        try {
            conection.insertarDentadura(cliente, dentadura);
        } catch (Exception e) {
            System.err.println(e);
        }
        Presupuesto presupuesto = null;
        try {
            presupuesto = new Presupuesto(dentadura, cliente);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        presupuesto.toString();

        return dentadura;
    }

    private static Dentadura menuDentadura(Dentadura dentadura) {
        HashMap<Integer, String> cambiar = new HashMap<>();
        Scanner sc = new Scanner(System.in);
        System.out.println("Elija Los dientes que han sido intervenidos");
        String choice = "";
        while (!choice.equals("s")) {
            System.out.println("introduzca el id del diente: ");
            int id = sc.nextInt();
            sc.nextLine();
            System.out.println("introduzca la intervencion realizada (recuerde: E[X]traccion, [P]rotesis, [E]mpaste ");
            String intervencion = sc.nextLine();
            cambiar.put(id, intervencion);

            System.out.println("pulse s para salir Enter para continuar");
            choice = sc.nextLine();
        }
        dentadura = new Dentadura(cambiar);

        return dentadura;
    }

    private static Cliente actualizarCliente(Conection conection, Cliente cliente) {
        cliente = menuCliente(cliente);
        try {
            conection.insertarCliente(cliente);
        } catch (Exception e) {
            System.out.println(e);
        }
        return cliente;
    }

    private static Cliente buscarCliente(Conection conection) throws SQLException {
        Scanner sc = new Scanner(System.in);
        Cliente cliente = null;
        System.out.println("Introduzca el DNI de su cliente: ");
        String DNI = sc.nextLine();

        try {
            conection.buscarClientes(DNI);

        } catch (SQLException e) {
            System.out.println(e);
        }
        if (conection.buscarClientes(DNI).equals("Cliente no encontrado")) {
            System.out.println("cliente No Encontrado, Desea Crearlo? ([S]/[N]");
            if (sc.nextLine().equals("s")) {
                cliente = crearCliente(conection, cliente);
            }
        } else {
            cliente = conection.crearObjetoCliente(DNI, cliente);
        }

        return cliente;
    }

    private static Cliente crearCliente(Conection conection, Cliente cliente) {
        cliente = menuCliente(cliente);

        try {
            conection.insertarCliente(cliente);
        } catch (Exception e) {
            System.out.println(e);
        }

        return cliente;
    }

    private static Cliente menuCliente(Cliente cliente) {
        Scanner sc = new Scanner(System.in);
        String dni, nombreYapellidos, fecha, email, telefono;

        System.out.println("introduzca el dni del cliente: ");
        dni = sc.next();
        sc.nextLine();
        System.out.println("introduzca el nombre y apellido del cliente: ");
        nombreYapellidos = sc.nextLine();
        System.out.println("introduzca el fecha de nacimiento del cliente en formato AAAA-MM-DD: ");
        fecha = sc.nextLine();
        System.out.println("introduzca el telefono del cliente: ");
        telefono = sc.nextLine();
        System.out.println("introduzca el email del cliente");
        email = sc.nextLine();

        cliente = new Cliente(dni, nombreYapellidos,
                Date.valueOf(fecha), telefono, email);


        return cliente;
    }

}

