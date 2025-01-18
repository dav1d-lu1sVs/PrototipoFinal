package Cine;

import java.util.Scanner;

public class FacturaInfo {
    private Sala sala;
    private double precio;
    private double apagar;
    private double iva;
    private double totalBoletos;
    private double ivaTotal;
    private String detalle;
    private Peliculas pelicula;
    private double plata;
    private double vuelto;

    public FacturaInfo(Sala sala) {
        this.sala = sala;
        this.precio = 50;
        this.iva = 0.15;
        this.detalle = detalle;
    }

    public void CantDePago() {
        Scanner scanner = new Scanner(System.in);
        totalBoletos = sala.getCantidadBoletos() * precio;
        ivaTotal = totalBoletos * iva;
        apagar = totalBoletos + ivaTotal;

        System.out.println("Total sin IVA: " + totalBoletos);
        System.out.println("IVA (15%): " + ivaTotal);
        System.out.println("Total a pagar con IVA: " + apagar);
        System.out.print("Ingrese la plata: ");
        plata = scanner.nextInt();
        scanner.nextLine();
        vuelto=plata-apagar;
        System.out.println("Su vuelto es: "+vuelto);
    }




    public void MostrarDetalle() {
        System.out.println("  DESCRIP                  CANT             P.UNIT             P.TOTAL");
        System.out.println("======================================================================");
        System.out.println("Boletos SensaCine              " + sala.getCantidadBoletos() + "              $50                 " + totalBoletos);
        System.out.println("     Subtotal                                                     " + totalBoletos);
        System.out.println("     Total: IVA 15%                                               " + ivaTotal);
        System.out.println("                                                          ------------");
        System.out.println("     TOTAL:                                                       "+apagar);
        System.out.println("----------------------------------------------------------------------");
        System.out.println("  Pago en:                  Valor                               Vuelto");
        System.out.println("                            "+ plata+"                                  "+vuelto);
        System.out.println("----------------------------------------------------------------------");
    }

    public static void main(String[] args) {
        User user1 = new User();
        Peliculas pelicula = new Peliculas("Inception", "2D", "Ciencia Ficción", 148);
        Sala sala = new Sala(pelicula);
        FacturaInfo facturaInfo = new FacturaInfo(sala);
        facturaInfo.MostrarDetalle();
    }
}

