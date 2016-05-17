package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Materia;

public class MateriaEliminarActivity extends Activity {

    EditText editCodMateria;
    MateriaBD controlHelper;
    UsuarioBD credencialesUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_eliminar);

        controlHelper = new MateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de Materia", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
        
        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
    }

    public void eliminarMateria(View v){
        String regEliminadas;
        Materia materia = new Materia();
        materia.setCodMateria(editCodMateria.getText().toString());
        regEliminadas = controlHelper.eliminar(materia);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
