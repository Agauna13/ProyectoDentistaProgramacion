package Empleados;

import java.util.*;

public class Usuarios {
    private static final Map<String, String> credenciales = new HashMap<>();
    private String USER;
    private String PASS;

    static {
        // Agregar las credenciales predefinidas
        credenciales.put("Alan", "12345678");
        credenciales.put("Rafa", "12345678");
        credenciales.put("Mateu", "12345678");
        credenciales.put("root", "mypass");
    }

    public Usuarios(String USER, String PASS){
        this.USER = USER;
        this.PASS = PASS;
    }

    private boolean verificar(String USER, String PASS){
        return (credenciales.containsKey(this.USER) && credenciales.containsValue(this.PASS));
    }

    public boolean getUsuario(String USER, String PASS){
        return verificar(this.USER,
                this.PASS);
    }

    public String getUsername(){
        return  USER;
    }



}
