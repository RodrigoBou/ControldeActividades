package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.CRUDTablas.MateriaBD;

public class MateriaConsultarActivity extends Activity {
    MateriaBD helper;
    UsuarioBD credencialesUsuario;
    EditText editNomMateria;
    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);

        helper = new MateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        helper = new MateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de Materia", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
        editNomMateria = (EditText) findViewById(R.id.editNomMateria);
    }

    public void consultarMateria(View v){

        Materia materia = helper.consultar(editCodMateria.getText().toString());
        if (materia == null){
            Toast.makeText(this, "Materia codigo:" + editCodMateria.getText().toString() + "no encontrado",
                    Toast.LENGTH_LONG).show();
        }
        else {
            editNomMateria.setText(materia.getNomMateria());
        }
    }

    public void limpiarTextoMateria(View v){
        editCodMateria.setText("");
        editNomMateria.setText("");
    }
}