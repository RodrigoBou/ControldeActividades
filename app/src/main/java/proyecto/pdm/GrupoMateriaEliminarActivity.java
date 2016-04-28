package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class GrupoMateriaEliminarActivity extends Activity {

    EditText editIdGrupo;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_eliminar);

        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);

    }


}
