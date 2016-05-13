package proyecto.pdm.CRUDTablas;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;

import proyecto.pdm.DatabaseHelper;

/**
 * Created by Rodrigo on 5/12/2016.
 */
public class ReservaActividadBD {

    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ReservaActividadBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);

    }


}
