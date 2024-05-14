package presupuestos;

import dentadura.*;
import java.util.*;
import Alarmas.*;

public class Presupuesto{

    private Dentadura dentadura;
    private HashMap<String, Integer> intervenciones = new HashMap<>();
    private Date fechaPresupuesto;
    private String cuerpo;
    private int total;


    public Presupuesto(Dentadura dentadura){
        this.dentadura = dentadura;
        this.fechaPresupuesto = new Date();
        generarPresupuesto();
        this.total = calcularTotal();
        this.cuerpo = cuerpo();

    }

    /*metemos en un hashmap como clave el estado del diente si lo hemos modificado y en el valor, el costo del diente.
    public HashMap h1(Diente[][] boca){
        for(String s : dentadura.getBoca())
    }*/
    public void generarPresupuesto() {
        Diente[][] boca = dentadura.getBoca(); // Acceso seguro a boca
        int piezas = dentadura.getPiezas(); // Acceso seguro a PIEZAS
        int totalPresupuesto = 0;
        for (int i = 0; i <= 1; i++) { //recorremos la boca buscando dientes que hayamos tratado
            for (int j = 0; j < piezas / 2; j++) {
                Diente diente = boca[i][j];
                if (!Objects.equals(diente.getEstado(), "B")) {
                    intervenciones.put(diente.getEstado(), calcularCostoDiente(diente));
                }
            }
        }

    }


    private int calcularCostoDiente(Diente diente) {
        String estado = diente.getEstado();
        int costo = 0;

        switch (estado) {
            case "X":
                costo = 50; // Descuento para dientes extraídos
                break;
            case "E":
                costo = 150; // Costo adicional para dientes empastados
                break;
            case "P":
                costo = 500; // Costo adicional para dientes con prótesis
                break;
            default:
                costo = 0; // Estado desconocido, no se asigna costo
                break;
        }

        return costo;
    }

    public int calcularTotal(){
        int total= 0;
        for(Integer precio: intervenciones.values()){
            total += precio;
        }
        if(total > 1000){
            grandesCuentas cuenta = new grandesCuentas();
            cuenta.presupuestoElevado();

        }
        return total;
    }

    public String cuerpo(){
        String body = "";
        for(String intervencion : intervenciones.keySet()){
            switch(intervencion){
                case "E":
                    body += "Empaste:......." + intervenciones.get(intervencion) + "\n";
                    break;
                case "X":
                    body += "Extracción:...." + intervenciones.get(intervencion) + "\n";
                    break;
                case "P":
                    body += "Prótesis:......" + intervenciones.get(intervencion) + "\n";
                    break;
            }
        }
        body += "\n" + "Subtotal:......" + (total /1.21) +
                "\n" + "Total:........." + total;

        return body;
    }

    public Date getFechaPresupuesto() {
        return fechaPresupuesto;
    }

    public void setFechaPresupuesto(Date fechaPresupuesto) {
        this.fechaPresupuesto = fechaPresupuesto;
    }

    public String getCuerpo() {
        return cuerpo;
    }

    public int getTotal() {
        return total;
    }

}
