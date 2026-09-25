package co.edu.uniquindio.modelo.entregable;

public class FabricaPresencial {
    public MaterialEstudio crearMaterial() { return new MaterialImpreso(); }
    public CarneEstudiantil crearCarne() { return new CarneFisico(); }
}
