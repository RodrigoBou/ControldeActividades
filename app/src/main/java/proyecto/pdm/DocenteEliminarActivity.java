package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;

public class DocenteEliminarActivity extends Activity {

    EditText editCodDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);

        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
    }
}
