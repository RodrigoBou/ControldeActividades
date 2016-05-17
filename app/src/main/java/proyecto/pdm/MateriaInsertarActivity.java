package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;
import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Materia;

public class MateriaInsertarActivity extends Activity {
    MateriaBD helper;
    UsuarioBD credencialesUsuario;
    EditText editNomMateria;
    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);

        helper = new MateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Materia", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
        editNomMateria = (EditText) findViewById(R.id.editNomMateria);
    }

    public void insertarMateria(View v){
        String codMateria = editCodMateria.getText().toString();
        String nomMateria = editNomMateria.getText().toString();
        String regInsertados;
        Materia materia = new Materia();
        materia.setCodMateria(codMateria);
        materia.setNomMateria(nomMateria);

        regInsertados = helper.insertar(materia);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodMateria.setText("");
        editNomMateria.setText("");
    }

}