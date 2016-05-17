package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by PDM115 on 26/04/2016.
 */

// En esta clase haceos las operaciones de CRUD para la tabla CategoriaRecurso
public class CategoriaRecursoDB {

    private String[] camposCatRecurso = {"id_categoria_recurso", "categoria_recurso"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CategoriaRecursoDB(Context ctx) {
       dbHelper = DatabaseHelper.getInstance(ctx);
        // Como pueden observar llamamos al metodo getDB desde aqui
        // Creo que esto nos puede generar problemas ya que en algún punto tendŕíamos un montoń de objetos de esta misma clase(ControlDB)
        // que en realidad no cambian nada. Me gustaria hacer la clase ControlBD un Singleton, pero ese valor de Context que tiene que recibir
        // en el constructor nos puede dar problemas. Por el momento lo dejamos asi.
    }

    public String insertar(CategoriaRecurso categoria){
        String registrosInsertados="";
        long contador = 0;

        ContentValues cat = new ContentValues();

        cat.put(camposCatRecurso[1], categoria.getCategoriaRecurso());
        try {
            db = dbHelper.getWritableDatabase();
            contador = db.insert("CategoriaRecurso", null, cat);
            dbHelper.close();
        }catch (SQLiteException e){
            e.printStackTrace();
        }

        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }

        return registrosInsertados;
    }

    public List<CategoriaRecurso> getCategorias(){

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("CategoriaRecurso", camposCatRecurso, null, null, null, null, null, null);
        List<CategoriaRecurso> categoriaRecursoList = new ArrayList<CategoriaRecurso>();
        if(c.moveToFirst())
        {
            do {
                CategoriaRecurso categoriaRecurso = new CategoriaRecurso();
                categoriaRecurso.setIdCatRecurso(c.getInt(0));
                categoriaRecurso.setCategoriaRecurso(c.getString(1));
                categoriaRecursoList.add(categoriaRecurso);
            }while(c.moveToNext());
        }
        dbHelper.close();

        return categoriaRecursoList;
    }

    public String eliminar(int id){
        String regEliminado = "";
        int contador = 0;

        try {
            db = dbHelper.getWritableDatabase();
            contador = db.delete("CategoriaRecurso", camposCatRecurso[0] + " = ?", new String[]{String.valueOf(id)});
            dbHelper.close();
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        if (contador <= 0)
            regEliminado = "No se puedo eliminar el registro";
        else
            regEliminado = "El registro fue exitosamente eliminado";

        return regEliminado;

    }

    public String actualizar(CategoriaRecurso categoria){
        String msg = "";

        long contador = 0;

        ContentValues cat = new ContentValues();

        cat.put(camposCatRecurso[1], categoria.getCategoriaRecurso());

        db = dbHelper.getWritableDatabase();
        contador = db.update("CategoriaRecurso", cat, camposCatRecurso[0]+" = ?",
                new String[] {String.valueOf(categoria.getIdCatRecurso())});
        dbHelper.close();

        msg = (contador <= 0) ? "No se pudo actualizar el registro" : "El registro fue actualizado exitosamente";

        return msg;
    }

    public CategoriaRecurso getCategoriaRecurso(int id){
        CategoriaRecurso cat = new CategoriaRecurso();

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("CategoriaRecurso", camposCatRecurso, camposCatRecurso[0]+" = ?",
                new String[] {String.valueOf(id)}, null, null, null, null);
        if(c.moveToFirst()){
            cat.setIdCatRecurso(c.getInt(0));
            cat.setCategoriaRecurso(c.getString(1));
        }
        dbHelper.close();

        return cat;
    }
}
