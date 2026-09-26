package co.edu.uniquindio.controlador;

import co.edu.uniquindio.modelo.*;
import co.edu.uniquindio.modelo.comprobantes.GeneradorComprobante;
import co.edu.uniquindio.modelo.comprobantes.GeneradorExcel;
import co.edu.uniquindio.modelo.comprobantes.GeneradorPdf;
import co.edu.uniquindio.modelo.entregable.CarneEstudiantil;
import co.edu.uniquindio.modelo.entregable.FabricaEntregable;
import co.edu.uniquindio.modelo.entregable.FabricaPresencial;
import co.edu.uniquindio.modelo.entregable.FabricaVirtual;
import co.edu.uniquindio.modelo.entregable.MaterialEstudio;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.util.StringConverter;
import java.time.LocalDate;

public class MatriculaControlador {
    @FXML private TextField txtNombre;
    @FXML private TextField txtDocumento;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDescuento;
    @FXML private ComboBox<ProgramaFormacion> cmbPrograma;
    @FXML private ComboBox<Docente> cmbDocente;
    @FXML private ComboBox<ServicioAdicional> cmbServicio;
    @FXML private ComboBox<String> cmbModalidad;
    @FXML private ComboBox<String> cmbFormato;
    @FXML private Label lblCupos;
    @FXML private ListView<String> lstMatriculas;
    @FXML private TextField txtBuscarTelefono;
    @FXML private DatePicker dpDesde;
    @FXML private DatePicker dpHasta;

    private final Academia academia = new Academia("LinguaPlus", "900.123.456-7");
    private final OfertaAcademica ofertaBase = new OfertaAcademica("Base");
    private OfertaAcademica periodoActual;

    @FXML
    public void initialize() {
        ofertaBase.agregarPrograma(new ProgramaFormacion("P01", "Inglés B1", 20, 150000));
        ofertaBase.agregarPrograma(new ProgramaFormacion("P02", "Francés A1", 15, 180000));
        ofertaBase.agregarPrograma(new ProgramaPersonalizado("P03", "Inglés personalizado", 5, 300000, 8, "B2", "Certificación internacional"));
        periodoActual = ofertaBase.clone();
        periodoActual.setPeriodo("2026-2");

        Docente docente1 = new Docente("D1", "Carlos Ruiz", "Inglés", "555-0000", 60000);
        Docente docente2 = new Docente("D2", "Laura Gómez", "Francés", "555-0001", 70000);
        academia.registrarDocente(docente1);
        academia.registrarDocente(docente2);

        cmbPrograma.getItems().setAll(periodoActual.getProgramas());
        cmbPrograma.setConverter(convertidor(ProgramaFormacion::getNombre));
        cmbDocente.getItems().setAll(docente1, docente2);
        cmbDocente.setConverter(convertidor(Docente::getNombre));
        cmbServicio.getItems().setAll(
                new ServicioAdicional("S1", "Tutoría extra", 50000, true),
                new ServicioAdicional("S2", "Simulacro de certificación", 120000, true));
        cmbServicio.setConverter(convertidor(s -> s.getNombre() + " - $" + String.format("%,.0f", s.getPrecio())));
        cmbModalidad.getItems().setAll("Presencial", "Virtual");
        cmbModalidad.getSelectionModel().selectFirst();
        cmbFormato.getItems().setAll("PDF", "Excel");
        cmbFormato.getSelectionModel().selectFirst();

        cmbDocente.setDisable(true);
        cmbPrograma.valueProperty().addListener((obs, anterior, programa) -> {
            boolean personalizado = programa instanceof ProgramaPersonalizado;
            cmbDocente.setDisable(!personalizado);
            if (!personalizado) cmbDocente.setValue(null);
            actualizarCupos();
        });
    }

