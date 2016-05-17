package proyecto.pdm;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;

public class CargaAcademicaActualizarActivity extends Activity {
    CargaAcademicaBD helper;
    EditText editDocente;
    EditText editMateria;
    EditText editCargo;
    EditText editCiclo;

    UsuarioBD credencialesUsuario;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_actualizar);

        helper = new CargaAcademicaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Modificacion de CargaAcademica", id)){
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
    public void actualizarCargaAcademica(View v) {
        CargaAcademica cargaAcademica = new CargaAcademica();
        cargaAcademica.setDocente(editDocente.getText().toString());
        cargaAcademica.setCiclo(Integer.valueOf(editCiclo.getText().toString()));
        cargaAcademica.setCargo(Integer.valueOf(editCargo.getText().toString()));
        cargaAcademica.setMateria(editMateria.getText().toString());

        String estado = helper.actualizar(cargaAcademica);

        Toast.makeText(this, estado,Toast.LENGTH_SHORT).show();

    }
    public  void limpiarTexto(){
        editDocente.setText("");
        editMateria.setText("");
        editCargo.setText("");
        editCiclo.setText("");
    }

}
