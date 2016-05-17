package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.GrupoMateriaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.GrupoMateria;

public class GrupoMateriaEliminarActivity extends Activity {

    EditText editIdGrupo;
    UsuarioBD credencialesUsuario;
    GrupoMateriaBD controlHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_eliminar);

        controlHelper = new GrupoMateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de GrupoMateria", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
    }

    public void eliminarGrupoMateria(View v){
        String regEliminadas;
        GrupoMateria grupoMateria = new GrupoMateria();
        grupoMateria.setIdGrupo(grupoMateria.getIdGrupo());
        regEliminadas=controlHelper.eliminar(grupoMateria);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }


}
