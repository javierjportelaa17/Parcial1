package co.edu.uniquindio.modelo;

public class ConsecutivoMatricula {
    private static ConsecutivoMatricula instancia;
    private int ultimoNumero;

    private ConsecutivoMatricula() {
        this.ultimoNumero = 0; // Inicia el contador
    }

    public static synchronized ConsecutivoMatricula getInstance() {
        if (instancia == null) {
            instancia = new ConsecutivoMatricula();
        }
        return instancia;
    }

    public int generarSiguiente() {
        return ++ultimoNumero;
    }
}