package co.edu.uniquindio.modelo.entregable;

public class FabricaVirtual extends FabricaEntregable {
    @Override
    public MaterialEstudio crearMaterial() {
        return new LicenciaPlataforma();
    }

    @Override
    public CarneEstudiantil crearCarne() {
        return new CarneDigital();
    }
}