package co.edu.uniquindio.controlador;

import co.edu.uniquindio.modelo.*;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import java.time.LocalDate;

public class MatriculaControlador {
    @FXML private TextField txtNombre;
    @FXML private TextField txtTelefono;
    @FXML private TextField txtDescuento;

    private ProgramaFormacion programaBase = new ProgramaFormacion("P01", "Inglés B1", 20, 150000);

    @FXML
    public void registrarMatriculaAction(ActionEvent event) {
        try {
            String nombre = txtNombre.getText();
            String telefono = txtTelefono.getText();
            double descuento = Double.parseDouble(txtDescuento.getText()) / 100.0;

            Estudiante estudiante = new Estudiante("DOC-123", nombre, telefono, "correo@test.com", 20, LocalDate.now());

            Matricula nuevaMatricula = new Matricula.Builder()
                    .conEstudiante(estudiante)
                    .conPrograma(programaBase)
                    .conDescuento(descuento)
                    .build();

            mostrarMensaje("Éxito", "Matrícula #" + nuevaMatricula.getNumeroMatricula() + " registrada.", Alert.AlertType.INFORMATION);
        } catch (IllegalArgumentException | IllegalStateException e) {
            mostrarMensaje("Error de Validación", e.getMessage(), Alert.AlertType.ERROR);
        } catch (Exception e) {
            mostrarMensaje("Error", "Verifique los datos ingresados.", Alert.AlertType.ERROR);
        }
    }

    private void mostrarMensaje(String titulo, String contenido, Alert.AlertType tipo) {
        Alert alerta = new Alert(tipo);
        alerta.setTitle(titulo);
        alerta.setHeaderText(null);
        alerta.setContentText(contenido);
        alerta.showAndWait();
    }
}