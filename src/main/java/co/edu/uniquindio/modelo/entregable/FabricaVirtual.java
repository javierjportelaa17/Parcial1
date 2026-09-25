package co.edu.uniquindio.modelo.entregable;

public class FabricaVirtual {
    public MaterialEstudio crearMaterial() { return new LicenciaPlataforma(); }
    public CarneEstudiantil crearCarne() { return new CarneDigital(); }
}
