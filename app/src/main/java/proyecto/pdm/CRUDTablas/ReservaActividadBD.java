package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Actividad;
import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.ClasesModelo.ReservaActividad;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by Rodrigo on 5/12/2016.
 */
public class ReservaActividadBD {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;
    private static final String [] camposReservaActividad=new String[]{"recurso","actividad"};

    public ReservaActividadBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);

    }


    public String insertar(ReservaActividad ra){

        String regIngresados="Registro Insertados N°= ";
        long contador=0;


        if (verificarIntegridad(ra)==false) {

            ContentValues c = new ContentValues();

            c.put("recurso", ra.getRecurso());
            c.put("actividad", ra.getActividad());

            try {
                db = dbHelper.getWritableDatabase();
                contador = db.insert("ReservaActividad", null, c);
                dbHelper.close();
            }catch (SQLiteException e){
                e.printStackTrace();
            }
        }

        if (contador==-1||contador==0){
            regIngresados="Error al insertar el registro, registro duplicado, verificar insercion";
        }else{
            regIngresados=regIngresados+contador;
        }

        return regIngresados;
    }



    private boolean verificarIntegridad(ReservaActividad resAc) throws SQLException {


        db=dbHelper.getWritableDatabase();

        //verificar si la reserva de actividad existe

        String rec=Integer.valueOf(resAc.getRecurso()).toString();
        String act=Integer.valueOf(resAc.getActividad()).toString();
        String[] id ={rec, act};

        Cursor c2 =db.query("ReservaActividad", null,"recurso=? AND actividad=?",id,null,null,null);
        if(c2.moveToFirst()){
            dbHelper.close();
            return true;
        }
        dbHelper.close();
        return false;

    }



    public String eliminar(ReservaActividad resAc){


        String regAfectados="";
        int contador = 0;
        db = dbHelper.getWritableDatabase();

        String rec=Integer.valueOf(resAc.getRecurso()).toString();
        String act=Integer.valueOf(resAc.getActividad()).toString();
        String[] id ={rec, act};



        try{

            contador+=db.delete("ReservaActividad", "actividad=? AND recurso=?", id);
            regAfectados = "filas afectadas";
            regAfectados+=contador;

        }

        catch(SQLException e){

            e.printStackTrace();

        }
        dbHelper.close();
        return  regAfectados;


    }




    public List<String> getRecursosActividad(Actividad act){

        String[] camposRecurso = {"nom_recurso"};

        db = dbHelper.getWritableDatabase();

        String actividad= Integer.valueOf(act.getIdActividad()).toString();

        String[] id={actividad};



        Cursor c = db.query("ReservaActividad", camposReservaActividad, "actividad=?", id, null, null, null, null);
        List<String> recursosActListt = new ArrayList<String>();
        if(c.moveToFirst())
        {

            do {


                String[] idr={c.getString(0)};



                Cursor c2=db.query("Recurso", camposRecurso, "id_recurso=?", idr, null, null, null, null);
                if (c2.moveToFirst()){

                recursosActListt.add(c2.getString(0));


                }


            }while(c.moveToNext());
        }
        dbHelper.close();
        return recursosActListt;
    }

}








