package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.ClasesModelo.Cargo;

public class CargoActualizarActivity extends AppCompatActivity {

    private TextView textIdCargoActualizar;
    private EditText editCargoActualizar;
    private CargoDB dbHelper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cargo_actualizar);

        dbHelper = new CargoDB(this);

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
