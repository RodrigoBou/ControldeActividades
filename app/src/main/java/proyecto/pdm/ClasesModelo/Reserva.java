package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Reserva {
    private int idReserva;
    private int grupo;
    private String materia;
    private String docente;
    private int ciclo;
    private int recurso;

    public Reserva() {
    }


    public Reserva(int idReserva, int grupo, String materia, String docente, int ciclo, int recurso) {
        this.idReserva = idReserva;
        this.grupo = grupo;
        this.materia = materia;
        this.docente = docente;
        this.ciclo = ciclo;
        this.recurso = recurso;
    }

    public int getIdReserva() {
        return idReserva;
    }

    public void setIdReserva(int idReserva) {
        this.idReserva = idReserva;
    }

    public int getGrupo() {
        return grupo;
    }

    public void setGrupo(int grupo) {
        this.grupo = grupo;
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

    public int getCiclo() {
        return ciclo;
    }

    public void setCiclo(int ciclo) {
        this.ciclo = ciclo;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }
}
