package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by kevin on 05-01-16.
 */
public class RecursoDB {

    private String[] camposRecurso = {"id_recurso", "nom_recurso", "detalle_recurso", "estado", "cat_recurso"};
    private SQLiteDatabase db;
    private DatabaseHelper DBHelper;

    public RecursoDB(Context ctx) {
       DBHelper = DatabaseHelper.getInstance(ctx);
    }

    public String insertar(Recurso recurso){
        String registrosInsertados="";
        long contador = 0;

        ContentValues reg = new ContentValues();

        reg.put("nom_recurso", recurso.getNomRecurso());
        reg.put("detalle_recurso", recurso.getDetalleRecurso());
        reg.put("estado", recurso.getEstado());
        reg.put("cat_recurso", recurso.getCatRecurso());

        db = DBHelper.getWritableDatabase();
        contador = db.insert("Recurso", null, reg);
        DBHelper.close();
        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }
        return registrosInsertados;

    }
}