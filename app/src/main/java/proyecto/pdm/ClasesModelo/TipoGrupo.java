package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class TipoGrupo {

    private int idTipoGrupo;
    private String codTipoGrupo;

    public TipoGrupo(){

    }

    public TipoGrupo(int idTipoGrupo, String codTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
        this.codTipoGrupo = codTipoGrupo;
    }

    public int getIdTipoGrupo() {
        return idTipoGrupo;
    }

    public void setIdTipoGrupo(int idTipoGrupo) {
        this.idTipoGrupo = idTipoGrupo;
    }

    public String getcodTipoGrupo() {
        return codTipoGrupo;
    }

    public void setcodTipoGrupo(String codTipoGrupo) {
        this.codTipoGrupo = codTipoGrupo;
    }
}
