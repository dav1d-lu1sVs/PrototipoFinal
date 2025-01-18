package Cine;

import java.io.FileReader;
import java.io.BufferedReader;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.List;
import java.util.Scanner;
import java.io.FileWriter;
import java.time.*;


public class Sala {
    private int FILAS = 4;
    private int COLUMNAS = 4;
    private String[][] asientos;
    private Peliculas pelicula;
    private User user;
    private int cantidadBoletos;
    private FechaEstreno fechaestreno;
    private int libres;
    private int ocupados;
    char[] letras = {'A', 'B', 'C', 'D'};
    String[] numeros = {"1", "2", "3", "4"};

    public int getCantidadBoletos() {
        return cantidadBoletos;
    }

    public Sala(Peliculas pelicula) {
        this.asientos = new String[FILAS][COLUMNAS];
        this.libres = FILAS * COLUMNAS;
        this.ocupados = 0;
        this.pelicula = pelicula;
        this.user = user;
        this.fechaestreno = fechaestreno;

        for (int fila = 0; fila < FILAS; fila++) {
            char letra = letras[fila];
            for (int columna = 0; columna < COLUMNAS; columna++) {
                asientos[fila][columna] = letras[fila] + numeros[columna];
            }
        }

        if (fechaestreno.viernesFinal()) {
            liberarAsientos();
            System.out.println(" ");
            System.out.println("Funcion Nueva");
        } else {
            cargarAsientosDesdeArchivo();
            System.out.println(" ");
            System.out.println("Aun hay asientos disponible, aprovecha!!");
        }


    }

    private void cargarAsientosDesdeArchivo() {
        if (fechaEstrenoYaPaso()) {
            try {
                Files.deleteIfExists(Paths.get("asientos_ocupados.txt"));
                System.out.println("El archivo de asientos ocupados ha sido borrado porque la fecha ya pasó.");
            } catch (Exception e) {
                System.out.println("Error, no sabemos lo que hacemos");
            }
        } else {

            try (BufferedReader reader = new BufferedReader(new FileReader("asientos_ocupados.txt"))) {
                String asiento;
                while ((asiento = reader.readLine()) != null) {
                    marcarAsientoOcupado(asiento.trim());
                }
            } catch (Exception e) {
                System.out.println("Algo malo paso, si al golpear la computadora 3 veces no funciona busca ayuda");
            }
        }
    }

    private boolean fechaEstrenoYaPaso() {
        LocalDateTime fechaGuardada = obtenerFechaEstrenoGuardada();
        if (fechaGuardada == null) {
            return false;
        }
        LocalDateTime ahora = LocalDateTime.now();
        return ahora.isAfter(fechaGuardada);
    }

    private LocalDateTime obtenerFechaEstrenoGuardada() {
        try {
            List<String> lineas = Files.readAllLines(Paths.get("proximo_viernes.txt"));
            if (!lineas.isEmpty()) {
                String ultimaFecha = lineas.get(lineas.size() - 1);
                return LocalDateTime.parse(ultimaFecha);
            }
        } catch (Exception e) {
            System.out.println("Error en el sistema, ups:).");
        }
        return null;
    }


    private void liberarAsientos() {
        if (fechaEstrenoYaPaso()) {
            try {
                Files.deleteIfExists(Paths.get("asientos_ocupados.txt"));
                Files.deleteIfExists(Paths.get("asientosreservas.txt"));
                System.out.println("Se han liberado todos los asientos y se han borrado las reservas.");
                for (int fila = 0; fila < letras.length; fila++) {
                    for (int columna = 0; columna < numeros.length; columna++) {
                        asientos[fila][columna] = letras[fila] + numeros[columna];
                    }
                }
                libres = FILAS * COLUMNAS;
                ocupados = 0;
            } catch (Exception e) {
                System.out.println("Aun no me graduo que esperabas??");
            }
        } else {
            System.out.println("Aún se puede entrar a la función.");
        }
    }




