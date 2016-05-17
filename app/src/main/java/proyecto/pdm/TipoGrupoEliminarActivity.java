package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoEliminarActivity extends Activity {
    EditText editCodTipoGrupo;
    TipoGrupoBD controlhelper;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_eliminar);

        controlhelper = new TipoGrupoBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de TipoGrupo", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCodTipoGrupo=(EditText) findViewById(R.id.editCod_tipo_grupo);
    }
    public void eliminarTipoGrupo(View v){
        String regEliminadas;
        TipoGrupo tipoGrupo =new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(editCodTipoGrupo.getText().toString());
        regEliminadas =controlhelper.eliminar(tipoGrupo);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
