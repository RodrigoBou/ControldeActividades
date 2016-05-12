package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.ClasesModelo.Actividad;
import proyecto.pdm.ControlBD;

/**
 * Created by Rodrigo on 5/11/2016.
 */
public class ActividadBD {

    private SQLiteDatabase db;
    private ControlBD controlBD;
    private static final String [] camposCargaAcademica=new String[]{"id_actividad","nom_actividad","detalle_actividad",
                                "fecha", "hora_ini", "hora_fin", "docente"};


    public ActividadBD(Context ctx) {
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();
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




        contador = db.insert("Actividad", null, reg);

        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }
        controlBD.cerrar();
        return registrosInsertados;
    }
}
