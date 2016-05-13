package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloConsultarActivity extends Activity {

    CicloBD helper;
    EditText editIdCiclo;
    EditText editAnioCiclo;
    EditText editNumCiclo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_consultar);
        helper = new CicloBD(this);
        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        editAnioCiclo = (EditText) findViewById(R.id.editAnioCiclo);
        editNumCiclo = (EditText) findViewById(R.id.editNumCiclo);
    }

    public void consultarCiclo(View v) {

        Ciclo ciclo = helper.consultar(editIdCiclo.getText().toString());

        if (ciclo == null) {
            Toast.makeText(this, "Ciclo id: " + editIdCiclo.getText().toString() + "no encontrado", Toast.LENGTH_LONG).show();
        } else {
            editIdCiclo.setText(ciclo.getId_ciclo());
            editAnioCiclo.setText(ciclo.getAnio_ciclo());
            editNumCiclo.setText(ciclo.getCiclo_num());
        }

    }

    public void limpiarTextoCiclo(View v) {
        editIdCiclo.setText("");
        editAnioCiclo.setText("");
        editNumCiclo.setText("");
    }
}
