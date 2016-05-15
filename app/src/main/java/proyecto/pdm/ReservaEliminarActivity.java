package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ReservaBD;
import proyecto.pdm.ClasesModelo.Reserva;

public class ReservaEliminarActivity extends Activity {

    EditText editIdReserva;
    ReservaBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_eliminar);

        helper = new ReservaBD(this);
        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
    }

    public void eliminarReserva(View v) {
        String regEliminadas;
        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editIdReserva.getText().toString()));

        regEliminadas = helper.eliminar(reserva);

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
