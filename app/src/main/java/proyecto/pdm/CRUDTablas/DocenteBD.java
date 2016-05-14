package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import proyecto.pdm.ClasesModelo.Docente;

import proyecto.pdm.DatabaseHelper;

import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
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
        db = dbHelper.getWritableDatabase();
        String regInsertados ="Registro Insertado N°:";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("cod_docente", docente.getCodDocente());
        doc.put("nom_docente", docente.getNomDocente());

        contador = db.insert("Docente", null, doc);
        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;
    }

    public Docente consultar(String codDocente){
        db = dbHelper.getWritableDatabase();
        String[] id = {codDocente};
        Cursor c = db.query("Docente", camposDocente, "cod_docente=?", id, null, null, null);
        if (c.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodDocente(c.getString(0));
            docente.setNomDocente(c.getString(1));
            dbHelper.close();
            return docente;
        }else {
            dbHelper.close();
            return  null;
        }
    }

    public String actualizar(Docente docente){
        db = dbHelper.getWritableDatabase();
        if (verificarIntegridad(docente, 1)) {
            String[] id = {docente.getCodDocente()};
            ContentValues doc = new ContentValues();
            doc.put("cod_docente", docente.getCodDocente());
            doc.put("nom_docente", docente.getNomDocente());
            db.update("Materia", doc, "cod_materia=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        }else {
            dbHelper.close();
            return "Registro con Codigo docente: " + docente.getCodDocente() + "no existe";
        }

    }

    public String eliminar(Docente docente){

        db = dbHelper.getWritableDatabase();
        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(docente, 1)) {
            contador+= db.delete("GrupoMateria", "cod_docente='"+ docente.getCodDocente()+"'", null);
        }
        contador+=db.delete("Docente", "cod_docente='"+docente.getCodDocente()+"'", null);
        regAfectados+=contador;
        dbHelper.close();
        return  regAfectados;
    }


    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {

        db = dbHelper.getWritableDatabase();
        switch(relacion){
            case 1:
            {
                //Verificar que exista el Docente
                Docente docente =(Docente)dato;
                String[] id = {docente.getCodDocente()};

                Cursor c = db.query("Docente",null,"cod_docente=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro Docente
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