package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 * and
 * Modified by Yohalmo
 */
public class Reserva {
    private int id_reserva;
    private int grupo_materia;
    private int recurso;

    public Reserva() {
    }


    public Reserva(int id_reserva, int grupo, int recurso) {
        this.id_reserva = id_reserva;
        this.grupo_materia = grupo;
        this.recurso = recurso;
    }

    public int getIdReserva() {
        return id_reserva;
    }

    public void setIdReserva(int idReserva) {
        this.id_reserva = id_reserva;
    }

    public int getGrupo() {
        return grupo_materia;
    }

    public void setGrupo(int grupo) {
        this.grupo_materia = grupo;
    }

    public int getRecurso() {
        return recurso;
    }

    public void setRecurso(int recurso) {
        this.recurso = recurso;
    }
}
