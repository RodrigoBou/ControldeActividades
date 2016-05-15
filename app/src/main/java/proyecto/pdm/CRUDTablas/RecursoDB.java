package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by kevin on 05-01-16.
 */
public class RecursoDB {

    private String[] camposRecurso = {"id_recurso", "nom_recurso", "detalle_recurso", "estado", "cat_recurso"};
    private SQLiteDatabase db;
    private DatabaseHelper DBHelper;

    public RecursoDB(Context ctx) {
       DBHelper = DatabaseHelper.getInstance(ctx);
    }

    public String insertar(Recurso recurso){
        String registrosInsertados="";
        long contador = 0;

        ContentValues reg = new ContentValues();

        reg.put("nom_recurso", recurso.getNomRecurso());
        reg.put("detalle_recurso", recurso.getDetalleRecurso());
        reg.put("estado", recurso.getEstado());
        reg.put("cat_recurso", recurso.getCatRecurso());

        db = DBHelper.getWritableDatabase();
        contador = db.insert("Recurso", null, reg);
        DBHelper.close();
        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }
        return registrosInsertados;

    }



    public List<Recurso> getRecursos(){
        db = DBHelper.getWritableDatabase();
        Cursor c = db.query("Recurso", camposRecurso, null, null, null, null, null, null);
        List<Recurso> recursoList = new ArrayList<Recurso>();
        if(c.moveToFirst())
        {
            do {

                Recurso recurso=new Recurso();

                recurso.setIdRecurso(Integer.parseInt(c.getString(0)));
                recurso.setNomRecurso(c.getString(1));
                recurso.setDetalleRecurso(c.getString(2));
                recurso.setEstado(Integer.parseInt(c.getString(3)));
                recurso.setCatRecurso(Integer.parseInt(c.getString(4)));

                recursoList.add(recurso);
            }while(c.moveToNext());
        }
        DBHelper.close();
        return recursoList;
    }

    public String eliminar(int id){
        String regEliminado = "";
        int contador = 0;

        db = DBHelper.getWritableDatabase();
        contador = db.delete("Recurso", camposRecurso[0] + " = ?", new String[]{String.valueOf(id)});
        DBHelper.close();

        if (contador == 0)
            regEliminado = "No se puedo eliminar el registro";
        else
            regEliminado = "El registro fue exitosamente eliminado";

        return regEliminado;
    }

    public String actualizar(Recurso recurso){
        String msg = "";
        long contador = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(camposRecurso[1], recurso.getNomRecurso());
        contentValues.put(camposRecurso[2], recurso.getDetalleRecurso());
        contentValues.put(camposRecurso[3], recurso.getEstado());
        contentValues.put(camposRecurso[4], recurso.getCatRecurso());

        db = DBHelper.getWritableDatabase();
        db.update("Recurso", contentValues, camposRecurso[0] + " = ?",
                new String[]{String.valueOf(recurso.getIdRecurso())});
        DBHelper.close();

        msg = (contador <= 0) ? "No se pudo actualizar el registro" : "El registro fue actualizado exitosamente";

        return msg;
    }

    public Recurso getRecurso(int id) {
        Recurso recurso = new Recurso();

        db = DBHelper.getWritableDatabase();
        Cursor c = db.query("Recurso", camposRecurso, camposRecurso[0] + " = ?", new String[]{String.valueOf(id)},
                null, null, null, null);
        if (c.moveToFirst()) {
            recurso.setIdRecurso(c.getInt(0));
            recurso.setNomRecurso(c.getString(1));
            recurso.setDetalleRecurso(c.getString(2));
            recurso.setEstado(c.getInt(3));
            recurso.setCatRecurso(c.getInt(4));
        }
        DBHelper.close();

        return recurso;
    }
}