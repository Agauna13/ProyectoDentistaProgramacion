package presupuestos;

import java.io.*;

public class Impresion {
    private String fileName;
    static File presupuesto = null;
    static FileReader fr = null;
    static FileWriter fw = null;
    static BufferedReader br = null;
    static PrintWriter pw = null;

    public Impresion(String fileName) throws IOException {
        this.fileName = fileName;

        try{
            presupuesto = new File("C:\\Users\\parki\\Desktop\\presupuestos\\" + fileName);//para pedir el nombre del archivo de texto a crear

            pw = new PrintWriter(presupuesto);
        }catch(Exception e){

        }finally{
            br.close();
        }
    }


}


/*import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;

public class TextWriter {
    private String filePath; // Ruta del archivo

    // Constructor
    public TextWriter(String filePath) {
        this.filePath = filePath;
    }

    // Método para escribir en el archivo de texto
    public void writeToFile(ArrayList<String> lines) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(filePath, true))) {
            Iterator<String> iterator = lines.iterator();
            while (iterator.hasNext()) {
                String line = iterator.next();
                writer.write(line);
                writer.newLine(); // Agrega una nueva línea después de cada línea de texto
            }
            writer.flush(); // Vacía el buffer para asegurarse de que todos los datos se escriban en el archivo
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
1*/