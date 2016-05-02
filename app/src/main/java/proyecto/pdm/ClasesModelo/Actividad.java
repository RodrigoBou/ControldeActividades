package proyecto.pdm.ClasesModelo;

import java.sql.Time;
import java.util.Date;

/**
 * Created by kelly on 23/04/2016.
 */
public class Actividad {

    private int idActividad;
    private String nomActividad;
    private String detalleActividad;
    private Date fecha;
    private Time horaIni;
    private Time horaFin;
    private String docente;


    public Actividad() {
    }


    public Actividad(int idActividad, String nomActividad, String detalleActividad, Date fecha, Time horaIni, String docente, Time horaFin) {
        this.idActividad = idActividad;
        this.nomActividad = nomActividad;
        this.detalleActividad = detalleActividad;
        this.fecha = fecha;
        this.horaIni = horaIni;
        this.docente = docente;
        this.horaFin = horaFin;
    }

    public int getIdActividad() {
        return idActividad;
    }

    public void setIdActividad(int idActividad) {
        this.idActividad = idActividad;
    }

    public String getNomActividad() {
        return nomActividad;
    }

    public void setNomActividad(String nomActividad) {
        this.nomActividad = nomActividad;
    }

    public String getDetalleActividad() {
        return detalleActividad;
    }

    public void setDetalleActividad(String detalleActividad) {
        this.detalleActividad = detalleActividad;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
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

    public String getDocente() {
        return docente;
    }

    public void setDocente(String docente) {
        this.docente = docente;
    }
}
