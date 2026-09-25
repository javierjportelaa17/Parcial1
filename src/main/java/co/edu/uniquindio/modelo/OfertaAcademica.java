package co.edu.uniquindio.modelo;
import java.util.ArrayList;
import java.util.List;

public class OfertaAcademica implements Cloneable {
    private String periodo;
    private List<ProgramaFormacion> programas;

    public OfertaAcademica(String periodo) {
        this.periodo = periodo;
        this.programas = new ArrayList<>();
    }

    public void agregarPrograma(ProgramaFormacion p) { this.programas.add(p); }
    public List<ProgramaFormacion> getProgramas() { return programas; }
    public void setPeriodo(String periodo) { this.periodo = periodo; }

    @Override
    public OfertaAcademica clone() {
        try {
            OfertaAcademica clon = (OfertaAcademica) super.clone();
            clon.programas = new ArrayList<>();
            for (ProgramaFormacion p : this.programas) {
                clon.programas.add(p.clone()); // Copia profunda
            }
            return clon;
        } catch (CloneNotSupportedException e) {
            throw new AssertionError();
        }
    }
}