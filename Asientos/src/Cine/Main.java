package Cine;

import java.util.Scanner;

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        // Solicitar información de la película
        System.out.println("Ingrese la información de la película:");
        System.out.print("Nombre: ");
        String nombre = scanner.nextLine();
        System.out.print("Género: ");
        String genero = scanner.nextLine();
        System.out.print("Duración (en minutos): ");
        int duracion = scanner.nextInt();
        String formato = "2D";
        String dia="0";
        // Crear una instancia de Peliculas
        Peliculas pelicula = new Peliculas(nombre,formato,genero,duracion);

        // Crear una instancia de Sala1 asociada a la película
        Sala sala = new Sala(pelicula);

        // Interacción con el usuario
        int opcion;
        do {
            System.out.println("\nMenú:");
            System.out.println("1. Mostrar estado de los asientos");
            System.out.println("2. Registrar un asiento ocupado");
            System.out.println("3. Salir");
            System.out.print("Seleccione una opción: ");
            opcion = scanner.nextInt();

            switch (opcion) {
                case 1:
                    sala.mostrarEstadoAsientos();
                    break;
                case 2:
                    System.out.println("  ");
                    break;
                case 3:
                    System.out.println("¡Gracias por usar el sistema!");
                    break;
                default:
                    System.out.println("Opción no válida. Intente de nuevo.");
            }
        } while (opcion != 3);
    }
}


