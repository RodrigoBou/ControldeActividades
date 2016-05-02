package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class MateriaEliminarActivity extends Activity {

    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_eliminar);

        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
    }
}
