package dentadura;

import java.util.ArrayList;
import java.util.HashMap;

public class Dentadura {

    //Estados: Bien, Extraido, Empastado, protesis
    private final int PIEZAS = 32;
    private Diente[][] boca; //fila 0 es el maxilar superior, fila 1 maxila inferior
    private String diagrama = "";

    private String Zona1 = "";
    private String Zona2 = "";
    private String Zona3 = "";
    private String Zona4 = "";

    public Dentadura() {//constructor vacio que crea la dentadura con dientes
        //inicializo la estructura
        boca = new Diente[2][PIEZAS / 2];
        //LLENAR LA BOCA CON LOS DIENTES
        int pos;
        String estado = "B";
        // Maxilar superior
        //Zona1
        for (int id = 11; id <= 18; id++) {
            pos = posicion(id);//posicion del diente en el maxilar
            boca[0][pos] = new Diente(id, estado);//pongo el nuevo diente en su posicion

        }
        //Zona2
        for (int id = 21; id <= 28; id++) {
            pos = posicion(id);//posicion del diente en el maxilar
            boca[0][pos] = new Diente(id, estado);//pongo el nuevo diente en su posicion

        }
        // Maxilar inferior
        //Zona3
        for (int id = 41; id <= 48; id++) {
            pos = posicion(id);//posicion del diente en el maxilar
            boca[1][pos] = new Diente(id, estado);//pongo el nuevo diente en su posicion

        }
        //Zona4
        for (int id = 31; id <= 38; id++) {
            pos = posicion(id);//posicion del diente en el maxilar
            boca[1][pos] = new Diente(id, estado);//pongo el nuevo diente en su posicion
        }
    }

    public Dentadura(HashMap<Integer, String> cambiar) {
        this(); //llamo y ejecuto el constructor por defecto
        for (Integer diente : cambiar.keySet()) { //recorro el array cambiar
            setDiente(diente, cambiar.get(diente));
        }
        zonas();
    }

    public Diente getDiente(int id) { //retorna el diente con el 'id'
        int pos = posicion(id);
        int maxi = maxilar(id);//devuelve el maxilar
        return this.boca[maxi][pos]; //retorno el diente

    }

    public void setDiente(int id, String estado) {
        int pos = posicion(id);
        int maxi = maxilar(id);//devuelve el maxilar
        this.boca[maxi][pos].setEstado(estado); //retorno el diente
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Dentadura other = (Dentadura) obj;
        //dos dentaduras son iguales cuando los dientes tienen el mismo estado
        for (int i = 0; i <= 1; i++) {//maxilar
            for (int j = 0; j < PIEZAS / 2; j++) {
                if (!boca[i][j].getEstado().equals(other.boca[i][j].getEstado())) {
                    return false;
                }
            }
        }
        return true;
    }

    public int getPiezas() {
        return PIEZAS;
    }

    public Diente[][] getBoca() {
        return boca;
    }

    public void mostrar() {
        //Mostramos el id de la Mandíbula superior
        for (int i = 0; i < PIEZAS / 2; i++) {
            System.out.print(this.boca[0][i].getId() + " ");
            diagrama += this.boca[0][i].getId() + " ";
        }
        diagrama += "\n";
        System.out.println();
        for (int i = 0; i < PIEZAS / 2; i++) {
            System.out.print(" | ");
            diagrama += " | ";
        }
        System.out.println();
        //Mostramos el estado a menos que este 'E[x]traído'
        for (int i = 0; i < (PIEZAS / 2); i++) {
            if (!"X".equals(this.boca[0][i].getEstado())) {
                System.out.print(" " + this.boca[0][i].getEstado() + " ");
                diagrama += " " + this.boca[0][i].getEstado() + " ";
            } else {
                System.out.print("   ");
                diagrama+="   ";
            }
        }
        diagrama += "\n";
        System.out.println();
        //Mostramos el estado a menos que este 'E[x]traído'
        for (int i = 0; i < (PIEZAS / 2); i++) {
            if (!"X".equals(this.boca[1][i].getEstado())) {
                System.out.print(" " + this.boca[1][i].getEstado() + " ");
                diagrama += " " + this.boca[1][i].getEstado() + " ";
            } else {
                System.out.print("   ");
                diagrama += "   ";
            }
        }
        diagrama += "\n";
        System.out.println();

        for (int i = 0; i < PIEZAS / 2; i++) {
            System.out.print(" | ");
            diagrama += " | ";
        }
        System.out.println();
        //Mostramos la mandíbula inferior
        for (int i = 0; i < (PIEZAS / 2); i++) {
            System.out.print(this.boca[1][i].getId() + " ");
            diagrama += this.boca[1][i].getId() + " ";
        }
        diagrama += "\n";
        System.out.println();

    }

    public void zonas() {
        //Zona1
        for (int i = 7; i >= 0; i--) {
            //System.out.print(this.boca[0][i].getId() + " ");
            Zona1 += this.boca[0][i].getEstado();
        }
        //System.out.println();
        //Zona2
        for (int i = 8; i < 16; i++) {
            //System.out.print(this.boca[0][i].getId() + " ");
            Zona2 += this.boca[0][i].getEstado();
        }
        //System.out.println();
        //Zona3
        for (int i = 8; i < 16; i++) {
            //System.out.print(this.boca[1][i].getId() + " ");
            Zona3 += this.boca[1][i].getEstado();
        }
        //System.out.println();
        for (int i = 7; i >= 0; i--) {
            //System.out.print(this.boca[1][i].getId() + " ");
            Zona4 += this.boca[1][i].getEstado();
        }
    }

    private int maxilar(int id) //devuelve el maxilar (0 -> superior 1-> inferior) para un determinado id
    {
        if (id < 30) {
            return 0;
        } else {
            return 1;
        }
    }

    private int posicion(int id) //devuelve la ubicación (del 0 al 15) para un determinado id
    {
        if (maxilar(id) == 0) //estamos en el superior
        {
            if (id < 19) //estamos a la derecha [18...11]  Zona1
            {
                return 7 - (id - 11);
            } else //estamos a la izquierda [21...28]  Zona2
            {
                return 8 + (id - 21);
            }
        } else //estamos en el inferior
        {
            if (id > 40) //estamos a la derecha [48...41]  //Zona3
            {
                return (48 - id);
            } else //estamos a la izquierda [31...38]  //Zona4
            {
                return 8 + (id - 31);
            }
        }
    }

    public String getZona1(){
        return Zona1;
    }
    public String getZona2(){
        return Zona2;
    }
    public String getZona3(){
        return Zona3;
    }
    public String getZona4(){
        return Zona4;
    }

    public String getDiagrama(){
        return this.diagrama;
    }

}