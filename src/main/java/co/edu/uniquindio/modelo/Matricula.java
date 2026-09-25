package co.edu.uniquindio.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Matricula {
    private final int numeroMatricula;
    private final Estudiante estudiante;
    private final ProgramaFormacion programa;
    private final Docente docenteTutor;
    private final List<ServicioAdicional> serviciosAdicionales;
    private final double descuento;
    private final LocalDate fechaInicio;

    private Matricula(Builder builder) {
        this.numeroMatricula = ConsecutivoMatricula.getInstance().generarSiguiente();
        this.estudiante = builder.estudiante;
        this.programa = builder.programa;
        this.docenteTutor = builder.docenteTutor;
        this.serviciosAdicionales = builder.serviciosAdicionales;
        this.descuento = builder.descuento;
        this.fechaInicio = builder.fechaInicio != null ? builder.fechaInicio : LocalDate.now();
    }

    public int getNumeroMatricula() { return numeroMatricula; }
    public Estudiante getEstudiante() { return estudiante; }
    public ProgramaFormacion getPrograma() { return programa; }
    public double getDescuento() { return descuento; }
    public LocalDate getFechaInicio() { return fechaInicio; }

    public static class Builder {
        private Estudiante estudiante;
        private ProgramaFormacion programa;
        private Docente docenteTutor;
        private List<ServicioAdicional> serviciosAdicionales = new ArrayList<>();
        private double descuento = 0.0;
        private LocalDate fechaInicio;

        public Builder conEstudiante(Estudiante e) { this.estudiante = e; return this; }
        public Builder conPrograma(ProgramaFormacion p) { this.programa = p; return this; }
        public Builder conDocente(Docente d) { this.docenteTutor = d; return this; }
        public Builder conServicioAdicional(ServicioAdicional s) {
            this.serviciosAdicionales.add(s);
            return this;
        }
        public Builder conDescuento(double d) { this.descuento = d; return this; }
        public Builder conFechaInicio(LocalDate fecha) { this.fechaInicio = fecha; return this; }

        public Matricula build() {
            if (this.programa == null) {
                throw new IllegalStateException("Error: La matrícula requiere un programa de formación.");
            }
            if (this.descuento > 0.30) {
                throw new IllegalArgumentException("Error: El descuento no puede superar el 30%.");
            }
            return new Matricula(this);
        }
    }
}