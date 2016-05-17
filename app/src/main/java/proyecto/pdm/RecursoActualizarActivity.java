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
import android.widget.TextView;
import android.widget.Toast;
import android.widget.ToggleButton;

import org.w3c.dom.Text;

import java.util.HashMap;
import java.util.List;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ClasesModelo.Recurso;

public class RecursoActualizarActivity extends AppCompatActivity {

    private TextView textIdRecurso;
    private EditText editNomRecurso;
    private EditText editDetRecurso;
    private Switch switchEstado;
    private Spinner spinnerCatRecurso;
    private HashMap<String, Integer> spinnerMap = new HashMap<String, Integer>();
    private RecursoDB dbHelper;
    private CategoriaRecursoDB categoriaRecursoDB;
    private int id;
    private UsuarioBD credencialesUsuario;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurso_actualizar);

        dbHelper = new RecursoDB(this);
        categoriaRecursoDB = new CategoriaRecursoDB(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Modificacion de Recurso", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        Recurso recurso = dbHelper.getRecurso(id);

        List<CategoriaRecurso> categoriaRecursoList = categoriaRecursoDB.getCategorias();
        String[] spinnerResource = new String[categoriaRecursoList.size()];
        int i = 0, position = 0;

        for (CategoriaRecurso cat : categoriaRecursoList){

            spinnerMap.put(cat.getCategoriaRecurso(), cat.getIdCatRecurso());
            spinnerResource[i] = cat.getCategoriaRecurso();
            if (recurso.getCatRecurso() == cat.getIdCatRecurso())
            {
                position = i;
            }
            i++;
        }

        textIdRecurso = (TextView) findViewById(R.id.textIdRecursoActualizar);
        editNomRecurso = (EditText) findViewById(R.id.editNomRecurso);
        editDetRecurso = (EditText) findViewById(R.id.editDetRecurso);
        switchEstado = (Switch) findViewById(R.id.switchEstadoActualizar);
        spinnerCatRecurso = (Spinner) findViewById(R.id.spinnerCategoria);

        ArrayAdapter<String> adapter = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,
                spinnerResource);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        spinnerCatRecurso.setAdapter(adapter);

        id = getIntent().getExtras().getInt("idRecurso");

        boolean estado = recurso.getEstado() != 0 && recurso.getEstado() == 1;

        textIdRecurso.setText(String.valueOf(recurso.getEstado()));
        editNomRecurso.setText(recurso.getNomRecurso());
        editDetRecurso.setText(recurso.getDetalleRecurso());
        switchEstado.setChecked(estado);
        spinnerCatRecurso.setSelection(position);

    }

    public void actualizarRecurso(View v){
        String nom_recurso = editNomRecurso.getText().toString();
        String det_recurso = editDetRecurso.getText().toString();
        int estado = switchEstado.isChecked() ? 1 : 0;
        String categoria = spinnerCatRecurso.getSelectedItem().toString();
        String msg;

        Recurso recurso = new Recurso();

        recurso.setIdRecurso(id);
        recurso.setNomRecurso(nom_recurso);
        recurso.setDetalleRecurso(det_recurso);
        recurso.setEstado(estado);
        recurso.setCatRecurso(spinnerMap.get(categoria));

        msg = dbHelper.actualizar(recurso);

        Toast.makeText(this, msg, Toast.LENGTH_SHORT).show();
        finish();
    }

    public void limpiarTexto(View v){
        editNomRecurso.setText("");
        editDetRecurso.setText("");
        switchEstado.setChecked(false);
    }
}
