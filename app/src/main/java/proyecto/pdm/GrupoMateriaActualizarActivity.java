package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class GrupoMateriaActualizarActivity extends Activity {

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

    public void limpiarTexto(View v){
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
