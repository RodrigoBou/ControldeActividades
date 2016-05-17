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

public class DocenteConsultarActivity extends Activity {
    DocenteBD helper;
    UsuarioBD credencialesUsuario;
    EditText editCodDocente;
    EditText editNomDocente;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_consultar_actualizar);

        helper = new DocenteBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de Docente", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCodDocente = (EditText) findViewById(R.id.editCodDocente);
        editNomDocente = (EditText) findViewById(R.id.editNomDocente);
    }

  public void consultarDocente(View v){

        Docente docente = helper.consultar(editCodDocente.getText().toString());
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

