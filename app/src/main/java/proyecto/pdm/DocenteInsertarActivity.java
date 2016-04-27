package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.ClasesModelo.Docente;

public class DocenteInsertarActivity extends Activity {
    ControlBD helper;
    EditText editCodDocente;
    EditText editNomDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);
        helper = new ControlBD(this);
        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editNomDocente = (EditText) findViewById(R.id.editNomDocente);
    }

    public  void  insertarDocente(View v ){
        String codDocente = editCodDocente.getText().toString();
        String nomDocente = editNomDocente.getText().toString();
        String regInsertados;
        Docente docente = new Docente();
        docente.setCodDocente(codDocente);
        docente.setNomDocente(nomDocente);
        helper.abrir();
        regInsertados = helper.insertar(docente);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTexto(View v){
        editCodDocente.setText("");
        editNomDocente.setText("");
    }
}
