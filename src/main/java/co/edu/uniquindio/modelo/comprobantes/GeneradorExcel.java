package co.edu.uniquindio.modelo.comprobantes;

public class GeneradorExcel extends GeneradorComprobante {
    protected ComprobantePago crearComprobante() { return new ComprobanteExcel(); }
}