package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;

import org.xml.sax.Parser;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.GrupoMateria;

import proyecto.pdm.ClasesModelo.Reserva;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by Yohalmo on 14/05/2016.
 */
public class ReservaBD {

    private String[] camposReserva = {"id_reservacion", "recurso", "grupo"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public ReservaBD(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);

    }

    public String insertar(Reserva reserva) {
        db = dbHelper.getWritableDatabase();
        String regInsertados = "Registro Insertado N°:";
        long contador = 0;

        ContentValues reser = new ContentValues();
        reser.put("id_reservacion", reserva.getIdReserva());
        reser.put("recurso", reserva.getRecurso());
        reser.put("grupo", reserva.getGrupo());

        contador = db.insert("Reserva", null, reser);

        if (contador == 0 || contador == -1) {
            regInsertados = "Error al insertar el registro, Registro duplicado. Verificar insercion";
        } else {
            regInsertados = regInsertados + contador;
        }

        dbHelper.close();
        return regInsertados;

    }

    public Reserva consultar(int idReserva) {
        db = dbHelper.getWritableDatabase();
        String[] id = {String.valueOf(idReserva)};

        Cursor c = db.query("Reserva", camposReserva, "id_reservacion=?", id, null, null, null);

        if (c.moveToFirst()) {
            Reserva reserva = new Reserva();
            reserva.setIdReserva(c.getInt(0));
            reserva.setRecurso(c.getInt(1));
            reserva.setGrupo(c.getInt(2));
            return reserva;
        } else {
            dbHelper.close();
            return null;
        }

    }

    public String actualizar(Reserva reserva) {
        db = dbHelper.getWritableDatabase();
        if (verificarIntegridad(reserva, 1)) {
            String[] id = {String.valueOf(reserva.getIdReserva())};

            ContentValues reser = new ContentValues();
            reser.put("id_reservacion", reserva.getIdReserva());
            reser.put("recurso", reserva.getRecurso());
            reser.put("grupo", reserva.getGrupo());
            db.update("Reserva", reser, "id_reservacion=?", id);
            dbHelper.close();
            return "Registro Actualizado Correctamente";
        } else {
            dbHelper.close();
            return "Registro con idReservac" + reserva.getIdReserva() + "no existe";
        }
    }

    public String eliminar(Reserva reserva) {
        db = dbHelper.getWritableDatabase();
        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(reserva, 1)) {
            contador += db.delete("Reserva", "id_reservacion='" + reserva.getIdReserva() + "'", null);
        }
        regAfectados += contador;
        dbHelper.close();
        return regAfectados;
    }


    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        db = dbHelper.getWritableDatabase();
        switch (relacion) {
            case 1: {
                //Verificar que exista la Reserva
                Reserva reserva = (Reserva) dato;
                String[] id = {String.valueOf(reserva.getIdReserva())};

                Cursor c = db.query("Reserva", null, "id_reservacion=?", id, null, null, null);
                if (c.moveToFirst()) {
                    //se encontro Reserva
                    return true;
                }
                return false;
            }
            default:
                return false;
        }
    }


    public List<Reserva> gerReservas() {
        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Reserva", camposReserva, null, null, null, null, null, null);
        List<Reserva> reservaList = new ArrayList<Reserva>();
        if (c.moveToFirst()) {
            do {
                Reserva reserva = new Reserva();
                reserva.setIdReserva(c.getInt(0));
                reserva.setRecurso(c.getInt(1));
                reserva.setGrupo(c.getInt(2));
                reservaList.add(reserva);
            } while (c.moveToNext());
        }
        dbHelper.close();
        return reservaList;
    }
}
