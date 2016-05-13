package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoEliminarActivity extends Activity {
    EditText editCodTipoGrupo;
    TipoGrupoBD controlhelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_eliminar);
        controlhelper = new TipoGrupoBD(this);
        editCodTipoGrupo=(EditText) findViewById(R.id.editCod_tipo_grupo);
    }
    public void eliminarTipoGrupo(View v){
        String regEliminadas;
        TipoGrupo tipoGrupo =new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(editCodTipoGrupo.getText().toString());

        regEliminadas =controlhelper.eliminar(tipoGrupo);

        Toast.makeText(this, regEliminadas, Toast.LENGTH_SHORT).show();
    }
}
