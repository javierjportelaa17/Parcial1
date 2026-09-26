package co.edu.uniquindio.modelo.comprobantes;

public class GeneradorPdf extends GeneradorComprobante {
    protected ComprobantePago crearComprobante() { return new ComprobantePdf(); }
}