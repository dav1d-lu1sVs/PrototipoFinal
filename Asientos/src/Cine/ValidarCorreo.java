package Cine;

import java.io.*;
import java.util.*;

public class ValidarCorreo {

    private String regex;

    public ValidarCorreo() {
        String[] dominios = leerDesdeArchivo("dominios.txt");
        String[] terminaciones = leerDesdeArchivo("terminaciones.txt");
        regex = Regex(dominios, terminaciones);
    }

    public boolean validarCorreo(String correo) {
        return correo.matches(regex);
    }

    private String[] leerDesdeArchivo(String archivo) {
        List<String> lista = new ArrayList<>();
        try (BufferedReader br = new BufferedReader(new FileReader(archivo))) {
            String linea;
            while ((linea = br.readLine()) != null) {
                lista.add(linea.trim());
            }
        } catch (Exception e) {
            System.out.println("No se pudo leer");
        }
        return lista.toArray(new String[0]);
    }

    private String Regex(String[] dominios, String[] terminaciones) {
        String regexDominios = String.join("|", dominios);
        String regexTerminaciones = String.join("|", terminaciones);
        return "^[A-Za-z0-9+_.-]+@(" + regexDominios + ")(" + regexTerminaciones + ")$";
    }

}


