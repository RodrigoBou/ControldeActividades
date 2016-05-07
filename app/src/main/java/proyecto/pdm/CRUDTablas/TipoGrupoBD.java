package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Switch;

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
    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        switch(relacion){
            case 1:
            {
                //Verificar que exista el Tipo grupo
                TipoGrupo tipoGrupo =(TipoGrupo)dato;
                String[] id = {tipoGrupo.getcodTipoGrupo()};
                abrir();
                Cursor c = db.query("TipoGrupo",null,"cod_tipo_grupo=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro Tipo Grupo
                    return true;
                }
                return false;
            }
            default: return false;
        }
    }
    public String actualizar(TipoGrupo tipoGrupo){
        if(verificarIntegridad(tipoGrupo,1)){
            String[]id= {tipoGrupo.getcodTipoGrupo()};
            ContentValues cv=new ContentValues();
            //cv.put("cod_tipo_dato",tipoGrupo.getcodTipoGrupo());
            cv.put("tipo_grupo",tipoGrupo.getTipoGrupo());
            db.update("TipoGrupo", cv,"cod_tipo_grupo=?",id);
            return "Registro Actualizado Correctamente";
        }else {
            return "Registro con Codigo "+tipoGrupo.getcodTipoGrupo()+" no existe";
        }
    }
    public String eliminar(TipoGrupo tipoGrupo){
        String regAfectados ="filas Afectadas= ";
        int contador=0;
        if (verificarIntegridad(tipoGrupo,1)){
            contador+=db.delete("TipoGrupo", "cod_tipo_grupo='"+tipoGrupo.getcodTipoGrupo()+"'", null);
        }
        contador+=db.delete("TipoGrupo","cod_tipo_grupo='"+tipoGrupo.getcodTipoGrupo()+"'",null);
        regAfectados+=contador;
        return regAfectados;
    }


}
