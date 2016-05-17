package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Cargo;

public class CargoInsertarActivity extends AppCompatActivity {

    private EditText editCargo;

    private CargoDB dbHelper;
    private UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_insertar);

        dbHelper = new CargoDB(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Cargo", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCargo = (EditText) findViewById(R.id.editCargo);
    }

    public void insertarCargo(View v){
        String nom_cargo = editCargo.getText().toString();
        String regInsertados = "";

        Cargo cargo = new Cargo();

        cargo.setNomCargo(nom_cargo);
        regInsertados = dbHelper.insertar(cargo);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v){
        editCargo.setText("");
    }
}
