package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.ClasesModelo.Materia;

public class MateriaEliminarActivity extends Activity {

    EditText editCodMateria;
    MateriaBD controlHelper;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_eliminar);
        controlHelper = new MateriaBD(this);
        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
    }

    public void eliminarMateria(View v){
        String regEliminadas;
        Materia materia = new Materia();
        materia.setCodMateria(editCodMateria.getText().toString());

        regEliminadas = controlHelper.eliminar(materia);

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
