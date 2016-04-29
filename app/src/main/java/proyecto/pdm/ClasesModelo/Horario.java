package proyecto.pdm.ClasesModelo;

import java.sql.Time;

/**
 * Created by kelly on 23/04/2016.
 */
public class Horario {
    private int idHorario;
    private Time horaIni;
    private Time horaFin;

    public Horario() {
    }

    public Horario(int idHorario, Time horaIni, Time horaFin) {
        this.idHorario = idHorario;
        this.horaIni = horaIni;
        this.horaFin = horaFin;
    }

    public int getIdHorario() {
        return idHorario;
    }

    public void setIdHorario(int idHorario) {
        this.idHorario = idHorario;
    }

    public Time getHoraIni() {
        return horaIni;
    }

    public void setHoraIni(Time horaIni) {
        this.horaIni = horaIni;
    }

    public Time getHoraFin() {
        return horaFin;
    }

    public void setHoraFin(Time horaFin) {
        this.horaFin = horaFin;
    }
}
