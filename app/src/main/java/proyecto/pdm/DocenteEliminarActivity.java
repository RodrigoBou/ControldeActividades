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

public class DocenteEliminarActivity extends Activity {

    EditText editCodDocente;
    DocenteBD controlHelper;
    UsuarioBD credencialesUsuario;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_docente_eliminar);

        controlHelper = new DocenteBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de Docente", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

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
