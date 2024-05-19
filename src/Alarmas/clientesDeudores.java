package Alarmas;

public class clientesDeudores implements Alarmas{

    public clientesDeudores(){
    }
    @Override
    public void presupuestoElevado() {
        System.out.println("El cliente debe una suma importante");
    }

    @Override
    public void deuda() {
        System.out.println("Presupuesto Impago caducado. Agregar a la lista de morosos");
    }
}