    private void marcarAsientoOcupado(String asiento) {
        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (asientos[fila][columna].equals(asiento)) {
                    asientos[fila][columna] = "  X  ";
                    ocupados++;
                    libres--;
                    break;
                }
            }
        }
    }

    public void comprarBoletos(User user) {
        this.user = user;
        Scanner scanner = new Scanner(System.in);
        System.out.print("¿Cuántos boletos deseas comprar? ");
        cantidadBoletos = scanner.nextInt();
        if (cantidadBoletos > libres) {
            System.out.println("No hay suficientes asientos disponibles. Intenta nuevamente.");
            return;
        }

        for (int i = 0; i < cantidadBoletos; i++) {
            mostrarEstadoAsientos();
            System.out.print("Selecciona el asiento " + (i + 1) + " (ejemplo: A1): ");
            String asiento = scanner.next();
            boolean encontrado = false;
            for (int fila = 0; fila < FILAS; fila++) {
                for (int columna = 0; columna < COLUMNAS; columna++) {
                    if (asientos[fila][columna].equals(asiento)) {
                        asientos[fila][columna] = "  X  ";
                        ocupados++;
                        libres--;
                        encontrado = true;
                        System.out.println("Asiento " + asiento + " marcado como ocupado.");
                        guardarAsientosEnArchivo(asiento);
                        guardarReservas(asiento);
                        break;
                    }
                }
                if (encontrado) break;
            }
            if (!encontrado) {
                System.out.println("Asiento no válido o ya ocupado. Intenta nuevamente.");
                i--;
            }
        }
        System.out.println("Compra realizada con éxito.");
    }

    public void mostrarEstadoAsientos() {
        System.out.println("\nInformación de la película:");
        System.out.println("Nombre: " + pelicula.getNombre());
        System.out.println("Formato: " + pelicula.getFormato());
        System.out.println("Género: " + pelicula.getGenero());
        System.out.println("Duración: " + pelicula.getDuracion() + " minutos");
        System.out.println(fechaestreno.viernesqueviene());
        System.out.println("\nEstado de los asientos ( X = ocupado, [A1,A2...] = disponible):");

        for (int fila = 0; fila < FILAS; fila++) {
            for (int columna = 0; columna < COLUMNAS; columna++) {
                if (!asientos[fila][columna].equals("  X  ")) {
                    System.out.print(" [" + asientos[fila][columna] + "]  ");
                } else {
                    System.out.print("  X  \t");
                }
            }
            System.out.println("\n [   MESA   ]  [   MESA   ]");
            System.out.println();
        }
        System.out.println("__________________________");
        System.out.println("         Pantalla          ");
        mostrarDisponibilidad();
    }

    public void mostrarDisponibilidad() {
        System.out.println(" ");
        System.out.println("Asientos disponibles: " + libres);
        System.out.println("Asientos ocupados: " + ocupados);
    }

    private void guardarAsientosEnArchivo(String asiento) {
        try (FileWriter writer = new FileWriter("asientos_ocupados.txt", true)) {
            writer.write(asiento + "\n");
        } catch (Exception e) {
            System.out.println("Alguien vio al ingeniero que hizo esto???");
        }
    }
    private void guardarReservas(String asiento) {
        try (FileWriter writer = new FileWriter("asientosreservas.txt", true)) {
            writer.write("Asiento: " + asiento + " | Apellido: " + user.getApellido() +
                    " | Nombre: " + user.getNombre() + " | Cédula: " + user.getCedula() + "\n");
        } catch (Exception e) {
            System.out.println("No nos culpes tal vez es tu computadora");
        }
    }

public static void main(String[] args) {
        Peliculas pelicula = new Peliculas("Inception", "2D", "Ciencia Ficción", 148);
        User user1 = new User();
        user1.ObtenerDatos();
        Sala sala = new Sala(pelicula);
        sala.mostrarEstadoAsientos();
        sala.comprarBoletos(user1);
        sala.mostrarEstadoAsientos();
    }
}




