package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.GrupoMateriaBD;
import proyecto.pdm.ClasesModelo.GrupoMateria;

public class GrupoMateriaEliminarActivity extends Activity {

   EditText editIdGrupo;
    GrupoMateriaBD controlHelper;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_eliminar);
        controlHelper = new GrupoMateriaBD(this);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
    }


    public void eliminarGrupoMateria(View v){
        String regEliminadas;
        GrupoMateria grupoMateria = new GrupoMateria();
        grupoMateria.setIdGrupo(Integer.parseInt(editIdGrupo.getText().toString()));
        regEliminadas=controlHelper.eliminar(grupoMateria);
        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }


}
