package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
import android.database.sqlite.SQLiteException;

/**
 * Created by kelly on 06/05/2016.
 */
public class DocenteBD {

    private String[] camposDocente = {"cod_docente", "nom_docente"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public DocenteBD(Context ctx){
       dbHelper = DatabaseHelper.getInstance(ctx);

    }

    public String insertar(Docente docente){
        String regInsertados ="Registro Insertado N°:";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("cod_docente", docente.getCodDocente());
        doc.put("nom_docente", docente.getNomDocente());
        try {
            db = dbHelper.getWritableDatabase();
            contador = db.insert("Docente", null, doc);
            dbHelper.close();
        }catch (SQLiteException e)
        {
            e.printStackTrace();
        }
        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public Docente consultar(String codDocente){
        String[] id = {codDocente};
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Docente", camposDocente, "cod_docente=?", id, null, null, null);
        if (c.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodDocente(c.getString(0));
            docente.setNomDocente(c.getString(1));
            dbHelper.close();
            return docente;
        }else {

     return  null;
        }
    }

    public String actualizar(Docente docente){
        int contador;
        String[] id = {docente.getCodDocente()};
        ContentValues doc = new ContentValues();
        doc.put("cod_docente", docente.getCodDocente());
        doc.put("nom_docente", docente.getNomDocente());
        db = dbHelper.getWritableDatabase();
        contador = db.update("Materia", doc, "cod_materia=?", id);
        dbHelper.close();
        if (contador > 0)
            return "Registro Actualizado Correctamente";
        else
            return "Registro con Codigo docente: " + docente.getCodDocente() + "no existe";


    }

    public String eliminar(Docente docente){

        String regAfectados = "filas afectadas";
        int contador = 0;
        try {
            db = dbHelper.getWritableDatabase();
            contador += db.delete("Docente", "cod_docente='" + docente.getCodDocente() + "'", null);
            dbHelper.close();
            regAfectados += contador;
        }catch (SQLiteConstraintException e){
            e.printStackTrace();
        }
        return  regAfectados;
    }

    public List<Docente> getDocentes(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Docente", camposDocente, null, null, null, null, null, null);
        List<Docente> docenteList = new ArrayList<Docente>();
        if(c.moveToFirst())
        {
            do {
                Docente docente = new Docente();
                docente.setCodDocente(c.getString(0));
                docente.setNomDocente(c.getString(1));
                docenteList.add(docente);
            }while(c.moveToNext());
        }
        dbHelper.close();

        return docenteList;
    }

}
