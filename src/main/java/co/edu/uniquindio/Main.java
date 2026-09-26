package co.edu.uniquindio;

import co.edu.uniquindio.modelo.*;
import co.edu.uniquindio.modelo.comprobantes.*;
import co.edu.uniquindio.modelo.entregable.*;
import java.time.LocalDate;

public class Main {
    public static void main(String[] args) {
        Academia academia = new Academia("LinguaPlus", "900.123.456-7");
        System.out.println("=== SISTEMA " + academia + " INICIADO ===\n");


        OfertaAcademica ofertaBase = new OfertaAcademica("Base");
        ProgramaFormacion p1 = new ProgramaFormacion("P01", "Inglés B1", 20, 150000);
        ofertaBase.agregarPrograma(p1);

        OfertaAcademica oferta2026_2 = ofertaBase.clone();
        oferta2026_2.getProgramas().get(0).reducirCupo();
        System.out.println("Cupos Base: " + ofertaBase.getProgramas().get(0).getCuposDisponibles() +
                " | Cupos Clonados: " + oferta2026_2.getProgramas().get(0).getCuposDisponibles());


        Estudiante est = new Estudiante("101", "Ana Gomez", "28", "ana@correo.com", 22, LocalDate.now());
        Docente doc = new Docente("D1", "Carlos Ruiz", "Inglés", "555-0000", 60000);
        ServicioAdicional serv = new ServicioAdicional("S1", "Tutoría Extra", 50000, true);

        try {
            Matricula matricula = new Matricula.Builder()
                    .conEstudiante(est)
                    .conPrograma(p1)
                    .conDocente(doc)
                    .conServicioAdicional(serv)
                    .conDescuento(0.10)
                    .build();

            academia.registrarMatricula(matricula);
            System.out.println("\nMatrícula #" + matricula.getNumeroMatricula() + " creada con Docente: " + matricula.getDocenteTutor().getNombre());


            GeneradorComprobante genPdf = new GeneradorPdf();
            genPdf.procesarPago(matricula);
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }


        System.out.println("\n--- Kit Modalidad Presencial ---");
        FabricaEntregable fabrica = new FabricaPresencial();
        fabrica.crearMaterial().entregarMaterial();
        fabrica.crearCarne().emitirCarne();


        System.out.println("\n¿Teléfono '28' es número perfecto?: " + est.esTelefonoNumeroPerfecto());
    }
}