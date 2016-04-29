package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class GrupoMateriaEliminarActivity extends Activity {

    EditText editTipoGrupo;
    EditText editMateria;
    EditText editDocente;
    EditText editCiclo;
    EditText editHorario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_eliminar);

        editTipoGrupo = (EditText) findViewById(R.id.editTipoGrupo);
        editMateria = (EditText) findViewById(R.id.editMateria);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editHorario= (EditText) findViewById(R.id.editDocente);
    }


}
