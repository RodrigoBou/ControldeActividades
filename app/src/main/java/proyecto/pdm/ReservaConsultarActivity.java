package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ReservaBD;
import proyecto.pdm.ClasesModelo.Reserva;

public class ReservaConsultarActivity extends Activity {

    ReservaBD helper;
    EditText editIdReserva;
    EditText editRecurso;
    EditText editGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_consultar);
        helper = new ReservaBD(this);
        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
        editRecurso = (EditText) findViewById(R.id.editRecurso);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
    }

    public void consultarReserva(View v) {

        Reserva reserva = helper.consultar(Integer.valueOf(editIdReserva.getText().toString()));

        if (reserva == null) {
            Toast.makeText(this, "Reserva id: " + editIdReserva.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        } else {
            editIdReserva.setText(String.valueOf(reserva.getIdReserva()));
            editRecurso.setText(String.valueOf(reserva.getRecurso()));
            editGrupo.setText(String.valueOf(reserva.getGrupo()));
        }

    }

    public void limpiarTextoReserva(View v) {
        editIdReserva.setText("");
        editRecurso.setText("");
        editGrupo.setText("");
    }
}
