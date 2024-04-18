package dentadura;

import Empleados.Dentista;
import Empleados.Recepcionista;
import presupuestos.GestionDentadura;
import DBCustomer.*;

import java.sql.SQLOutput;

import java.util.Scanner;

import static java.lang.System.in;


public class Main {

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        //inicio de sesion
        System.out.print("introduzca su nombre de usuario: ");
        String username = sc.nextLine();
        System.out.print("introduzca su contraseña: ");
        String password = sc.nextLine();

        if(username.equals("alan") || username.equals("ALAN") && password.equals(Dentista.getPass())){
            Dentista dentista = new Dentista();
        }else if(username.equals("rafa") || username.equals("RAFA") && password.equals(Recepcionista.getPass())){
            Recepcionista recepcionista = new Recepcionista();
        }




        Dentadura d = new Dentadura(new int[]{23}, "E");
        d.setDiente(11, "X");
        d.setDiente(38, "P");
        GestionDentadura presupuestoD = new GestionDentadura(d);
        d.mostrar();
        presupuestoD.calcularPresupuesto();



    }

}