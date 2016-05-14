package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

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

            db = dbHelper.getWritableDatabase();
            contador = db.insert("ReservaActividad", null, c);
            dbHelper.close();

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

                //verificar si la carga academica existe

                String rec=Integer.valueOf(resAc.getRecurso()).toString();
                String[] id ={rec};

                Cursor c2 =db.query("ReservaActividad", null,"recurso=?",id,null,null,null);
                if(c2.moveToFirst()){
                    dbHelper.close();
                    return true;
                }
               dbHelper.close();
                return false;

        }




}

