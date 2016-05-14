package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Horario;

import proyecto.pdm.DatabaseHelper;

public class HorarioBD {
    private String[] camposHorario = {"id_horario", "hora_ini", "hora_fin"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public HorarioBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);


    }

    public String insertar(Horario horario) {
        db = dbHelper.getWritableDatabase();

        String regInsertados = "Registro Insertado No= ";
        long contador = 0;

        ContentValues hora = new ContentValues();

        hora.put("id_horario", horario.getId_horario());
        hora.put("hora_ini", horario.getHora_ini());
        hora.put("hora_fin", horario.getHora_fin());

        contador = db.insert("Horario", null, hora);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado.Verificar inserción ";
        } else {
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;
    }

    public String actualizar(Horario horario) {

        db = dbHelper.getWritableDatabase();

        if (verificarIntegridad(horario, 1)) {
            String[] id = {String.valueOf(horario.getId_horario())};
            ContentValues hora = new ContentValues();

            hora.put("id_horario", horario.getId_horario());
            hora.put("hora_ini", horario.getHora_ini());
            hora.put("hora_fin", horario.getHora_fin());

            db.update("Horario", hora, "id_horario=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        } else {
            dbHelper.close();
            return "Registro con Codigo horario: " + horario.getId_horario() + "no existe";
        }

    }

    public Horario consultar(String idHorario) {
        db = dbHelper.getWritableDatabase();
        String[] id = {idHorario};
        Cursor c = db.query("Horario", camposHorario, "id_horario=?", id, null, null, null);
        if (c.moveToFirst()) {
            Horario horario = new Horario();
            horario.setId_horario(c.getInt(0));
            horario.setHora_ini(c.getString(1));
            horario.setHora_fin(c.getString(2));
            dbHelper.close();
            return horario;
        } else {
            dbHelper.close();
            return null;
        }
    }

    public String eliminar(Horario horario) {

        db = dbHelper.getWritableDatabase();

        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(horario, 1)) {
            contador += db.delete("GrupoMateria", "horario='" + horario.getId_horario() + "'", null);
        }
        contador += db.delete("Ciclo", "id_horario='" + horario.getId_horario() + "'", null);
        regAfectados += contador;
        dbHelper.close();
        return regAfectados;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        db = dbHelper.getWritableDatabase();
        switch (relacion) {
            case 1: {
                //Verificar que exista Horario
                Horario horario = (Horario) dato;
                String[] id = {String.valueOf(horario.getId_horario())};

                Cursor c = db.query("Horario", null, "id_horario=?", id, null, null, null);
                if (c.moveToFirst()) {
                    //se encontro Ciclo
                    dbHelper.close();
                    return true;
                }
                dbHelper.close();
                return false;
            }
            default:
                dbHelper.close();
                return false;
        }
    }

    public List<Horario> getHorarios() {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Horario", camposHorario, null, null, null, null, null, null);
        List<Horario> cicloList = new ArrayList<Horario>();
        if (c.moveToFirst()) {
            do {
                Horario horario = new Horario();
                horario.setId_horario(c.getInt(0));
                horario.setHora_ini(c.getString(1));
                horario.setHora_fin(c.getString(2));
                cicloList.add(horario);
            } while (c.moveToNext());

        }
        dbHelper.close();
        return cicloList;
    }
}
