package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.ClasesModelo.Materia;

public class MateriaInsertarActivity extends Activity {
    ControlBD helper;
    EditText editNomMateria;
    EditText editCodMateria;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_materia_insertar);
        helper = new ControlBD(this);
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
        helper.abrir();
        regInsertados = helper.insertar(materia);
        helper.cerrar();
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }

    public void limpiarTexto(View v){
        editCodMateria.setText("");
        editNomMateria.setText("");
    }

}
