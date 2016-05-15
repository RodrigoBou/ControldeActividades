package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import java.io.Console;
import java.io.IOException;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.ClasesModelo.Recurso;

public class InformacionRecursoActivity extends Activity {

    private TextView textIdRecurso;
    private TextView textNomRecurso;
    private TextView textDetRecurso;
    private TextView textEstado;
    private TextView textCategoriaRecurso;
    private RecursoDB dbHelper;
    private CategoriaRecursoDB categoriaRecursoDB;
    private int id = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_informacion_recurso);

        textIdRecurso = (TextView) findViewById(R.id.textIdRecursoInfo);
        textNomRecurso = (TextView) findViewById(R.id.textNomRecursoInfo);
        textDetRecurso = (TextView) findViewById(R.id.textDetRecursoInfo);
        textEstado = (TextView) findViewById(R.id.textEstadoInfo);
        textCategoriaRecurso = (TextView) findViewById(R.id.textCatRecursoInfo);

        dbHelper = new RecursoDB(this);
        categoriaRecursoDB = new CategoriaRecursoDB(this);

        id = getIntent().getExtras().getInt("idRecurso");

        Recurso recurso = dbHelper.getRecurso(id);

        textIdRecurso.setText(String.valueOf(recurso.getIdRecurso()));
        textNomRecurso.setText(recurso.getNomRecurso());
        textDetRecurso.setText(recurso.getDetalleRecurso());

        String estado = (recurso.getEstado() != 0 && recurso.getEstado() == 1) ? "Activo" : "Inactivo";
        textEstado.setText(estado);

        String catRecurso = categoriaRecursoDB.getCategoriaRecurso(recurso.getCatRecurso()).getCategoriaRecurso();
        textCategoriaRecurso.setText(catRecurso);
    }

    public void cerrar(View v){
        finish();
    }
}
