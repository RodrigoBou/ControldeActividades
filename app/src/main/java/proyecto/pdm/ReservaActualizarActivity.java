package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ReservaBD;
import proyecto.pdm.ClasesModelo.Reserva;

public class ReservaActualizarActivity extends Activity {

    ReservaBD helper;
    EditText editIdReserva;
    EditText editRecurso;
    EditText editGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_actualizar);
        helper = new ReservaBD(this);
        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
        editRecurso = (EditText) findViewById(R.id.editRecurso);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
    }

    public void actualizarReserva(View v) {
        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editIdReserva.getText().toString()));
        reserva.setRecurso(Integer.parseInt(editRecurso.getText().toString()));
        reserva.setGrupo(Integer.parseInt(editGrupo.getText().toString()));

        String regInsertados = helper.actualizar(reserva);

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTextoReserva(View v) {
        editIdReserva.setText("");
        editRecurso.setText("");
        editGrupo.setText("");
    }
}
