package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.DocenteBD;

import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Docente;

public class DocenteInsertarActivity extends Activity {
    DocenteBD helper;
    UsuarioBD credencialesUsuario;
    EditText editCodDocente;
    EditText editNomDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_insertar);

        helper = new DocenteBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Docente", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

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
        regInsertados = helper.insertar(docente);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }
    public void limpiarTextoDocente(View v){
        editCodDocente.setText("");
        editNomDocente.setText("");
    }
}
