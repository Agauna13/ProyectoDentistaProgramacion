package presupuestos;

import Clientes.Cliente;
import dentadura.*;
import java.io.*;
import java.time.*;
import java.time.format.DateTimeFormatter;
import java.time.temporal.ChronoUnit;
import java.util.*;
import Alarmas.*;

public class Presupuesto{
    private static int idPresupuesto = 0;
    private Cliente cliente;
    private Dentadura dentadura;
    private HashMap<String, Integer> intervenciones = new HashMap<>();
    private Date fechaPresupuesto;
    private String cuerpo;
    private int total;
    private String presu;


    public Presupuesto(Dentadura dentadura, Cliente cliente) throws IOException {
        idPresupuesto++;
        this.cliente = cliente;
        this.dentadura = dentadura;
        this.fechaPresupuesto = new Date();
        generarPresupuesto();
        this.total = calcularTotal();
        this.cuerpo = cuerpo();
        presu = toString();
        imprimirPresupuesto();

    }


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

    @Override
    public String toString(){
        return "Presupuesto nº: " + idPresupuesto +
                "\n" + cliente.toString() +
                "\n" +
                "\n" + "Fecha Presupuesto " + fechaPresupuesto +
                "\n" + "Conceptos:\n" + cuerpo +
                "\n" + "Total: " + total;
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

    public void imprimirPresupuesto() throws IOException {
        Scanner sc = new Scanner(System.in);
        LocalDate fechaIntroducida = null;
        System.out.println("introduzca el nombre del archivo ");
        String archivo = sc.nextLine();


        while(fechaIntroducida == null){
            try{
                System.out.println("Introduce la fecha de la intervencion en formato yyyy-MM-dd:");//try catch
                String fechaIntervencion = sc.nextLine();
                fechaIntroducida = LocalDate.parse(fechaIntervencion, DateTimeFormatter.ISO_DATE);
            }catch(Exception e){
                System.out.println("Por favor, verifique que el formato de la fecha es YYYY-MM-DD");
            }
        }
        LocalDate fechaActual = LocalDate.now();

        long diferenciaDias = ChronoUnit.DAYS.between(fechaIntroducida, fechaActual);

        if(diferenciaDias > 30 && total < 1000){
            clientesDeudores moroso = new clientesDeudores();
            moroso.deuda();
            presu += "\nPresupuesto Impago";
        }else if(diferenciaDias > 30 && total > 1000){
            grandesCuentas cuenta = new grandesCuentas();
            cuenta.presupuestoElevado();
            cuenta.deuda();
            presu += "\nPresupuesto Impago";
        }

        Impresion presupuesto = new Impresion(archivo, presu);
        System.out.println(presu);
    }

}
