package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import proyecto.pdm.CRUDTablas.GrupoMateriaBD;

import proyecto.pdm.ClasesModelo.GrupoMateria;

public class GrupoMateriaConsultarActivity extends Activity {
    GrupoMateriaBD helper;
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
        editTipoGrupo = (EditText) findViewById(R.id.editTipoGrupo);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editMateria = (EditText) findViewById(R.id.editMateria);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editLocal = (EditText) findViewById(R.id.editIdGrupo);
        editDiasImpartida = (EditText) findViewById(R.id.editMateria);
        editHorario= (EditText) findViewById(R.id.editDocente);
        editNumGrupo= (EditText) findViewById(R.id.editCiclo);
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
            editHorario.setText(grupoMateria.getHorario());
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
