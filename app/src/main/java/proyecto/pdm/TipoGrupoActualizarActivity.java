package proyecto.pdm;

import android.os.Bundle;
import android.app.Activity;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoActualizarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editCodTipoGrupo;
    EditText editTipoGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_actualizar);
        helper = new TipoGrupoBD(this);
        editCodTipoGrupo =(EditText) findViewById(R.id.editCod_tipo_grupo);
        editTipoGrupo=(EditText) findViewById(R.id.editTipoGrupo);
    }
    public void actualizarTipoGrupo(View v){
        TipoGrupo tipoGrupo = new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(editCodTipoGrupo.getText().toString());
        tipoGrupo.setTipoGrupo(editTipoGrupo.getText().toString());
        helper.abrir();
        String estado=helper.actualizar(tipoGrupo);
        helper.cerrar();
        Toast.makeText(this,estado,Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editCodTipoGrupo.setText("");
        editTipoGrupo.setText("");
    }

}
