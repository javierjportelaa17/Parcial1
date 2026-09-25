package co.edu.uniquindio.modelo;
import java.time.LocalDate;

public class Estudiante {
    private String documentoIdentidad;
    private String nombreCompleto;
    private String telefono;
    private String correo;
    private int edad;
    private LocalDate fechaRegistro;

    public Estudiante(String documentoIdentidad, String nombreCompleto, String telefono, String correo, int edad, LocalDate fechaRegistro) {
        this.documentoIdentidad = documentoIdentidad;
        this.nombreCompleto = nombreCompleto;
        this.telefono = telefono;
        this.correo = correo;
        this.edad = edad;
        this.fechaRegistro = fechaRegistro;
    }

    public String getTelefono() { return telefono; }
    public String getNombreCompleto() { return nombreCompleto; }


    public boolean esTelefonoNumeroPerfecto() {
        try {
            int numero = Integer.parseInt(this.telefono);
            if (numero <= 1) return false;
            int sumaDivisores = 0;
            for (int i = 1; i <= numero / 2; i++) {
                if (numero % i == 0) {
                    sumaDivisores += i;
                }
            }
            return sumaDivisores == numero;
        } catch (NumberFormatException e) {
            return false;
        }
    }
}