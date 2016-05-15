package proyecto.pdm;

import android.app.Activity;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.ClasesModelo.Ciclo;

public class CicloActualizarActivity extends Activity {

    CicloBD helper;
    EditText editIdCiclo;
    EditText editAnioCiclo;
    EditText editNumCiclo;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ciclo_actualizar);
        helper = new CicloBD(this);
        editIdCiclo = (EditText) findViewById(R.id.editIdCiclo);
        editAnioCiclo = (EditText) findViewById(R.id.editAnioCiclo);
        editNumCiclo = (EditText) findViewById(R.id.editNumCiclo);
    }

    public void actualizarCiclo(View v) {

        String idCiclo = editIdCiclo.getText().toString();
        String anioCiclo = editAnioCiclo.getText().toString();
        String numCiclo = editNumCiclo.getText().toString();
        String regInsertados;

        Ciclo ciclo = new Ciclo();
        ciclo.setId_ciclo(idCiclo);
        ciclo.setAnio_ciclo(anioCiclo);
        ciclo.setCiclo_num(numCiclo);



        regInsertados = helper.actualizar(ciclo);

        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTextoCiclo(View v) {
        editIdCiclo.setText("");
        editAnioCiclo.setText("");
        editNumCiclo.setText("");
    }
}
