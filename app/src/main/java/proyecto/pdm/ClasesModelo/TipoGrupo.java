package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class TipoGrupo {

    private String codTipoGrupo;
    private String TipoGrupo;

    public TipoGrupo(){

    }

    public TipoGrupo(String codTipoGrupo, String tipoGrupo) {
        this.codTipoGrupo = codTipoGrupo;
        TipoGrupo = tipoGrupo;
    }

    public String getTipoGrupo() {
        return TipoGrupo;
    }

    public void setTipoGrupo(String tipoGrupo) {
        TipoGrupo = tipoGrupo;
    }

    public String getcodTipoGrupo() {
        return codTipoGrupo;
    }

    public void setcodTipoGrupo(String codTipoGrupo) {
        this.codTipoGrupo = codTipoGrupo;
    }
}
