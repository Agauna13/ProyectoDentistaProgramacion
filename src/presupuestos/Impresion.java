package presupuestos;

import java.io.*;

public class Impresion {
    static File presupuesto = null;
    static FileReader fr = null;
    static FileWriter fw = null;
    static BufferedReader br = null;
    static PrintWriter pw = null;

    public Impresion(String estado, double precioUnitario) throws IOException {
        try{
            presupuesto = new File("C:\\Users\\parki\\Desktop\\presupuestos\\presu.txt");
            pw = new PrintWriter(presupuesto);
        }catch(Exception e){

        }finally{
            br.close();
        }
    }


}
