package co.edu.uniquindio.modelo;

public class ProgramaFormacion implements Cloneable {
    private String codigo;
    private String nombre;
    private int cuposDisponibles;
    private double valorMensual;

    public ProgramaFormacion(String codigo, String nombre, int cuposDisponibles, double valorMensual) {
        this.codigo = codigo;
        this.nombre = nombre;
        this.cuposDisponibles = cuposDisponibles;
        this.valorMensual = valorMensual;
    }

    public void reducirCupo() {
        if (cuposDisponibles > 0) cuposDisponibles--;
    }

    public int getCuposDisponibles() { return cuposDisponibles; }
    public double getValorMensual() { return valorMensual; }
    public String getNombre() { return nombre; }

    @Override
    public ProgramaFormacion clone() {
        try {
            return (ProgramaFormacion) super.clone();
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}