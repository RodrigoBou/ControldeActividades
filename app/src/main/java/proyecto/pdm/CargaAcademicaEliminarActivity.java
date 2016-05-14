package proyecto.pdm;

import android.app.Activity;

import android.os.Bundle;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaEliminarActivity extends Activity {
    CargaAcademicaBD controlhelper;
    EditText editDocente;
    EditText editCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_eliminar);
        controlhelper=new CargaAcademicaBD(this);
        editDocente=(EditText)findViewById(R.id.editDocente);
        editCiclo =(EditText)findViewById(R.id.editCiclo);


    }
    public  void eliminarCargaAcademica(){
        String regEliminadas;
        CargaAcademica cargaAcademica = new CargaAcademica();
        cargaAcademica.setDocente(editDocente.getText().toString());
        cargaAcademica.setCiclo(editCiclo.getText().toString());
        controlhelper.abrir();
        regEliminadas=controlhelper.eliminar(cargaAcademica);
        controlhelper.cerrar();
        Toast.makeText(this,regEliminadas,Toast.LENGTH_SHORT).show();

    }
    public  void limpiarTexto(){
        editDocente.setText("");

        editCiclo.setText("");
    }
}
