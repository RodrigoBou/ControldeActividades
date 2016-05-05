package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.Toast;
import android.view.View;

import proyecto.pdm.ClasesModelo.GrupoMateria;

public class GrupoMateriaInsertarActivity extends Activity {
    ControlBD helper;

    EditText editTipoGrupo;
    EditText editIdGrupo;
    EditText editMateria;
    EditText editDocente;
    EditText editCiclo;
    EditText editLocal;
    EditText editDiasImpartida;
    EditText editHorario;
    EditText editNumGrupo;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_insertar);
        helper = new ControlBD(this);
        editTipoGrupo = (EditText) findViewById(R.id.editTipoGrupo);
        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editMateria = (EditText) findViewById(R.id.editMateria);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editCiclo = (EditText) findViewById(R.id.editCiclo);
        editLocal = (EditText) findViewById(R.id.editIdGrupo);
        editDiasImpartida = (EditText) findViewById(R.id.editMateria);
        editHorario= (EditText) findViewById(R.id.editDocente);
        editNumGrupo= (EditText) findViewById(R.id.editCiclo);


    }

    public void insertarGrupoMateria(View v){
        String idGrupo = editIdGrupo.getText().toString();
        String tipoGrupo = editTipoGrupo.getText().toString();
        String materia = editMateria.getText().toString();
        String docente = editDocente.getText().toString();
        String ciclo = editCiclo.getText().toString();
        String local = editLocal.getText().toString();
        String diasImpartida = editDiasImpartida.getText().toString();
        String horario = editHorario.getText().toString();
        String numGrupo = editNumGrupo.getText().toString();
        String regInsertados;
        GrupoMateria grupoMateria = new GrupoMateria();
        grupoMateria.setIdGrupo(Integer.parseInt(idGrupo));
        grupoMateria.setTipoGrupo(tipoGrupo);
        grupoMateria.setMateria(materia);
        grupoMateria.setDocente(docente);
        grupoMateria.setCiclo(Integer.parseInt(ciclo));
        grupoMateria.setLocal(local);
        grupoMateria.setDiasImpartida(diasImpartida);
        grupoMateria.setHorario(Integer.parseInt(horario));
        grupoMateria.setNumGrupo(numGrupo);
        helper.abrir();
       // regInsertados=helper.insertar(grupoMateria);
        helper.cerrar();
       // Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v){
        editTipoGrupo.setText("");
        editIdGrupo.setText("");
        editMateria.setText("");
        editDocente.setText("");
        editCiclo.setText("");
        editLocal.setText("");
        editDiasImpartida.setText("");
        editHorario.setText("");
        editNumGrupo.setText("");
    }

}
