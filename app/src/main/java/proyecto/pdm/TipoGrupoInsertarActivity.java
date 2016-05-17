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

public class TipoGrupoInsertarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editIdTipoGrupo;
    EditText editTipoGrupo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_insertar);

        helper= new TipoGrupoBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de TipoGrupo", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdTipoGrupo=(EditText)findViewById(R.id.editIdTipoGrupo);
        editTipoGrupo=(EditText)findViewById(R.id.editTipoGrupo);

    }
    public void insertarTipoGrupo(View v){
        String cod_tipo_grupo=editIdTipoGrupo.getText().toString();
        String tipo_grupo=editTipoGrupo.getText().toString();
        String regIngresados;
        TipoGrupo tipoGrupo = new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(cod_tipo_grupo);
        tipoGrupo.setTipoGrupo(tipo_grupo);

        regIngresados=helper.insertar(tipoGrupo);
        Toast.makeText(this, regIngresados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editIdTipoGrupo.setText("");
        editTipoGrupo.setText("");
    }

}
