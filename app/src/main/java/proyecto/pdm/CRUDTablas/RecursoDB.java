package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.ControlBD;

/**
 * Created by kevin on 05-01-16.
 */
public class RecursoDB {

    private String[] camposRecurso = {"id_recurso", "nom_recurso", "detalle_recurso", "estado", "cat_recurso"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public RecursoDB(Context ctx) {
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();
    }

    public String insertar(Recurso recurso){
        String registrosInsertados="";
        long contador = 0;

        ContentValues reg = new ContentValues();

        reg.put("nom_recurso", recurso.getNomRecurso());
        reg.put("detalle_recurso", recurso.getDetalleRecurso());
        reg.put("estado", recurso.getEstado());
        reg.put("cat_recurso", recurso.getCatRecurso());

        contador = db.insert("Recurso", null, reg);

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