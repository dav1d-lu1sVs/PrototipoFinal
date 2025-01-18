package Cine;

import java.time.LocalDate;
import java.time.LocalTime;

public class Boleto {
    private String asiento;
    private User user;
    private Peliculas pelicula;
    private LocalDate fechaCompra;
    private LocalTime horaCompra;

    public Boleto(String asiento, User user, Peliculas pelicula) {
        this.asiento = asiento;
        this.user = user;
        this.pelicula = pelicula;
        this.fechaCompra = LocalDate.now();
        this.horaCompra = LocalTime.now();
    }


    public void mostrarDetalles() {
        System.out.println("Boleto de Cine");
        System.out.println("---------------------------");
        System.out.println("Película: " + pelicula.getNombre());
        System.out.println("Género: " + pelicula.getGenero());
        System.out.println("Formato: " + pelicula.getFormato());
        System.out.println("Asiento: " + asiento);
        System.out.println("Nombre: " + user.getNombre() + " " + user.getApellido());
        System.out.println("Cédula: " + user.getCedula());
        System.out.println("Fecha de compra: " + fechaCompra);
        System.out.println("Hora de compra: " + horaCompra);
    }

    public static void main(String[] args) {

        Peliculas pelicula = new Peliculas("Inception", "2D", "Ciencia Ficción", 148);
        User user1 = new User();
        Sala sala = new Sala(pelicula);
        sala.comprarBoletos(user1);
        Boleto boleto = new Boleto("A1", user1, pelicula);
        boleto.mostrarDetalles();
    }
}

