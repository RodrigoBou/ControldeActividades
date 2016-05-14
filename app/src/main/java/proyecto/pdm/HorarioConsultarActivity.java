package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.ClasesModelo.Horario;

public class HorarioConsultarActivity extends Activity {

    HorarioBD helper;
    EditText editIdHorario;
    EditText editHoraIni;
    EditText editHoraFin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_consultar);
        helper = new HorarioBD(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editHoraIni = (EditText) findViewById(R.id.editHoraIni);
        editHoraFin = (EditText) findViewById(R.id.editHoraFin);
    }

    public void consultarHorario(View v) {

        Horario horario = helper.consultar(editIdHorario.getText().toString());

        if (horario == null) {
            Toast.makeText(this, "Horario id: " + editIdHorario.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        } else {
            editIdHorario.setText(horario.getId_horario());
            editHoraIni.setText(horario.getHora_ini());
            editHoraFin.setText(horario.getHora_fin());
        }

    }

    public void limpiarTextoHorario(View v) {
        editIdHorario.setText("");
        editHoraIni.setText("");
        editHoraFin.setText("");
    }
}
