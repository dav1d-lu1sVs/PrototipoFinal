package Cine;

import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import java.util.Scanner;

public class FacturaUsuario {
    private User user;
    private Peliculas pelicula;
    private Sala sala;
    private FacturaInfo facturaInfo;
    private String direccion= "OE38 PEDRO ALVARADO N56-10";
    private String cajero="Carlos";
    private LocalDate fechaCompra;
    private LocalTime hora;
    private String horacompra;
    private String numeroFactura;



    public FacturaUsuario(User user, Sala sala) {
        this.user = user;
        this.direccion = direccion;
        this.cajero=cajero;
        this.sala = sala;
        this.fechaCompra = LocalDate.now();
        this.hora = LocalTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("HH:mm:ss");
        this.horacompra = hora.format(formatter);
        this.numeroFactura = generarNumeroFactura();
        this.facturaInfo = new FacturaInfo(sala);
    }

    private String generarNumeroFactura() {
        Random random = new Random();
        StringBuilder numero = new StringBuilder();
        for (int i = 0; i < 6; i++) {
            numero.append(random.nextInt(10));
        }
        return numero.toString();
    }

    public void infodefactura(){
        System.out.println("----------------------------------------------------------------------");
        System.out.println("                       Sensa Cine");
        System.out.println("          Sucursal: "+direccion);
        System.out.println("                  Telefono: 0984470605");
        System.out.println("                Numero de factura: " +numeroFactura);
        System.out.println("  Fecha de compra: " + fechaCompra + "     Hora de compra: " + horacompra);
        System.out.println("======================================================================");
        System.out.println("  Cajero: "+  cajero);
    }

    public void MostrarDatosUsu() {
        Scanner scanner = new Scanner(System.in);
        System.out.print("Consumidor Final' (Sí/No)?");
        String respuesta = scanner.nextLine();
        facturaInfo.CantDePago();
        if (respuesta.equals("Si")) {
            infodefactura();
            facturaConsumidorFinal();
        } else {
            infodefactura();
            user.ObtenerDatos();
        }
        System.out.println("======================================================================");
        facturaInfo.MostrarDetalle();

    }

    public void facturaConsumidorFinal(){
        System.out.println(" Cedula: "+ null);
        System.out.println(" Nombre: "+null);
        System.out.println(" Apellido: "+null);
        System.out.println(" Correo: "+null);
        System.out.println(" Direccion: "+null);
    }

    public static void main(String[] args) {
        User user1 = new User();
        Peliculas pelicula = new Peliculas("Inception", "2D", "Ciencia Ficción", 148);
        Sala sala = new Sala(pelicula);
        FacturaUsuario facturaUsuario = new FacturaUsuario(user1,sala);
        facturaUsuario.MostrarDatosUsu();
    }
}



