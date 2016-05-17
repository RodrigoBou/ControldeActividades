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

import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Horario;

public class HorarioEliminarActivity extends Activity {

    EditText editIdHorario;
    HorarioBD helper;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_eliminar);

        helper = new HorarioBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de Horario", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
    }

    public void eliminarHorario(View v) {
        String regEliminadas;
        Horario horario = new Horario();
        horario.setId_horario(Integer.parseInt(editIdHorario.getText().toString()));

        regEliminadas = helper.eliminar(horario);

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
