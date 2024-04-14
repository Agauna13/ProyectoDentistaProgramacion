package dentadura;

import presupuestos.GestionDentadura;
import DBCustomer.*;


public class Main {

    public static void main(String[] args) {
        //crear usuario
        Conection.insertarCliente("X9074791A", "Alan Gauna", "603807972", "Indalecio prieto 35, Palma");
        Conection.mostrarClientes("X9074791A");

        //crear dentadura

        Dentadura d = new Dentadura(new int[]{23}, "E");
        d.setDiente(11, "X");
        d.setDiente(38, "P");
        GestionDentadura presupuestoD = new GestionDentadura(d);
        d.mostrar();
        presupuestoD.calcularPresupuesto();



    }

}