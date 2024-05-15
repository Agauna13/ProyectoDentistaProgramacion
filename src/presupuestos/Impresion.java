package presupuestos;

import java.io.*;

public class Impresion {

    private PrintWriter pw;

    public Impresion(String fileName, String contenido) throws IOException {
        pw = new PrintWriter("C:\\Users\\parki\\Desktop\\presupuestos\\" + fileName + ".txt");

        // Escribir el contenido en el archivo
        pw.println(contenido);
        pw.close();

    }

}

