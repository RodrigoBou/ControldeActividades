package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Reserva {
    private int idReserva;
    private int grupo;
    private int recurso;

    public Reserva() {
    }


    public Reserva(int idReserva, int grupo,  int recurso) {
        this.idReserva = idReserva;
        this.grupo = grupo;
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

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }
}
