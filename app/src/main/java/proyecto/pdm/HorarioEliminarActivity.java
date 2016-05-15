package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.ClasesModelo.Horario;

public class HorarioEliminarActivity extends Activity {

    EditText editIdHorario;
    HorarioBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_eliminar);

        helper = new HorarioBD(this);
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
