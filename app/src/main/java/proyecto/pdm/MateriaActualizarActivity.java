package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.ClasesModelo.Materia;

public class MateriaActualizarActivity extends Activity {
    MateriaBD helper;
    EditText editNomMateria;
    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_actualizar);
        helper = new MateriaBD(this);
        editCodMateria = (EditText) findViewById(R.id.editCodMateria);
        editNomMateria = (EditText) findViewById(R.id.editNomMateria);
    }

    public void actualizarMateria(View v){
        Materia materia = new Materia();
        materia.setCodMateria(editCodMateria.getText().toString());
        materia.setNomMateria(editNomMateria.getText().toString());

        helper.abrir();
        String estado = helper.actualizar(materia);
        helper.cerrar();

        Toast.makeText(this, estado, Toast.LENGTH_SHORT).show();


    }
    public void limpiarTexto(View v){
        editCodMateria.setText("");
        editNomMateria.setText("");
    }
}
