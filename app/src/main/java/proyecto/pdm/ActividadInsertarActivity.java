package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;

public class ActividadInsertarActivity extends Activity {


    EditText editID;
    EditText editNombre;
    EditText editDetalle;
    EditText editFecha;
    EditText editHoraI;
    EditText editHoraF;
    EditText editDocente;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_insertar);

        editID=(EditText) findViewById(R.id.editActividad);
        editNombre= (EditText) findViewById(R.id.editNombreActividad);
        editDetalle=(EditText) findViewById(R.id.editDetalle);
        editFecha=(EditText) findViewById(R.id.editFecha);
        editHoraI=(EditText) findViewById(R.id.editHoraI);
        editHoraF=(EditText) findViewById(R.id.editHoraF);
        editDocente=(EditText) findViewById(R.id.editDocente);



    }
}
