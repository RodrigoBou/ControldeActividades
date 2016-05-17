package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Cargo;

public class CargoActualizarActivity extends AppCompatActivity {

    private TextView textIdCargoActualizar;
    private EditText editCargoActualizar;
    private CargoDB dbHelper;
    private UsuarioBD credencialesUsuario;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_actualizar);

        dbHelper = new CargoDB(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Modificacion de Cargo", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        textIdCargoActualizar = (TextView) findViewById(R.id.textIdCargoActualizar);
        editCargoActualizar = (EditText) findViewById(R.id.editCargoActualizar);

        id = getIntent().getExtras().getInt("idCargo");

        Cargo cargo = dbHelper.getCargo(id);


        textIdCargoActualizar.setText(String.valueOf(cargo.getIdCargo()));
        editCargoActualizar.setText(cargo.getNomCargo());


    }

    public void actualizarCargo(View v){
        String nomCargo = editCargoActualizar.getText().toString();
        String msg;

        Cargo cargo = new Cargo();

        cargo.setIdCargo(id);
        cargo.setNomCargo(nomCargo);

        msg = dbHelper.actualizar(cargo);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void limpiarTexto(View v){
        editCargoActualizar.setText("");
    }

}
