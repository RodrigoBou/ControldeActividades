package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloInsertarActivity extends Activity {

    CicloBD helper;
    UsuarioBD credencialesUsuario;
    EditText editIdCiclo;
    EditText editAnioCiclo;
    Spinner dropdown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        helper = new CicloBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Ciclo", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        editAnioCiclo = (EditText) findViewById(R.id.editAnioCiclo);

        dropdown = (Spinner) findViewById(R.id.spinner1);
        String[] items = new String[]{"1", "2"};
        ArrayAdapter<String> adapter = new ArrayAdapter<>(this, android.R.layout.simple_spinner_dropdown_item, items);
        dropdown.setAdapter(adapter);
    }

    public void insertarCiclo(View v) {

        String idCiclo = editIdCiclo.getText().toString();
        String anioCiclo = editAnioCiclo.getText().toString();
        String regInsertados;

        Ciclo ciclo = new Ciclo();
        ciclo.setId_ciclo(idCiclo);
        ciclo.setAnio_ciclo(anioCiclo);
        ciclo.setCiclo_num(dropdown.getSelectedItem().toString());

        regInsertados = helper.insertar(ciclo);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTextoCiclo(View v) {
        editIdCiclo.setText("");
        editAnioCiclo.setText("");
    }
}
