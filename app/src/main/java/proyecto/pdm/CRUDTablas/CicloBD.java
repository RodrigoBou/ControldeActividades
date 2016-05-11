package proyecto.pdm.CRUDTablas;

import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.CargaAcademicaInsertarActivity;
import proyecto.pdm.ClasesModelo.Ciclo;
import proyecto.pdm.ControlBD;

/**
 * Created by pc on 10/05/2016.
 */
public class CicloBD {
    private String[] camposCiclo = {"id_ciclo", "anio_ciclo", "ciclo_num"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public CicloBD(Context ctx){
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();

    }

    public List<Ciclo> getCiclos() {
        Cursor c= db.query("Ciclo", camposCiclo,null,null,null,null,null,null);
        List<Ciclo> cicloList = new ArrayList<Ciclo>();
        if (c.moveToFirst()){
            do {
                Ciclo ciclo= new Ciclo();
                ciclo.setId_ciclo(c.getInt(0));
               ciclo.setAnio_ciclo(c.getString(1));
                ciclo.setCiclo_num(c.getString(2));
                cicloList.add(ciclo);
            }while (c.moveToNext());

        }
        return cicloList;
    }
}
