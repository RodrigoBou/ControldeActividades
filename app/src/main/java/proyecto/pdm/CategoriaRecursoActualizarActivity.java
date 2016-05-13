package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;

public class CategoriaRecursoActualizarActivity extends AppCompatActivity {

    private TextView idCatRecurso;
    private EditText catRecurso;
    private CategoriaRecursoDB helper;
    private int id;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_recurso_actualizar);

        helper = new CategoriaRecursoDB(this);

        idCatRecurso = (TextView) findViewById(R.id.textViewIdCatRecursoActualizar);
        catRecurso = (EditText) findViewById(R.id.editCategoriaRecursoActualizar);

        id = getIntent().getExtras().getInt("idCatRecurso");

        CategoriaRecurso categoriaRecurso = helper.getCategoriaRecurso(id);

        idCatRecurso.setText(String.valueOf(categoriaRecurso.getIdCatRecurso()));
        catRecurso.setText(categoriaRecurso.getCategoriaRecurso());

    }

    public void actualizarCategoriaRecurso(View v){
        String categoria = catRecurso.getText().toString();
        String msg;

        CategoriaRecurso categoriaRecurso = new CategoriaRecurso();

        categoriaRecurso.setIdCatRecurso(id);
        categoriaRecurso.setCategoriaRecurso(categoria);

        msg = helper.actualizar(categoriaRecurso);
        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void limpiarTexto(View v){
        catRecurso.setText("");
    }
}
