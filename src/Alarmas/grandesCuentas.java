package Alarmas;

public class grandesCuentas implements Alarmas{

    public grandesCuentas(){
        presupuestoElevado();
    }

    @Override
    public void presupuestoElevado() {
        System.out.println("Presupuesto Elevado, Debe resolverse a la mayor brevedad posible.");
    }

    @Override
    public void deuda() {
        System.out.println("Deuda Crítica, Cuenta grande deudora");
    }


}
