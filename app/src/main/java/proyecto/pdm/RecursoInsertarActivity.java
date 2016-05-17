package proyecto.pdm;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Switch;
import android.widget.Toast;
import android.widget.ToggleButton;

import java.util.HashMap;
import java.util.List;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ClasesModelo.Recurso;

public class RecursoInsertarActivity extends AppCompatActivity {

    private EditText editNomRecurso;
    private EditText editDetRecurso;
    private Switch switchEstado;
    private Spinner spinnerCatRecurso;
    private HashMap<String, Integer> spinnerMap = new HashMap<String, Integer>();
    private RecursoDB dbHelper;
    private CategoriaRecursoDB categoriaRecursoDB;
    private UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurso_insertar);

        dbHelper = new RecursoDB(this);
        categoriaRecursoDB = new CategoriaRecursoDB(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Adicion de Recurso", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        List<CategoriaRecurso> categoriaRecursoList = categoriaRecursoDB.getCategorias();
        String[] spinnerResource = new String[categoriaRecursoList.size()];
        int i = 0;

        for (CategoriaRecurso cat : categoriaRecursoList){

            spinnerMap.put(cat.getCategoriaRecurso(), cat.getIdCatRecurso());
            spinnerResource[i] = cat.getCategoriaRecurso();
            i++;
        }

        editNomRecurso = (EditText) findViewById(R.id.editNomRecurso);
        editDetRecurso = (EditText) findViewById(R.id.editDetRecurso);
        switchEstado = (Switch) findViewById(R.id.switchEstado);
        spinnerCatRecurso = (Spinner) findViewById(R.id.spinnerCategoria);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCatRecurso.setAdapter(adapter);
    }

    public void insertarCargo(View v){
        String nom_recurso = editNomRecurso.getText().toString();
        String det_recurso = editDetRecurso.getText().toString();
        int estado = switchEstado.isChecked() ? 1 : 0;
        String categoria = spinnerCatRecurso.getSelectedItem().toString();

        String regInsertados = "";

        Recurso recurso = new Recurso();

        recurso.setNomRecurso(nom_recurso);
        recurso.setDetalleRecurso(det_recurso);
        recurso.setEstado(estado);
        recurso.setCatRecurso(spinnerMap.get(categoria));

        regInsertados = dbHelper.insertar(recurso);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editNomRecurso.setText("");
        editDetRecurso.setText("");
        switchEstado.setChecked(false);
    }
}
