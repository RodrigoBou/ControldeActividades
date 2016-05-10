package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaInsertarActivity extends Activity {
    CargaAcademicaBD helper;
    EditText editCargo;
    EditText editDocente;
    EditText editMateria;
    EditText editCiclo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_insertar);
        helper = new CargaAcademicaBD(this);
        editCargo=(EditText)findViewById(R.id.editcargo);
        editCargo=(EditText)findViewById(R.id.editCargo);
        editDocente=(EditText)findViewById(R.id.editDocente);
        editMateria=(EditText)findViewById(R.id.editMateria);
        editCiclo=(EditText)findViewById(R.id.editCiclo);
    }
    public void insertarCargaAcademica(View v){
        String regInsertados;
        Integer cargo=Integer.valueOf(editCargo.getText().toString());
        String docente =editDocente.getText().toString();
        String materia = editMateria.getText().toString();
        Integer ciclo = Integer.valueOf(editCiclo.getText().toString());

        CargaAcademica cargaAcademica= new CargaAcademica();
        cargaAcademica.setCargo(cargo);
        cargaAcademica.setDocente(docente);
        cargaAcademica.setMateria(materia);
        cargaAcademica.setCiclo(ciclo);
        helper.abrir();
        regInsertados=helper.insertar(cargaAcademica);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editCargo.setText("");
        editDocente.setText("");
        editMateria.setText("");
        editCiclo.setText("");
    }

}
