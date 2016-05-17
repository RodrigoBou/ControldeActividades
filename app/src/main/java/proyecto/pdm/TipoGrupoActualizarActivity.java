package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.app.Activity;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoActualizarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editCodTipoGrupo;
    EditText editTipoGrupo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_actualizar);

        helper = new TipoGrupoBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Modificacion de TipoGrupo", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCodTipoGrupo =(EditText) findViewById(R.id.editCod_tipo_grupo);
        editTipoGrupo=(EditText) findViewById(R.id.editTipoGrupo);
    }
    public void actualizarTipoGrupo(View v){
        TipoGrupo tipoGrupo = new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(editCodTipoGrupo.getText().toString());
        tipoGrupo.setTipoGrupo(editTipoGrupo.getText().toString());
        String estado=helper.actualizar(tipoGrupo);
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editCodTipoGrupo.setText("");
        editTipoGrupo.setText("");
    }

}
