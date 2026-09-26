package co.edu.uniquindio.modelo.entregable;

public class FabricaPresencial extends FabricaEntregable {
    @Override
    public MaterialEstudio crearMaterial() {
        return new MaterialImpreso();
    }

    @Override
    public CarneEstudiantil crearCarne() {
        return new CarneFisico();
    }
}