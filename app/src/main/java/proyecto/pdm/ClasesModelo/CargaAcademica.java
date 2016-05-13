package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class CargaAcademica {
    private String cargo;
    private String materia;
    private String docente;
    private String ciclo;

    public CargaAcademica(){

    }

    public CargaAcademica(String cargo, String materia, String docente, String ciclo) {
        this.cargo = cargo;
        this.materia = materia;
        this.docente = docente;
        this.ciclo = ciclo;
    }

    public String getCargo() {
        return cargo;
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

    public void setCargo(String cargo) {
        this.cargo=cargo;
    }
}
