package Cine;

public class Peliculas {
    private String nombre;
    private String genero;
    private int duracion;
    private String formato;

    public Peliculas(String nombre, String formato, String genero, int duracion) {
        this.nombre = nombre;
        this.formato = formato;
        this.genero = genero;
        this.duracion = duracion;
    }

    public String getNombre() {
        return nombre;
    }

    public String getGenero() {
        return genero;
    }

    public int getDuracion() {
        return duracion;
    }

    public String getFormato() {
        return formato;
    }

}
