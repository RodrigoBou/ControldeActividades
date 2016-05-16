package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;


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
        db = dbHelper.getWritableDatabase();
        contador = db.insert("GrupoMateria", null, grupMa);
        dbHelper.close();

        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }
        return regInsertados;

    }

    public GrupoMateria consultar(int idGrupo){
        String[] id ={String.valueOf(idGrupo)};
        db = dbHelper.getWritableDatabase();
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
        int contador;
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
        db = dbHelper.getWritableDatabase();
        contador = db.update("GrupoMateria", grupMa, "id_grupo=?", id);
        dbHelper.close();
        if (contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con idGrupo" + grupoMateria.getIdGrupo() + "no existe";
}

    public String eliminar(GrupoMateria grupoMateria){
        String regAfectados = "filas afectadas";
        int contador = 0;
        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("GrupoMateria", "id_grupo='" + grupoMateria.getIdGrupo() + "'", null);
            dbHelper.close();
        }catch (SQLiteConstraintException e) {
            e.printStackTrace();
        }
        regAfectados+=contador;
        return  regAfectados;
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
