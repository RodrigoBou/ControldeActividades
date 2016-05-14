package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.ClasesModelo.Horario;

public class HorarioActualizarActivity extends Activity {

    HorarioBD helper;
    EditText editIdHorario;
    EditText editHoraIni;
    EditText editHoraFin;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_horario_actualizar);
        helper = new HorarioBD(this);
        editIdHorario = (EditText) findViewById(R.id.editIdHorario);
        editHoraIni = (EditText) findViewById(R.id.editHoraIni);
        editHoraFin = (EditText) findViewById(R.id.editHoraFin);
    }

    public void actualizarHorario(View v) {

        String idHorario = editIdHorario.getText().toString();
        String horaIni = editHoraIni.getText().toString();
        String horaFin = editHoraFin.getText().toString();
        String regInsertados;

        Horario horario = new Horario();
        horario.setId_horario(Integer.parseInt(idHorario));
        horario.setHora_ini(horaIni);
        horario.setHora_fin(horaFin);


        regInsertados = helper.actualizar(horario);

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTextoHorario(View v) {
        editIdHorario.setText("");
        editHoraIni.setText("");
        editHoraFin.setText("");
    }
}
