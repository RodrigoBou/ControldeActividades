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

public class GrupoMateriaConsultarActivity extends Activity {
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
        setContentView(R.layout.activity_grupo_materia_consultar);

        helper = new GrupoMateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de GrupoMateria", id)){
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

    public  void consultarGrupoMateria(View v){

        GrupoMateria grupoMateria = helper.consultar(Integer.valueOf(editIdGrupo.getText().toString()));
        if(grupoMateria == null){
            Toast.makeText(this, "Grupo Materia con Id" + editIdGrupo.getText().toString() + "no encontrado",
                    Toast.LENGTH_LONG).show();
        }
        else {
            editTipoGrupo.setText(grupoMateria.getTipoGrupo());
            editMateria.setText(grupoMateria.getMateria());
            editDocente.setText(grupoMateria.getDocente());
            editCiclo.setText(grupoMateria.getCiclo());
            editLocal.setText(grupoMateria.getLocal());
            editDiasImpartida.setText(grupoMateria.getDiasImpartida());
            editHorario.setText(String.valueOf(grupoMateria.getHorario()));
            editNumGrupo.setText(grupoMateria.getNumGrupo());
        }

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
