package dentadura;

import presupuestos.GestionDentadura;


public class Main {

    public static void main(String[] args) {
        //crear dentadura

        Dentadura d = new Dentadura(new int[]{23}, "E");
        d.setDiente(11, "X");
        d.setDiente(38, "P");
        GestionDentadura presupuestoD = new GestionDentadura(d);
        d.mostrar();
        presupuestoD.calcularPresupuesto();



    }

}