package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Cargo {
    private int idCargo;
    private String nomCargo;

    public Cargo() {
    }

    public Cargo(int idCargo, String nomCargo) {
        this.idCargo = idCargo;
        this.nomCargo = nomCargo;
    }

    public int getIdCargo() {
        return idCargo;
    }

    public void setIdCargo(int idCargo) {
        this.idCargo = idCargo;
    }

    public String getNomCargo() {
        return nomCargo;
    }

    public void setNomCargo(String nomCargo) {
        this.nomCargo = nomCargo;
    }
}
