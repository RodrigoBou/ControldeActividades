package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloEliminarActivity extends Activity {

    EditText editIdCiclo;
    CicloBD helper;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar);

        helper = new CicloBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de Ciclo", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
    }

    public void eliminarCiclo(View v) {
        String regEliminadas;
        Ciclo ciclo = new Ciclo();
        ciclo.setId_ciclo(editIdCiclo.getText().toString());
        regEliminadas = helper.eliminar(ciclo);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