    @FXML
    public void registrarMatriculaAction(ActionEvent event) {
        try {
            String textoDescuento = txtDescuento.getText().trim();
            double descuento = textoDescuento.isEmpty() ? 0 : Double.parseDouble(textoDescuento) / 100.0;

            Estudiante estudiante = new Estudiante(txtDocumento.getText(), txtNombre.getText(), txtTelefono.getText().trim(),
                    "correo@test.com", 20, LocalDate.now());

            Matricula.Builder builder = new Matricula.Builder()
                    .conEstudiante(estudiante)
                    .conPrograma(cmbPrograma.getValue())
                    .conDocente(cmbDocente.getValue())
                    .conDescuento(descuento);
            if (cmbServicio.getValue() != null) {
                builder.conServicioAdicional(cmbServicio.getValue());
            }
            Matricula nuevaMatricula = builder.build();

            academia.registrarEstudiante(estudiante);
            academia.registrarMatricula(nuevaMatricula);
            nuevaMatricula.getPrograma().reducirCupo();

            GeneradorComprobante generador = "Excel".equals(cmbFormato.getValue()) ? new GeneradorExcel() : new GeneradorPdf();
            generador.procesarPago(nuevaMatricula);

            FabricaEntregable fabrica = "Virtual".equals(cmbModalidad.getValue()) ? new FabricaVirtual() : new FabricaPresencial();
            MaterialEstudio material = fabrica.crearMaterial();
            CarneEstudiantil carne = fabrica.crearCarne();
            material.entregarMaterial();
            carne.emitirCarne();

            lstMatriculas.getItems().add("#" + nuevaMatricula.getNumeroMatricula() + " - " + estudiante.getNombreCompleto()
                    + " - " + nuevaMatricula.getPrograma().getNombre());
            actualizarCupos();

            mostrarMensaje("Éxito", "Matrícula #" + nuevaMatricula.getNumeroMatricula() + " registrada.\n"
                    + "Comprobante generado en formato " + cmbFormato.getValue() + ".\n"
                    + "Entregables (" + cmbModalidad.getValue() + "): " + material.getClass().getSimpleName()
                    + " + " + carne.getClass().getSimpleName(), Alert.AlertType.INFORMATION);
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje("Error de Validación", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarMensaje("Error", "Verifique los datos ingresados.", Alert.AlertType.ERROR);
        }
    }

    @FXML
    public void buscarPorTelefonoAction(ActionEvent event) {
        String telefono = txtBuscarTelefono.getText().trim();
        Estudiante estudiante = academia.buscarEstudiantePorTelefono(telefono);
        if (estudiante == null) {
            mostrarMensaje("Consulta", "No hay ningún estudiante con el teléfono " + telefono + ".", Alert.AlertType.INFORMATION);
            return;
        }
        mostrarMensaje("Consulta", "Estudiante: " + estudiante.getNombreCompleto()
                + "\n¿El teléfono es número perfecto? " + (estudiante.esTelefonoNumeroPerfecto() ? "SÍ" : "NO"),
                Alert.AlertType.INFORMATION);
    }

    @FXML
    public void calcularIngresosAction(ActionEvent event) {
        if (dpDesde.getValue() == null || dpHasta.getValue() == null) {
            mostrarMensaje("Error", "Seleccione la fecha inicial y la final.", Alert.AlertType.ERROR);
            return;
        }
        double total = academia.calcularIngresosPorRangoFechas(dpDesde.getValue(), dpHasta.getValue());
        mostrarMensaje("Ingresos", "Ingresos del periodo: $" + String.format("%,.0f", total), Alert.AlertType.INFORMATION);
    }

    private void actualizarCupos() {
        ProgramaFormacion programa = cmbPrograma.getValue();
        if (programa == null) {
            lblCupos.setText("");
            return;
        }
        int indice = periodoActual.getProgramas().indexOf(programa);
        ProgramaFormacion enOfertaBase = ofertaBase.getProgramas().get(indice);
        lblCupos.setText("Cupos de " + programa.getNombre() + " -> oferta base: " + enOfertaBase.getCuposDisponibles()
                + " | periodo 2026-2: " + programa.getCuposDisponibles());
    }

    private <T> StringConverter<T> convertidor(java.util.function.Function<T, String> texto) {
        return new StringConverter<>() {
            @Override
            public String toString(T objeto) { return objeto == null ? "" : texto.apply(objeto); }

            @Override
            public T fromString(String cadena) { return null; }
        };
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}
