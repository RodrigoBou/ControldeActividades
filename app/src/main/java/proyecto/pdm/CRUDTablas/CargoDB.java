package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Cargo;
import proyecto.pdm.DatabaseHelper;

/**
 * Created by kevin on 04-30-16.
 */
public class CargoDB {

    private String[] camposCargo = {"id_cargo","nom_cargo"};
    private SQLiteDatabase db;
    private DatabaseHelper dbHelper;

    public CargoDB(Context ctx) {
        dbHelper = DatabaseHelper.getInstance(ctx);
    }

    public String insertar(Cargo cargo){
        String registrosInsertados="";
        long contador = 0;

        ContentValues cat = new ContentValues();

        cat.put("nom_cargo", cargo.getNomCargo());

        dbHelper.getWritableDatabase();
        contador = db.insert("Cargo", null, cat);
        dbHelper.close();

        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }
        return registrosInsertados;
    }

    public List<Cargo> getCargos(){

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Cargo", camposCargo, null, null, null, null, null, null);

        List<Cargo> cargoList = new ArrayList<Cargo>();

        if(c.moveToFirst()){
            do{
                Cargo cargo = new Cargo();
                cargo.setIdCargo(c.getInt(0));
                cargo.setNomCargo(c.getString(1));
                cargoList.add(cargo);
            }while(c.moveToNext());
        }

        dbHelper.close();
        return cargoList;
    }

    public String eliminar(int id){
        String regEliminado = "";
        int contador = 0;

        db = dbHelper.getWritableDatabase();
        contador = db.delete("Cargo", camposCargo[0] + " = ?", new String[]{String.valueOf(id)});
        dbHelper.close();

        if (contador == 0)
            regEliminado = "No se puedo eliminar el registro";
        else
            regEliminado = "El registro fue exitosamente eliminado";

        return regEliminado;
    }

    public String actualizar(Cargo cargo){
        String msg = "";
        long contador = 0;

        ContentValues contentValues = new ContentValues();
        contentValues.put(camposCargo[1], cargo.getNomCargo());

        db = dbHelper.getWritableDatabase();
        contador = db.update("Cargo", contentValues, camposCargo[0] + " = ?",
                new String[]{String.valueOf(cargo.getIdCargo())});
        dbHelper.close();

        msg = (contador <= 0) ? "No se pudo actualizar el registro" : "El registro fue actualizado exitosamente";

        return msg;
    }

    public Cargo getCargo(int id){
        Cargo cargo = new Cargo();

        db = dbHelper.getWritableDatabase();
        Cursor c = db.query("Cargo", camposCargo, camposCargo[0]+" = ?", new String[]{String.valueOf(id)}, null, null,
                null, null);
        if (c.moveToFirst()){
            cargo.setIdCargo(c.getInt(0));
            cargo.setNomCargo(c.getString(1));
        }
        dbHelper.close();

        return cargo;
    }

}
