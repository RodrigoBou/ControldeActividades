package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoConsultarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editCod_tipo_grupo;
    EditText editTipoGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_consultar);
        helper = new TipoGrupoBD(this);
        editCod_tipo_grupo=(EditText) findViewById(R.id.editCod_tipo_grupo);
        editTipoGrupo=(EditText) findViewById(R.id.editTipoGrupo);
    }
    public void consultarTipoGrupo(View v){

        TipoGrupo tipoGrupo= helper.consultarTipoGrupo(editCod_tipo_grupo.getText().toString());

        if (tipoGrupo==null)
            Toast.makeText(this, "Tipo Grupo con codigo de Grupo" + editCod_tipo_grupo.getText().toString()+"no encontrado",Toast.LENGTH_SHORT).show();
        else {
            editTipoGrupo.setText(tipoGrupo.getTipoGrupo());
        }
    }
    public void limpiarTexto(View v){
        editCod_tipo_grupo.setText("");
        editTipoGrupo.setText("");
    }

}
