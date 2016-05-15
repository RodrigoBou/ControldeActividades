package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class TipoGrupoInsertarActivity extends Activity {
    TipoGrupoBD helper;
    EditText editIdTipoGrupo;
    EditText editTipoGrupo;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_grupo_insertar);
        helper= new TipoGrupoBD(this);
        editIdTipoGrupo=(EditText)findViewById(R.id.editIdTipoGrupo);
        editTipoGrupo=(EditText)findViewById(R.id.editTipoGrupo);

    }
    public void insertarTipoGrupo(View v){
        String cod_tipo_grupo=editIdTipoGrupo.getText().toString();
        String tipo_grupo=editTipoGrupo.getText().toString();
        String regIngresados;
        TipoGrupo tipoGrupo = new TipoGrupo();
        tipoGrupo.setcodTipoGrupo(cod_tipo_grupo);
        tipoGrupo.setTipoGrupo(tipo_grupo);

        regIngresados=helper.insertar(tipoGrupo);
        Toast.makeText(this, regIngresados, Toast.LENGTH_SHORT).show();
    }
    public void limpiarTexto(View v){
        editIdTipoGrupo.setText("");
        editTipoGrupo.setText("");
    }

}
