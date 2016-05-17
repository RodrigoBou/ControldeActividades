package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ReservaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Reserva;

public class ReservaConsultarActivity extends Activity {

    ReservaBD helper;
    EditText editIdReserva;
    EditText editRecurso;
    EditText editGrupo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_consultar);

        helper = new ReservaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de Reservacion", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

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
