package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Actividad;

public class ActividadEliminarActivity extends AppCompatActivity {

    EditText editActividadID;
    ActividadBD controlHelper;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_eliminar_actividad);
        controlHelper = new ActividadBD(this);
        credencialesUsuario = new UsuarioBD(this);

        editActividadID = (EditText) findViewById(R.id.editActividadID);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de Actividad", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }
    }


    public void EliminarActividad(View v){
        String regEliminadas;
        Actividad actividad = new Actividad();
       actividad.setIdActividad(Integer.parseInt(editActividadID.getText().toString()));
        regEliminadas=controlHelper.Eliminar(actividad);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
        editActividadID.setText("");
    }
}
