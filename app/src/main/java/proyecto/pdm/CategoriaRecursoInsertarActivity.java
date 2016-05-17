package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;

public class CategoriaRecursoInsertarActivity extends AppCompatActivity {

    private CategoriaRecursoDB helper;
    private UsuarioBD credencialesUsuario;
    private EditText editCategoriaRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_recurso_insertar);
        // Creamos una instancia de la clase CategoriaRecursoDB
        helper = new CategoriaRecursoDB(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de CategoriaRecurso", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        editCategoriaRecurso = (EditText) findViewById(R.id.editCategoriaRecurso);
    }

    public void insertarCategoriaRecurso(View v){
        String catRecurso = editCategoriaRecurso.getText().toString();
        String regInsertados;

        CategoriaRecurso categoriaRecurso = new CategoriaRecurso();
        categoriaRecurso.setCategoriaRecurso(catRecurso);
        // Enviamos los datos a categoriaRecursoDB para que realize la inserción
        regInsertados = helper.insertar(categoriaRecurso);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();

    }

    public void limpiarTexto(View v){
        editCategoriaRecurso.setText("");
    }
}
