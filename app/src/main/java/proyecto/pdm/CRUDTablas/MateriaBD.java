package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Materia;

import proyecto.pdm.DatabaseHelper;

/**
 * Created by kelly on 06/05/2016.
 */
public class MateriaBD {

    private String[] camposMateria = {"cod_materia", "nom_materia"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public MateriaBD(Context ctx){
        dbHelper = DatabaseHelper.getInstance(ctx);

    }

    public String insertar(Materia materia){
        db = dbHelper.getWritableDatabase();

        String regInsertados ="Registro Insertado N°:";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("cod_materia", materia.getCodMateria());
        doc.put("nom_materia", materia.getNomMateria());

        contador = db.insert("Materia", null, doc);
        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;
    }

    public Materia consultar(String codMateria){
        db = dbHelper.getWritableDatabase();

        String [] id = {codMateria};
        Cursor c = db.query("Materia", camposMateria, "cod_materia=?", id, null, null, null);
        if (c.moveToFirst()){
            Materia  materia = new Materia();
            materia.setCodMateria(c.getString(0));
            materia.setNomMateria(c.getString(1));
            dbHelper.close();
            return materia;
        }else {
            dbHelper.close();
            return null;
        }
    }

    public String actualizar(Materia materia){
        db = dbHelper.getWritableDatabase();

        if (verificarIntegridad(materia, 1)) {
            String[] id = {materia.getCodMateria()};
            ContentValues doc = new ContentValues();
            doc.put("cod_materia", materia.getCodMateria());
            doc.put("nom_materia", materia.getNomMateria());
            db.update("Materia", doc, "cod_materia=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        }else {
            dbHelper.close();
            return "Registro con Codigo materia: " +materia.getCodMateria() + "no existe";
        }

    }

    public String eliminar(Materia materia){
        db = dbHelper.getWritableDatabase();

        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(materia, 1)) {
            contador+= db.delete("GrupoMateria", "cod_materia='"+ materia.getCodMateria()+"'", null);
        }
        contador+=db.delete("Materia", "cod_materia='"+materia.getCodMateria()+"'", null);
        regAfectados+=contador;
        dbHelper.close();
        return  regAfectados;
    }



    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        db = dbHelper.getWritableDatabase();
        switch(relacion){
            case 1:
            {
                //Verificar que exista la Materia
                Materia materia =(Materia)dato;
                String[] id = {materia.getCodMateria()};

                Cursor c = db.query("Materia",null,"cod_materia=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro Materia
                    dbHelper.close();
                    return true;
                }
                dbHelper.close();
                return false;
            }
            default:

                dbHelper.close();
                return false;
        }
    }

    public List<Materia> getMaterias(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Materia", camposMateria, null, null, null, null, null, null);
        List<Materia>  materiaList = new ArrayList<Materia>();
        if(c.moveToFirst())
        {
            do {
                Materia  materia = new Materia();
                materia.setCodMateria(c.getString(0));
                materia.setNomMateria(c.getString(1));
                materiaList.add( materia);
            }while(c.moveToNext());
        }
        dbHelper.close();
        return  materiaList;
    }
}