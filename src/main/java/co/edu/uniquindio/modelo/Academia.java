package co.edu.uniquindio.modelo;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

public class Academia {
    private String nombreComercial;
    private String nit;
    private List<Estudiante> estudiantes = new ArrayList<>();
    private List<Docente> docentes = new ArrayList<>();
    private List<ProgramaFormacion> programas = new ArrayList<>();
    private List<Matricula> matriculas = new ArrayList<>();

    public Academia(String nombreComercial, String nit) {
        this.nombreComercial = nombreComercial;
        this.nit = nit;
    }

    public void registrarEstudiante(Estudiante e) { estudiantes.add(e); }
    public void registrarDocente(Docente d) { docentes.add(d); }
    public void registrarPrograma(ProgramaFormacion p) { programas.add(p); }
    public void registrarMatricula(Matricula m) { matriculas.add(m); }

    public Estudiante buscarEstudiantePorTelefono(String telefono) {
        return estudiantes.stream()
                .filter(e -> e.getTelefono().equals(telefono))
                .findFirst()
                .orElse(null);
    }

    public double calcularIngresosPorRangoFechas(LocalDate inicio, LocalDate fin) {
        return matriculas.stream()
                .filter(m -> !m.getFechaInicio().isBefore(inicio) && !m.getFechaInicio().isAfter(fin))
                .mapToDouble(m -> m.getPrograma().getValorMensual() * (1 - m.getDescuento()))
                .sum();
    }
}