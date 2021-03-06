package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteConstraintException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.sql.Date;
import java.sql.Time;
import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Actividad;
import proyecto.pdm.DatabaseHelper;


/**
 * Created by Rodrigo on 5/11/2016.
 */
public class ActividadBD {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private static final String [] camposActividad=new String[]{"id_actividad","nom_actividad","detalle_actividad",
                                "fecha", "hora_ini", "hora_fin", "docente"};


    public ActividadBD(Context ctx) {
       dbHelper = DatabaseHelper.getInstance(ctx);







    }

    public String Insertar(Actividad actividad){

        String registrosInsertados="";
        long contador = 0;

        ContentValues reg = new ContentValues();

        reg.put("id_actividad", actividad.getIdActividad());
        reg.put("nom_actividad", actividad.getNomActividad());
        reg.put("detalle_actividad", actividad.getDetalleActividad());
        reg.put("fecha", actividad.getFecha().toString());
        reg.put("hora_ini", actividad.getHoraIni().toString());
        reg.put("hora_fin", actividad.getHoraFin().toString());
        reg.put("docente", actividad.getDocente());


        try {
            db = dbHelper.getWritableDatabase();
            contador = db.insert("Actividad", null, reg);
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










    public String Eliminar(Actividad actividad){

        String regAfectados="";
        int contador = 0;
        db = dbHelper.getWritableDatabase();
    try{
        contador+=db.delete("Actividad", "id_actividad='"+actividad.getIdActividad()+"'", null);
        dbHelper.close();
        regAfectados = "filas afectadas";
        regAfectados+=contador;

        }

    catch(SQLiteConstraintException e){

       e.printStackTrace();

    }

        return  regAfectados;
    }









    public Actividad consultar(String idActividad){
        String[] id = {idActividad};
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Actividad", camposActividad, "id_actividad=?", id, null, null, null);

        if (c.moveToFirst()){
            Actividad act = new Actividad();

            act.setIdActividad(Integer.parseInt(c.getString(0)));
            act.setNomActividad(c.getString(1));
            act.setDetalleActividad(c.getString(2));
            act.setFecha(Date.valueOf(c.getString(3)));
            act.setHoraIni(Time.valueOf(c.getString(4)));
            act.setHoraFin(Time.valueOf(c.getString(5)));
            act.setDocente(c.getString(6));
            dbHelper.close();
            return act;
        }else {
            dbHelper.close();
            return  null;
        }
    }


    public String actualizar(Actividad act){
        String msg = "";
        long contador = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(camposActividad[1], act.getNomActividad());
        contentValues.put(camposActividad[2], act.getDetalleActividad());
        contentValues.put(camposActividad[3], act.getFecha().toString());
        contentValues.put(camposActividad[4], act.getHoraIni().toString());
        contentValues.put(camposActividad[5], act.getHoraFin().toString());
        contentValues.put(camposActividad[6], act.getDocente());

        db = dbHelper.getWritableDatabase();
        contador = db.update("Actividad", contentValues, camposActividad[0] + " = ?",
                new String[]{String.valueOf(act.getIdActividad())});
        dbHelper.close();

        msg = (contador <= 0) ? "No se pudo actualizar el registro" : "El registro fue actualizado exitosamente";

        return msg;
    }



    public List<Actividad> getActividades(){
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Actividad", camposActividad, null, null, null, null, null, null);
        List<Actividad> actividadList = new ArrayList<Actividad>();
        if(c.moveToFirst())
        {
            do {


                Actividad act=new Actividad();

                act.setIdActividad(Integer.parseInt(c.getString(0)));
                act.setNomActividad(c.getString(1));
                act.setDetalleActividad(c.getString(2));
                act.setFecha(Date.valueOf(c.getString(3)));
                act.setHoraIni(Time.valueOf(c.getString(4)));
                act.setHoraFin(Time.valueOf(c.getString(5)));
                act.setDocente(c.getString(6));

                actividadList.add(act);
            }while(c.moveToNext());
        }
        dbHelper.close();
        return actividadList;
    }
}
