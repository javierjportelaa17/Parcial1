package co.edu.uniquindio.modelo.comprobantes;
import co.edu.uniquindio.modelo.Matricula;

class ComprobanteExcel implements ComprobantePago {
    public void generarEstructura(Matricula m) {
        System.out.println("Generando filas en Excel para Matrícula #" + m.getNumeroMatricula());
    }
}