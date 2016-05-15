package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class CargaAcademica {
    private Integer cargo;
    private String materia;
    private String docente;
    private Integer ciclo;

    public CargaAcademica() {
    }

    public CargaAcademica(Integer cargo, String materia, String docente, Integer ciclo) {
        this.cargo = cargo;
        this.materia = materia;
        this.docente = docente;
        this.ciclo = ciclo;
    }

    public Integer getCargo() {
        return cargo;
    }

    public void setCargo(Integer cargo) {
        this.cargo = cargo;
    }

    public String getMateria() {
        return materia;
    }

    public void setMateria(String materia) {
        this.materia = materia;
    }

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }

    public Integer getCiclo() {
        return ciclo;
    }

    public void setCiclo(Integer ciclo) {
        this.ciclo = ciclo;
    }
}
