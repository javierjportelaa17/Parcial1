package co.edu.uniquindio.modelo;

public class Docente {
    private String identificacion;
    private String nombre;
    private String idiomaEspecialidad;
    private String telefono;
    private double tarifaPorSesion;

    public Docente(String identificacion, String nombre, String idiomaEspecialidad, String telefono, double tarifaPorSesion) {
        this.identificacion = identificacion;
        this.nombre = nombre;
        this.idiomaEspecialidad = idiomaEspecialidad;
        this.telefono = telefono;
        this.tarifaPorSesion = tarifaPorSesion;
    }

    public String getNombre() {
        return nombre;
    }
}
