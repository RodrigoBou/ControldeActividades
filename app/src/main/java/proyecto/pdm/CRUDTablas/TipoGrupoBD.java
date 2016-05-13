package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.widget.Switch;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.TipoGrupo;

import proyecto.pdm.DatabaseHelper;

/**
 * Created by pc on 05/05/2016.
 */
public class TipoGrupoBD {
    String[] camposTipoGrupo=new String[]{"cod_tipo_grupo","tipo_grupo"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;



    public TipoGrupoBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);


    }
    public String insertar(TipoGrupo tipoGrupo){
        db = dbHelper.getWritableDatabase();
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
        dbHelper.close();
        return regIngresados;
    }


    public TipoGrupo consultarTipoGrupo(String cod_tipo_grupo){
        db = dbHelper.getWritableDatabase();
        String[] id = {cod_tipo_grupo};
        Cursor cursor = db.query("TipoGrupo", camposTipoGrupo,"cod_tipo_grupo=?", id, null, null, null );
        if (cursor.moveToFirst()){
            TipoGrupo tipoGrupo = new TipoGrupo();
            tipoGrupo.setcodTipoGrupo(cursor.getString(0));
            tipoGrupo.setTipoGrupo(cursor.getString(1));
            dbHelper.close();
            return tipoGrupo;
        }else{
            dbHelper.close();
            return null;
        }
    }
    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException{
        db = dbHelper.getWritableDatabase();
        switch(relacion){
            case 1:
            {
                //Verificar que exista el Tipo grupo
                TipoGrupo tipoGrupo =(TipoGrupo)dato;
                String[] id = {tipoGrupo.getcodTipoGrupo()};

                Cursor c = db.query("TipoGrupo",null,"cod_tipo_grupo=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro Tipo Grupo
                    dbHelper.close();
                    return true;
                }
                dbHelper.close();
                return false;
            }
            default:   dbHelper.close();
                return false;
        }
    }
    public String actualizar(TipoGrupo tipoGrupo){
        db = dbHelper.getWritableDatabase();
        if(verificarIntegridad(tipoGrupo,1)){
            String[]id= {tipoGrupo.getcodTipoGrupo()};
            ContentValues cv=new ContentValues();
            //cv.put("cod_tipo_dato",tipoGrupo.getcodTipoGrupo());
            cv.put("tipo_grupo",tipoGrupo.getTipoGrupo());
            db.update("TipoGrupo", cv, "cod_tipo_grupo=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        }else {
            dbHelper.close();
            return "Registro con Codigo "+tipoGrupo.getcodTipoGrupo()+" no existe";
        }
    }
    public String eliminar(TipoGrupo tipoGrupo){
        db = dbHelper.getWritableDatabase();
        String regAfectados ="filas Afectadas= ";
        int contador=0;
        if (verificarIntegridad(tipoGrupo,1)){
            contador+=db.delete("TipoGrupo", "cod_tipo_grupo='"+tipoGrupo.getcodTipoGrupo()+"'", null);
        }
        contador+=db.delete("TipoGrupo","cod_tipo_grupo='"+tipoGrupo.getcodTipoGrupo()+"'",null);
        regAfectados+=contador;
        dbHelper.close();
        return regAfectados;
    }
    public List<TipoGrupo> getTipoGrupos(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("TipoGrupo",camposTipoGrupo,null,null,null,null,null);
        List<TipoGrupo>tipoGrupoList= new ArrayList<TipoGrupo>();
        if (c.moveToFirst()){
            do{
                TipoGrupo tipoGrupo = new TipoGrupo();
                tipoGrupo.setcodTipoGrupo(c.getString(0));
                tipoGrupo.setTipoGrupo(c.getString(1));
                tipoGrupoList.add(tipoGrupo);
            }while (c.moveToNext());
        }
        dbHelper.close();
        return tipoGrupoList;
    }


}
