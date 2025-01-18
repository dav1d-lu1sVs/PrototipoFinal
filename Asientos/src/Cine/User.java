package Cine;

import java.util.Scanner;
import java.util.regex.Pattern;
import java.io.FileWriter;
import java.io.BufferedReader;
import java.io.FileReader;

public class User {
    private String nombre;
    private String apellido;
    private String cedula;
    private String correo;
    private String direccion;
    ValidarCorreo validar = new ValidarCorreo();

    public void ObtenerDatos() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Cedula: ");
        cedula = scanner.next();
        if (verificarCedula(cedula)) {
            System.out.println("Usuario Encontrado");
            return;
        }
        System.out.print("Nombre: ");
        nombre = scanner.next();
        System.out.print("Apellido: ");
        apellido = scanner.next();
        System.out.print("Correo: ");
        correo = scanner.next();
        while (!validar.validarCorreo(correo)) {
            System.out.println("El correo ingresado no es válido. Intente de nuevo:");
            correo = scanner.next();
        }
        System.out.print("Direccion: ");
        direccion = scanner.next();

        guardarEnArchivo();
    }
    private boolean verificarCedula(String cedula) {
        try (BufferedReader reader = new BufferedReader(new FileReader("registro_usuarios.txt"))) {
            String linea;
            while ((linea = reader.readLine()) != null) {
                if (linea.startsWith("Cedula: ")) {
                    String cedulaExistente = linea.substring(8).trim();
                    if (cedulaExistente.equals(cedula)) {
                        System.out.println(reader.readLine()); // Nombre
                        System.out.println(reader.readLine()); // Apellido
                        System.out.println(reader.readLine()); // Correo
                        System.out.println(reader.readLine()); // Dirección
                        return true;
                    }
                }
            }
        } catch (Exception e) {
            System.out.println("Error al leer el archivo de información personal: " + e.getMessage());
        }
        return false;
    }

    private boolean validarCorreo(String correo) {

        String regex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+\\.[A-Za-z]{2,6}$";
        return Pattern.matches(regex, correo);
    }

    private void guardarEnArchivo() {
        try (FileWriter writer = new FileWriter("registro_usuarios.txt", true)) {
            writer.write("Cedula: " + cedula + "\n");
            writer.write("Nombre: " + nombre + "\n");
            writer.write("Apellido: " + apellido + "\n");
            writer.write("Correo: " + correo + "\n");
            writer.write("Direccion: " + direccion + "\n");
            writer.write("---------------------------\n");
        } catch (Exception e) {
            System.out.println("Ocurrió un error al guardar la información: " + e.getMessage());
        }
    }

    public String getNombre() {return nombre;    }

    public String getApellido() {return apellido;    }

    public String getCedula() {return cedula;}

    public String getCorreo() {return correo;}

    public String getDireccion() {return direccion;}

    public static void main(String[] args) {
        User user = new User();
        user.ObtenerDatos();
    }
}
