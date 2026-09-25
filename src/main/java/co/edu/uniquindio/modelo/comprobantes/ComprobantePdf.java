package co.edu.uniquindio.modelo.comprobantes;
import co.edu.uniquindio.modelo.Matricula;


public class ComprobantePdf implements ComprobantePago {
    public void generarEstructura(Matricula m) {
        System.out.println("Generando PDF estructurado para Matrícula #" + m.getNumeroMatricula());
    }
}