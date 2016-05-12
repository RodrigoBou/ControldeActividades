package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.ClasesModelo.Actividad;

public class ActividadEliminarActivity extends AppCompatActivity {

    EditText editActividadID;
    ActividadBD controlHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_eliminar_actividad);
        controlHelper = new ActividadBD(this);
        editActividadID = (EditText) findViewById(R.id.editActividadID);
    }


    public void EliminarActividad(View v){
        String regEliminadas;
        Actividad actividad = new Actividad();
       actividad.setIdActividad(Integer.parseInt(editActividadID.getText().toString()));
        controlHelper.abrir();
        regEliminadas=controlHelper.Eliminar(actividad);
        controlHelper.cerrar();
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
