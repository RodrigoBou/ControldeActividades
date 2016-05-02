package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.view.View;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;

public class CategoriaRecursoInsertarActivity extends AppCompatActivity {

    private CategoriaRecursoDB helper;
    private EditText editCategoriaRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_recurso_insertar);
        // Creamos una instancia de la clase CategoriaRecursoDB
        helper = new CategoriaRecursoDB(this);
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
