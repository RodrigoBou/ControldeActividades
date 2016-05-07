package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.ClasesModelo.TipoGrupo;
import proyecto.pdm.ControlBD;

/**
 * Created by pc on 05/05/2016.
 */
public class TipoGrupoBD {
    String[] camposTipoGrupo=new String[]{"cod_tipo_grupo","tipo_grupo"};
    private SQLiteDatabase db;
    private ControlBD controlBD;
    public TipoGrupoBD(Context ctx) {
        controlBD = new ControlBD(ctx);
                db = controlBD.getDb();
    }
    public String insertar(TipoGrupo tipoGrupo){
        String regIngresados="Registro Ingresado N°==";
        long contador =0;
        ContentValues tipoG = new ContentValues();
        tipoG.put("cod_tipo_grupo", tipoGrupo.getcodTipoGrupo());
        tipoG.put("tipo_grupo",tipoGrupo.getTipoGrupo());
        contador=db.insert("TipoGrupo",null, tipoG);
        if (contador==-1||contador==0){
            regIngresados="Error al igresar, Registro duplicado. Verificar insercion";

        }else{
            regIngresados=regIngresados+contador;
        }
        return regIngresados;
    }

    public void abrir() {
        controlBD.abrir();
        return;
    }

    public void cerrar() {
        controlBD.cerrar();
        return;
    }
    public TipoGrupo consultarTipoGrupo(String cod_tipo_grupo){
        String[] id = {cod_tipo_grupo};
        Cursor cursor = db.query("TipoGrupo", camposTipoGrupo,"cod_tipo_grupo=?", id, null, null, null );
        if (cursor.moveToFirst()){
            TipoGrupo tipoGrupo = new TipoGrupo();
            tipoGrupo.setcodTipoGrupo(cursor.getString(0));
            tipoGrupo.setTipoGrupo(cursor.getString(1));
            return tipoGrupo;
        }else{
            return null;
        }
    }

}
