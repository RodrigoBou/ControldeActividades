package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import proyecto.pdm.CRUDTablas.DocenteBD;

import proyecto.pdm.ClasesModelo.Docente;

public class DocenteConsultarActivity extends Activity {
    DocenteBD helper;
    EditText editCodDocente;
    EditText editNomDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar_actualizar);
        helper = new DocenteBD(this);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editNomDocente = (EditText) findViewById(R.id.editNomDocente);
    }

  public void consultarDocente(View v){
        helper.abrir();
        Docente docente = helper.consultar(editCodDocente.getText().toString());
        helper.cerrar();
        if (docente == null){
            Toast.makeText(this, "Docente codigo" + editCodDocente.getText().toString() + "no encontrado",
                    Toast.LENGTH_LONG).show();
        }
        else {
            editNomDocente.setText(docente.getNomDocente());
        }
    }

    public void limpiarTextoDocente(View v){
        editCodDocente.setText("");
        editNomDocente.setText("");
    }
}

