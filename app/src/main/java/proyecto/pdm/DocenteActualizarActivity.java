package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.ClasesModelo.Docente;

public class DocenteActualizarActivity extends Activity {
    DocenteBD helper;
    EditText editCodDocente;
    EditText editNomDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_actualizar);
        helper = new DocenteBD(this);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editNomDocente = (EditText) findViewById(R.id.editNomDocente);
    }

    public void actualizarDocente(View v){
        Docente docente = new Docente();
        docente.setCodDocente(editCodDocente.getText().toString());
        docente.setNomDocente(editNomDocente.getText().toString());

        String estado = helper.actualizar(docente);

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();


    }

    public void limpiarTextoDocente(View v){
        editCodDocente.setText("");
        editNomDocente.setText("");
    }
}
