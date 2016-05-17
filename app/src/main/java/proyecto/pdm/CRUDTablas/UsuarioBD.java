package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import proyecto.pdm.DatabaseHelper;

/**
 * Created by kevin on 05-16-16.
 */
public class UsuarioBD {

    private String[] camposUsuario = new String[]{"id_usuario", "nombre_usuario", "clave"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public UsuarioBD(Context ctx){
        dbHelper = DatabaseHelper.getInstance(ctx);
    }

    public boolean validarUsuario(String nombreUsuario, String password){
        boolean validacion = false;
        int cantidad;
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Usuario", new String[]{"COUNT("+camposUsuario[0]+")"},
                camposUsuario[1] + " = ? AND " +camposUsuario[2] + " = ?", new String[]{nombreUsuario, password}, null,
                null, null, null);
        if (c.moveToFirst()){
            cantidad = c.getInt(0);
            if (cantidad == 1){
                validacion = true;
            }
        }
        return validacion;
    }

    public int getIdUsuario(String nombreUsuario, String password){
        int id = -1;
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Usuario", new String[]{camposUsuario[0]}, camposUsuario[1] + " = ? AND "
                + camposUsuario[2] + " = ?", new String[]{nombreUsuario, password}, null, null, null, null);
        if (c.moveToFirst()){
            id = c.getInt(0);
        }
        return id;
    }

    public boolean validarPermiso(String permiso, int idUsuario){
        boolean validacion = false;
        int cantidad;
        db = dbHelper.getWritableDatabase();
        Cursor c = db.rawQuery("SELECT COUNT(id_usuario) FROM AccesoUsuario,OpcionCRUD  where id_usuario = ? AND " +
                        "AccesoUsuario.id_opcion = OpcionCRUD.id_opcion AND OpcionCRUD.des_opcion = ?",
                new String[]{String.valueOf(idUsuario), permiso});
        if (c.moveToFirst()){
            cantidad = c.getInt(0);
            if (cantidad == 1){
                validacion = true;
            }
        }
        return validacion;
    }

    public void ingresarUsuarios(String nom_user, String pass){
        ContentValues reg = new ContentValues();

        reg.put(camposUsuario[1], nom_user);
        reg.put(camposUsuario[2], pass);

        try{
            if (getIdUsuario(nom_user, pass) < 0) {
                db = dbHelper.getWritableDatabase();
                db.insert("Usuario", null, reg);
                dbHelper.close();
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public void ingresarOpcionesCRUD(String desOpcion, int numCRUD){
        ContentValues reg = new ContentValues();

        reg.put("des_opcion", desOpcion);
        reg.put("num_crud", numCRUD);

        try{
            if(getOpcionCRUD(desOpcion) < 0) {
                db = dbHelper.getWritableDatabase();
                db.insert("OpcionCRUD", null, reg);
                dbHelper.close();
            }
        }catch (SQLiteException e){
            e.printStackTrace();
        }
    }

    public int getOpcionCRUD(String desOpcion){

        int opcion = -1;
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("OpcionCRUD", new String[]{"id_opcion"}, "des_opcion = ?", new String[]{desOpcion}, null,
                null, null, null);
        if (c.moveToFirst()){
            opcion = c.getInt(0);
        }
        dbHelper.close();
        return opcion;
    }

    public void ingresarPermisos(int idUsuario, String desOpcion){
        ContentValues reg = new ContentValues();
        int opcionCRUD = getOpcionCRUD(desOpcion);

        if (opcionCRUD > 0) {
            reg.put("id_usuario", idUsuario);
            reg.put("id_opcion", opcionCRUD);
            try {
                db = dbHelper.getWritableDatabase();
                db.insert("AccesoUsuario", null, reg);
                db.close();
            } catch (SQLiteException e) {
                e.printStackTrace();
            }
        }
    }


}
