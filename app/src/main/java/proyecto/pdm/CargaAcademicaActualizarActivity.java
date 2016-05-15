package proyecto.pdm;

import android.app.Activity;

import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaActualizarActivity extends Activity {
    CargaAcademicaBD helper;
    EditText editDocente;
    EditText editMateria;
    EditText editCargo;
    EditText editCiclo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_actualizar);
        helper = new CargaAcademicaBD(this);
        editDocente=(EditText)findViewById(R.id.editDocente);
        editMateria=(EditText)findViewById(R.id.editMateria);
        editCargo=(EditText)findViewById(R.id.editCargo);
        editCiclo=(EditText)findViewById(R.id.editCiclo);
    }
    public void actualizarCargaAcademica(View v) {
        CargaAcademica cargaAcademica = new CargaAcademica();
        cargaAcademica.setDocente(editDocente.getText().toString());
        cargaAcademica.setCiclo(Integer.valueOf(editCiclo.getText().toString()));
        cargaAcademica.setCargo(Integer.valueOf(editCargo.getText().toString()));
        cargaAcademica.setMateria(editMateria.getText().toString());
        helper.abrir();
        String estado = helper.actualizar(cargaAcademica);
        helper.cerrar();
        Toast.makeText(this, estado,Toast.LENGTH_SHORT).show();

    }
    public  void limpiarTexto(){
        editDocente.setText("");
        editMateria.setText("");
        editCargo.setText("");
        editCiclo.setText("");
    }

}
