package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class GrupoMateria {
    private int tipoGrupo;
    private String materia;
    private String docente;
    private int ciclo;
    private String local;
    private String diasImpartida;
    private int horario;
    private String numGrupo;

    public GrupoMateria() {
    }

    public GrupoMateria(int tipoGrupo, String materia, String docente, int ciclo, String local, String diasImpartida, int horario, String numGrupo) {
        this.tipoGrupo = tipoGrupo;
        this.materia = materia;
        this.docente = docente;
        this.ciclo = ciclo;
        this.local = local;
        this.diasImpartida = diasImpartida;
        this.horario = horario;
        this.numGrupo = numGrupo;
    }

    public int getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(int tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
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

    public String getLocal() {
        return local;
    }

    public void setLocal(String local) {
        this.local = local;
    }

    public String getDiasImpartida() {
        return diasImpartida;
    }

    public void setDiasImpartida(String diasImpartida) {
        this.diasImpartida = diasImpartida;
    }

    public int getHorario() {
        return horario;
    }

    public void setHorario(int horario) {
        this.horario = horario;
    }

    public String getNumGrupo() {
        return numGrupo;
    }

    public void setNumGrupo(String numGrupo) {
        this.numGrupo = numGrupo;
    }
}
