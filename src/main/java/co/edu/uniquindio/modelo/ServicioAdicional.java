package co.edu.uniquindio.modelo;

public class ProgramaPersonalizado extends ProgramaFormacion {
    private int cantidadSesionesTutor;
    private String nivelIdiomaRequerido;
    private String objetivosEstudiante;

    public ProgramaPersonalizado(String codigo, String nombre, int cupos, double valor, int sesiones, String nivel, String objetivos) {
        super(codigo, nombre, cupos, valor);
        this.cantidadSesionesTutor = sesiones;
        this.nivelIdiomaRequerido = nivel;
        this.objetivosEstudiante = objetivos;
    }

    @Override
    public ProgramaPersonalizado clone() {
        return (ProgramaPersonalizado) super.clone();
    }
}