package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Ciclo;

import proyecto.pdm.DatabaseHelper;

public class CicloBD {
    private String[] camposCiclo = {"id_ciclo", "anio_ciclo", "ciclo_num"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CicloBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);


    }

    public String insertar(Ciclo ciclo) {
        db = dbHelper.getWritableDatabase();

        String regInsertados = "Registro Insertado No= ";
        long contador = 0;

        ContentValues cicl = new ContentValues();

        cicl.put("id_ciclo", ciclo.getId_ciclo());
        cicl.put("anio_ciclo", ciclo.getAnio_ciclo());
        cicl.put("ciclo_num", ciclo.getCiclo_num());

        contador = db.insert("Ciclo", null, cicl);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado.Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;
    }

    public String actualizar(Ciclo ciclo) {

        db = dbHelper.getWritableDatabase();

        if (verificarIntegridad(ciclo, 1)) {

            String[] id = {ciclo.getId_ciclo()};
            ContentValues cicl = new ContentValues();

            cicl.put("id_ciclo", ciclo.getId_ciclo());
            cicl.put("anio_ciclo", ciclo.getAnio_ciclo());
            cicl.put("ciclo_num", ciclo.getCiclo_num());

            db.update("Ciclo", cicl, "id_ciclo=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        } else {
            dbHelper.close();
            return "Registro con Codigo ciclo: " + ciclo.getId_ciclo() + "no existe";
        }

    }

    public Ciclo consultar(String idCiclo) {

        db = dbHelper.getWritableDatabase();
        String[] id = {idCiclo};

        Cursor c = db.query("Ciclo", camposCiclo, "id_ciclo=?", id, null, null, null);
        if (c.moveToFirst()) {
            Ciclo ciclo = new Ciclo();
            ciclo.setId_ciclo(c.getString(0));
            ciclo.setAnio_ciclo(c.getString(1));
            ciclo.setCiclo_num(c.getString(2));
            dbHelper.close();
            return ciclo;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String eliminar(Ciclo ciclo) {

        db = dbHelper.getWritableDatabase();

        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(ciclo, 2)) {
            contador += db.delete("GrupoMateria", "ciclo='" + ciclo.getId_ciclo() + "'", null);
        }
        contador += db.delete("Ciclo", "id_ciclo='" + ciclo.getId_ciclo() + "'", null);
        regAfectados += contador;
        dbHelper.close();
        return regAfectados;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        db = dbHelper.getWritableDatabase();
        switch (relacion) {
            case 1: {
                //Verificar que exista Ciclo
                Ciclo ciclo = (Ciclo) dato;
                String[] id = {ciclo.getId_ciclo()};

                Cursor c = db.query("Ciclo", null, "id_ciclo=?", id, null, null, null);
                if (c.moveToFirst()) {
                    //se encontro Ciclo
                    return true;
                }
                return false;
            }
            case 2: {
                //Verificar que exista Ciclo
                Ciclo ciclo = (Ciclo) dato;
                String[] id = {ciclo.getId_ciclo()};

                Cursor c = db.query("GrupoMateria", null, "ciclo=?", id, null, null, null);
                if (c.moveToFirst()) {
                    //se encontro Ciclo en GrupoMateria
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }

    public List<Ciclo> getCiclos() {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Ciclo", camposCiclo, null, null, null, null, null, null);
        List<Ciclo> cicloList = new ArrayList<Ciclo>();
        if (c.moveToFirst()) {
            do {
                Ciclo ciclo = new Ciclo();
                ciclo.setId_ciclo(c.getString(0));
                ciclo.setAnio_ciclo(c.getString(1));
                ciclo.setCiclo_num(c.getString(2));
                cicloList.add(ciclo);
            } while (c.moveToNext());

        }
        dbHelper.close();
        return cicloList;
    }
}
