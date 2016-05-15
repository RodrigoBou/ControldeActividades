package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloEliminarActivity extends Activity {

    EditText editIdCiclo;
    CicloBD helper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_eliminar);

        helper = new CicloBD(this);
        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
    }

    public void eliminarCiclo(View v) {
        String regEliminadas;
        Ciclo ciclo = new Ciclo();
        ciclo.setId_ciclo(editIdCiclo.getText().toString());
        regEliminadas = helper.eliminar(ciclo);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
