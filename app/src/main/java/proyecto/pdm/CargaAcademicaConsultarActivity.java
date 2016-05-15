package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaConsultarActivity extends Activity {
    CargaAcademicaBD helper;
    EditText editDocente;
    EditText editMateria;
    EditText editCargo;
    EditText editCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_consultar);
        helper = new CargaAcademicaBD(this);
        editDocente=(EditText)findViewById(R.id.editDocente);
        editMateria=(EditText)findViewById(R.id.editMateria);
        editCargo=(EditText)findViewById(R.id.editCargo);
        editCiclo=(EditText)findViewById(R.id.editCiclo);
    }
    public void consultarCargaAcademica(View v){
        helper.abrir();
        CargaAcademica cargaAcademica=helper.consultar(editDocente.getText().toString(),editCiclo.getText().toString());
        helper.cerrar();
        if (cargaAcademica==null)
            Toast.makeText(this,"Carga Academica no registrada "+editDocente.getText().toString()+editCiclo.getText().toString(), Toast.LENGTH_SHORT).show();
        else {
            editCargo.setText(String.valueOf(cargaAcademica.getCargo()));
            editMateria.setText(cargaAcademica.getMateria());
        }
    }
    public  void limpiarTexto(){
        editDocente.setText("");
        editMateria.setText("");
        editCargo.setText("");
        editCiclo.setText("");
    }
}
