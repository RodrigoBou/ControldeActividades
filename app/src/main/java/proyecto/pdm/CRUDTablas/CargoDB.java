package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.ClasesModelo.Cargo;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ControlBD;

/**
 * Created by kevin on 04-30-16.
 */
public class CargoDB {

    private String[] camposCargo = {"id_cargo","nom_cargo"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public CargoDB(Context ctx) {
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();
    }

    public String insertar(Cargo cargo){
        String registrosInsertados="";
        long contador = 0;

        ContentValues cat = new ContentValues();

        cat.put("nom_cargo", cargo.getNomCargo());

        contador = db.insert("Cargo", null, cat);

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