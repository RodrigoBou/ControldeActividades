package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Materia {
    private String codMateria;
    private String nomMateria;

    public Materia() {
    }

    public Materia(String codMateria, String nomMateria) {
        this.codMateria = codMateria;
        this.nomMateria = nomMateria;
    }

    public String getCodMateria() {
        return codMateria;
    }

    public void setCodMateria(String codMateria) {
        this.codMateria = codMateria;
    }

    public String getNomMateria() {
        return nomMateria;
    }

    public void setNomMateria(String nomMateria) {
        this.nomMateria = nomMateria;
    }
}
