package presupuestos;

import dentadura.Dentadura;
import dentadura.Diente;

import java.util.Objects;

public class GestionDentadura {

    private Dentadura dentadura;


    public GestionDentadura(Dentadura dentadura) {
        this.dentadura = dentadura;
    }

    public void calcularPresupuesto() {
        double presupuestoTotal = 0.0;
        Diente[][] boca = dentadura.getBoca(); // Acceso seguro a boca
        int piezas = dentadura.getPiezas(); // Acceso seguro a PIEZAS

        for (int i = 0; i <= 1; i++) { //recorremos la boca buscando dientes que hayamos tratado
            for (int j = 0; j < piezas / 2; j++) {
                Diente diente = boca[i][j];
                if(!Objects.equals(diente.getEstado(), "B")) {
                    precioUnitario(diente);
                    presupuestoTotal += calcularCostoDiente(diente);
                }
            }
        }

        System.out.println("Total intervención:.... " + presupuestoTotal);
    }

    private double calcularCostoDiente(Diente diente) {
        String estado = diente.getEstado();
        double costo = 0.0;

        switch (estado) {
            case "X":
                costo = 50.0; // Descuento para dientes extraídos
                break;
            case "E":
                costo = 150.0; // Costo adicional para dientes empastados
                break;
            case "P":
                costo = 500.0; // Costo adicional para dientes con prótesis
                break;
            default:
                costo = 0.0; // Estado desconocido, no se asigna costo
                break;
        }

        return costo;
    }


    private void precioUnitario(Diente diente){
        String estado = diente.getEstado();
        double precioUnitario = calcularCostoDiente(diente);
        String x = "Extracción:.......";
        String e = "Empaste:..........";
        String p = "Prótesis:.......";


        switch (estado) {
            case "X":
                System.out.println(x + precioUnitario);
                break;
            case "E":
                System.out.println(e + precioUnitario);
                break;
            case "P":
                System.out.println(p + precioUnitario);
                break;
            default:
                System.out.println("Error:.... " + precioUnitario);
                break;
        }

    }
}


