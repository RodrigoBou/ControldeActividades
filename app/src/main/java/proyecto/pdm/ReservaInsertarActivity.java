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

public class ReservaInsertarActivity extends Activity {

    ReservaBD helper;
    EditText editIdReserva;
    EditText editRecurso;
    EditText editGrupo;
    UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_insertar);

        helper = new ReservaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Reservacion", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdReserva = (EditText) findViewById(R.id.editIdReserva);
        editRecurso = (EditText) findViewById(R.id.editRecurso);
        editGrupo = (EditText) findViewById(R.id.editGrupo);
    }

    public void insertarReserva(View v) {

        Reserva reserva = new Reserva();
        reserva.setIdReserva(Integer.parseInt(editIdReserva.getText().toString()));
        reserva.setRecurso(Integer.parseInt(editRecurso.getText().toString()));
        reserva.setGrupo(Integer.parseInt(editGrupo.getText().toString()));

        String regInsertados = helper.insertar(reserva);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTextoReserva(View v) {
        editIdReserva.setText("");
        editRecurso.setText("");
        editGrupo.setText("");
    }
}
