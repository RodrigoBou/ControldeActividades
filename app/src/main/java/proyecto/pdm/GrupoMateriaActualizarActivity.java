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

public class GrupoMateriaActualizarActivity extends Activity {
    GrupoMateriaBD helper;
    UsuarioBD credencialesUsuario;
    EditText editTipoGrupo;
    EditText editIdGrupo;
    EditText editMateria;
    EditText editDocente;
    EditText editCiclo;
    EditText editLocal;
    EditText editDiasImpartida;
    EditText editHorario;
    EditText editNumGrupo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_actualizar);

        helper = new GrupoMateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Modificacion de GrupoMateria", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editTipoGrupo = (EditText) findViewById(R.id.editTipoGrupo);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editMateria = (EditText) findViewById(R.id.editMateria);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editDiasImpartida = (EditText) findViewById(R.id.editDiasImpartida);
        editHorario= (EditText) findViewById(R.id.editHorario);
        editNumGrupo= (EditText) findViewById(R.id.editNumGrupo);
    }

    public void actualizarGrupoMateria(View v){
        GrupoMateria grupoMateria = new GrupoMateria();
        grupoMateria.setTipoGrupo(editTipoGrupo.getText().toString());
        grupoMateria.setMateria(editMateria.getText().toString());
        grupoMateria.setDocente(editDocente.getText().toString());
        grupoMateria.setCiclo(editCiclo.getText().toString());
        grupoMateria.setLocal(editLocal.getText().toString());
        grupoMateria.setDiasImpartida(editDiasImpartida.getText().toString());
        grupoMateria.setHorario(Integer.parseInt(editHorario.getText().toString()));
        grupoMateria.setNumGrupo(editNumGrupo.getText().toString());
        String estado = helper.actualizar(grupoMateria);

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTextoGrupoMateria(View v){
        editTipoGrupo.setText("");
        editIdGrupo.setText("");
        editMateria.setText("");
        editDocente.setText("");
        editCiclo.setText("");
        editLocal.setText("");
        editDiasImpartida.setText("");
        editHorario.setText("");
        editNumGrupo.setText("");
    }

}
