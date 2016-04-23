package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Recurso {
    private int idRecurso;
    private String nomRecurso;
    private String detalleRecurso;
    private int estado;
    private int catRecurso;

    public Recurso() {
    }

    public Recurso(int idRecurso, String nomRecurso, String detalleRecurso, int estado, int catRecurso) {
        this.idRecurso = idRecurso;
        this.nomRecurso = nomRecurso;
        this.detalleRecurso = detalleRecurso;
        this.estado = estado;
        this.catRecurso = catRecurso;
    }

    public int getIdRecurso() {
        return idRecurso;
    }

    public void setIdRecurso(int idRecurso) {
        this.idRecurso = idRecurso;
    }

    public String getNomRecurso() {
        return nomRecurso;
    }

    public void setNomRecurso(String nomRecurso) {
        this.nomRecurso = nomRecurso;
    }

    public String getDetalleRecurso() {
        return detalleRecurso;
    }

    public void setDetalleRecurso(String detalleRecurso) {
        this.detalleRecurso = detalleRecurso;
    }

    public int getEstado() {
        return estado;
    }

    public void setEstado(int estado) {
        this.estado = estado;
    }

    public int getCatRecurso() {
        return catRecurso;
    }

    public void setCatRecurso(int catRecurso) {
        this.catRecurso = catRecurso;
    }
}
