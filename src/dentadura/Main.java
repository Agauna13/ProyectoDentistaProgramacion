package dentadura;

import presupuestos.GestionDentadura;


public class Main {

    public static void main(String[] args) {
        //crear dentadura

        Dentadura d = new Dentadura(new int[]{14, 28, 15, 12, 23}, "E");
        GestionDentadura presupuestoD = new GestionDentadura(d);
        d.mostrar();
        presupuestoD.calcularPresupuesto();



    }

}