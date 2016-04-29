package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 */
public class Ciclo {
    private int idCiclo;
    private String anioCiclo;
    private String cicloNum;

    public Ciclo() {
    }

    public Ciclo(int idCiclo, String anioCiclo, String cicloNum) {
        this.idCiclo = idCiclo;
        this.anioCiclo = anioCiclo;
        this.cicloNum = cicloNum;
    }

    public int getIdCiclo() {
        return idCiclo;
    }

    public void setIdCiclo(int idCiclo) {
        this.idCiclo = idCiclo;
    }

    public String getAnioCiclo() {
        return anioCiclo;
    }

    public void setAnioCiclo(String anioCiclo) {
        this.anioCiclo = anioCiclo;
    }

    public String getCicloNum() {
        return cicloNum;
    }

    public void setCicloNum(String cicloNum) {
        this.cicloNum = cicloNum;
    }
}
