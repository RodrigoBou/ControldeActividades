package proyecto.pdm.ClasesModelo;

/**
 * Created by kelly on 23/04/2016.
 * and
 * Modified by Yohalmo
 */
public class Ciclo {

    private String id_ciclo;
    private String anio_ciclo;
    private String ciclo_num;

    public Ciclo() {
    }

    public Ciclo(String id_ciclo, String anio_ciclo, String ciclo_num) {
        this.id_ciclo = id_ciclo;
        this.anio_ciclo = anio_ciclo;
        this.ciclo_num = ciclo_num;
    }

    public String getId_ciclo() {
        return id_ciclo;
    }

    public void setId_ciclo(String id_ciclo) {
        this.id_ciclo = id_ciclo;
    }

    public String getAnio_ciclo() {
        return anio_ciclo;
    }

    public void setAnio_ciclo(String anio_ciclo) {
        this.anio_ciclo = anio_ciclo;
    }

    public String getCiclo_num() {
        return ciclo_num;
    }

    public void setCiclo_num(String ciclo_num) {
        this.ciclo_num = ciclo_num;
    }
}
