package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Spinner;

import java.util.HashMap;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.DocenteBD;

public class CargaAcademicaEliminarActivity extends Activity {
    private Spinner SpinDocente;
    private CargaAcademicaBD helper;
    private DocenteBD docenteBD;
    private HashMap<String, String> spinnerMapDocente = new HashMap<String, String>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_eliminar);
        helper=new CargaAcademicaBD(this);
        docenteBD =new DocenteBD(this);

    }
}
