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

public class TipoGrupoConsultarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editCod_tipo_grupo;
    EditText editTipoGrupo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_consultar);

        helper = new TipoGrupoBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de TipoGrupo", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCod_tipo_grupo=(EditText) findViewById(R.id.editCod_tipo_grupo);
        editTipoGrupo=(EditText) findViewById(R.id.editTipoGrupo);
    }
    public void consultarTipoGrupo(View v){
        TipoGrupo tipoGrupo= helper.consultarTipoGrupo(editCod_tipo_grupo.getText().toString());
        if (tipoGrupo==null)
            Toast.makeText(this, "Tipo Grupo con codigo de Grupo" + editCod_tipo_grupo.getText().toString()+"no encontrado",Toast.LENGTH_SHORT).show();
        else {
            editTipoGrupo.setText(tipoGrupo.getTipoGrupo());
        }
    }
    public void limpiarTexto(View v){
        editCod_tipo_grupo.setText("");
        editTipoGrupo.setText("");
    }

}
