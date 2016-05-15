package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloInsertarActivity extends Activity {

    CicloBD helper;
    EditText editIdCiclo;
    EditText editAnioCiclo;
    Spinner dropdown;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_insertar);
        helper = new CicloBD(this);
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
