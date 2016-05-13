package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.ClasesModelo.Docente;

public class DocenteEliminarActivity extends Activity {

    EditText editCodDocente;
    DocenteBD controlHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);
        controlHelper = new DocenteBD(this);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
    }

    public void eliminarDocente(View v){
        String regEliminadas;
        Docente docente = new Docente();
        docente.setCodDocente(editCodDocente.getText().toString());

        regEliminadas=controlHelper.eliminar(docente);

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
