package Alarmas;

public class clientesDeudores extends Alarmas{

    public clientesDeudores(){
    }
    @Override
    public void presupuestoElevado() {
        System.out.println("El cliente debe una suma importante");
    }

    @Override
    public void deuda() {
        System.out.println("Agregar a la lista de morosos");
    }
}
