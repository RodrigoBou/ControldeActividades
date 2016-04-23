package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class TipoGrupo {
    private int idTipoGrupo;
    private String tipoGrupo;

    public TipoGrupo(){

    }

    public TipoGrupo(int idTipoGrupo, String tipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
        this.tipoGrupo = tipoGrupo;
    }

    public String getTipoGrupo() {
        return tipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        this.tipoGrupo = tipoGrupo;
    }
}
