package co.edu.uniquindio.modelo.comprobantes;

import co.edu.uniquindio.modelo.Matricula;

public abstract class GeneradorComprobante {
    protected abstract ComprobantePago crearComprobante();

    public void procesarPago(Matricula m) {
        System.out.println("Procesando pago de matrícula...");
        ComprobantePago comprobante = crearComprobante();
        comprobante.generarEstructura(m);
    }
}
