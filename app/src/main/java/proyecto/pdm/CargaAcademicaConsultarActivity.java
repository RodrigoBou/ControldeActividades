package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.util.Vector;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaConsultarActivity extends Activity {
    CargaAcademicaBD helper;
    EditText editDocente;
    EditText editMateria;
    EditText editCargo;
    EditText editCiclo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_consultar);

        helper = new CargaAcademicaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de CargaAcademica", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editDocente=(EditText)findViewById(R.id.editDocente);
        editMateria=(EditText)findViewById(R.id.editMateria);
        editCargo=(EditText)findViewById(R.id.editCargo);
        editCiclo=(EditText)findViewById(R.id.editCiclo);

    }
    public void consultarCargaAcademica(View v){

        CargaAcademica cargaAcademica=helper.consultar(editDocente.getText().toString(),editCiclo.getText().toString());

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
