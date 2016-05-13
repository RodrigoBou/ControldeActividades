package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class CargaAcademica {
    private int cargo;
    private String materia;
    private String docente;
    private String ciclo;

    public CargaAcademica(){

    }

    public CargaAcademica(int cargo, String materia, String docente, String ciclo) {
        this.cargo = cargo;
        this.materia = materia;
        this.docente = docente;
        this.ciclo = ciclo;
    }

    public int getCargo() {
        return cargo;
    }

    public void setCargo(int cargo) {
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

    public String getCiclo() {
        return ciclo;
    }

    public void setCiclo(String ciclo) {
        this.ciclo = ciclo;
    }
}
