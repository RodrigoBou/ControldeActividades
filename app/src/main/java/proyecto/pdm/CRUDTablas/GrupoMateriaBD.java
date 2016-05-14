package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.GrupoMateria;

import proyecto.pdm.DatabaseHelper;

/**
 * Created by kelly on 06/05/2016.
 */
public class GrupoMateriaBD {

    private String[] camposGrupoMateria = {"id_grupo", "tipo_grupo", "materia", "docente", "ciclo", "LOCAL", "diasImpartida", "num_grupo", "horario"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public GrupoMateriaBD(Context ctx){
        dbHelper = DatabaseHelper.getInstance(ctx);


    }
    public String insertar(GrupoMateria grupoMateria){
        db = dbHelper.getWritableDatabase();
        String regInsertados="Registro Insertado N°:";
        long contador = 0;

        ContentValues grupMa = new ContentValues();
        grupMa.put("tipoGrupo", grupoMateria.getTipoGrupo());
        grupMa.put("materia", grupoMateria.getMateria());
        grupMa.put("docente", grupoMateria.getDocente());
        grupMa.put("ciclo", grupoMateria.getCiclo());
        grupMa.put("local", grupoMateria.getLocal());
        grupMa.put("diasImpartida", grupoMateria.getDiasImpartida());
        grupMa.put("numGrupo", grupoMateria.getNumGrupo());
        grupMa.put("horario", grupoMateria.getHorario());
        contador = db.insert("GrupoMateria", null, grupMa);

        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;

    }

    public GrupoMateria consultar(int idGrupo){
        db = dbHelper.getWritableDatabase();
        String[] id ={String.valueOf(idGrupo)};
        Cursor c = db.query("GrupoMateria", camposGrupoMateria, "id_grupo=?", id, null, null, null);
        if (c.moveToFirst()){
            GrupoMateria grupoMateria = new GrupoMateria();
            grupoMateria.setIdGrupo(c.getInt(0));
            grupoMateria.setTipoGrupo(c.getString(1));
            grupoMateria.setMateria(c.getString(2));
            grupoMateria.setDocente(c.getString(3));
            grupoMateria.setCiclo(c.getString(4));
            grupoMateria.setLocal(c.getString(5));
            grupoMateria.setDiasImpartida(c.getString(6));
            grupoMateria.setNumGrupo(c.getString(7));
            grupoMateria.setHorario(c.getInt(8));
            dbHelper.close();
            return grupoMateria;
        }else {
            dbHelper.close();
            return null;
        }

    }

    public String actualizar(GrupoMateria grupoMateria){
        db = dbHelper.getWritableDatabase();
        if (verificarIntegridad(grupoMateria, 1)){
            String[] id={String.valueOf(grupoMateria.getIdGrupo())};
            ContentValues grupMa = new ContentValues();
            grupMa.put("tipoGrupo", grupoMateria.getTipoGrupo());
            grupMa.put("materia", grupoMateria.getMateria());
            grupMa.put("docente", grupoMateria.getDocente());
            grupMa.put("ciclo", grupoMateria.getCiclo());
            grupMa.put("local", grupoMateria.getLocal());
            grupMa.put("diasImpartida", grupoMateria.getDiasImpartida());
            grupMa.put("numGrupo", grupoMateria.getNumGrupo());
            grupMa.put("horario", grupoMateria.getHorario());
            db.update("GrupoMateria", grupMa, "id_grupo=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        }else {
            dbHelper.close();
            return "Registro con idGrupo" + grupoMateria.getIdGrupo() + "no existe";
        }
    }

    public String eliminar(GrupoMateria grupoMateria){
        db = dbHelper.getWritableDatabase();
        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(grupoMateria, 1)) {
            contador += db.delete("Reserva", "grupo='" + grupoMateria.getIdGrupo() + "'", null);
        }
        contador+=db.delete("GrupoMateria", "id_grupo='"+grupoMateria.getIdGrupo()+"'", null);
        regAfectados+=contador;
        dbHelper.close();
        return  regAfectados;
    }



    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        db = dbHelper.getWritableDatabase();
        switch(relacion){
            case 1:
            {
                //Verificar que exista el GrupoMateria
                GrupoMateria grupoMateria =(GrupoMateria)dato;
                String[] id = {String.valueOf(grupoMateria.getIdGrupo())};

                Cursor c = db.query("GrupoMateria",null,"id_grupo=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro GrupoMateria
                    dbHelper.close();
                    return true;
                }
                dbHelper.close();
                return false;
            }
            default: dbHelper.close();
                return false;
        }
    }



    public List<GrupoMateria> getGruposMateria(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("GrupoMateria", camposGrupoMateria, null, null, null, null, null, null);
        List<GrupoMateria> grupoMateriaList = new ArrayList<GrupoMateria>();
        if(c.moveToFirst())
        {
            do {
                GrupoMateria grupoMateria = new GrupoMateria();
                grupoMateria.setIdGrupo(c.getInt(0));
                grupoMateria.setTipoGrupo(c.getString(1));
                grupoMateria.setMateria(c.getString(2));
                grupoMateria.setDocente(c.getString(3));
                grupoMateria.setCiclo(c.getString(4));
                grupoMateria.setLocal(c.getString(5));
                grupoMateria.setDiasImpartida(c.getString(6));
                grupoMateria.setNumGrupo(c.getString(7));
                grupoMateria.setHorario(c.getInt(8));
                grupoMateriaList.add(grupoMateria);
            }while(c.moveToNext());
        }
        dbHelper.close();
        return grupoMateriaList;
    }
}
