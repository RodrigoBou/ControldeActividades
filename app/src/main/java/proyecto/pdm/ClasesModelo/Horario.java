package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 * and
 * Modified by Yohalmo
 */
public class Horario {
    private int id_horario;
    private String hora_ini;
    private String hora_fin;

    public Horario() {
    }

    public Horario(int id_horario, String hora_ini, String hora_fin) {
        this.id_horario = id_horario;
        this.hora_ini = hora_ini;
        this.hora_fin = hora_fin;
    }

    public int getId_horario() {
        return id_horario;
    }

    public void setId_horario(int id_horario) {
        this.id_horario = id_horario;
    }

    public String getHora_ini() {
        return hora_ini;
    }

    public void setHora_ini(String hora_ini) {
        this.hora_ini = hora_ini;
    }

    public String getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(String hora_fin) {
        this.hora_fin = hora_fin;
    }
}


