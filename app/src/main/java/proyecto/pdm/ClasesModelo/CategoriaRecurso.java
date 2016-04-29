package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class CategoriaRecurso {
    private int idCatRecurso;
    private String categoriaRecurso;

    public CategoriaRecurso() {
    }

    public CategoriaRecurso(int idCatRecurso, String categoriaRecurso) {
        this.idCatRecurso = idCatRecurso;
        this.categoriaRecurso = categoriaRecurso;
    }

    public int getIdCatRecurso() {
        return idCatRecurso;
    }

    public void setIdCatRecurso(int idCatRecurso) {
        this.idCatRecurso = idCatRecurso;
    }

    public String getCategoriaRecurso() {
        return categoriaRecurso;
    }

    public void setCategoriaRecurso(String categoriaRecurso) {
        this.categoriaRecurso = categoriaRecurso;
    }
}
