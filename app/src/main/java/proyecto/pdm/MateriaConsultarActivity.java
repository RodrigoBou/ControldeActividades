package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.CRUDTablas.MateriaBD;

public class MateriaConsultarActivity extends Activity {
    MateriaBD helper;
    EditText editNomMateria;
    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_consultar);
        helper = new MateriaBD(this);
        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
        editNomMateria = (EditText) findViewById(R.id.editNomMateria);
    }

    public void consultarMateria(View v){
        helper.abrir();
        Materia materia = helper.consultar(editCodMateria.getText().toString());
        helper.cerrar();
        if (materia == null){
            Toast.makeText(this, "Materia codigo:" + editCodMateria.getText().toString() + "no encontrado",
                    Toast.LENGTH_LONG).show();
        }
        else {
            editNomMateria.setText(materia.getNomMateria());
        }
    }

    public void limpiarTextoMateria(View v){
        editCodMateria.setText("");
        editNomMateria.setText("");
    }
}