package co.edu.uniquindio.modelo;

public class ServicioAdicional {
    private String codigo;
    private String nombre;
    private double precio;
    private boolean disponible;

    public ServicioAdicional(String codigo, String nombre, double precio, boolean disponible) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.precio = precio;
        this.disponible = disponible;
    }

    public double getPrecio() { return precio; }
    public String getNombre() { return nombre; }
}