package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.ControlBD;

/**
 * Created by kelly on 06/05/2016.
 */
public class MateriaBD {

    private String[] camposMateria = {"cod_materia", "nom_materia"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public MateriaBD(Context ctx){
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();

    }

    public String insertar(Materia materia){
        String regInsertados ="Registro Insertado N°:";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("codMateria", materia.getCodMateria());
        doc.put("nomMateria", materia.getNomMateria());

        contador = db.insert("Materia", null, doc);
        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        controlBD.cerrar();
        return regInsertados;
    }

    public Materia consultar(String codMateria){

        return  null;
    }

    public String actualizar(Materia materia){
        return  null;
    }

    public String eliminar(Materia materia){
        return  null;
    }

    public List<Materia> getMaterias(){
        Cursor c = db.query("Materia", camposMateria, null, null, null, null, null, null);
        List<Materia>  materiaList = new ArrayList<Materia>();
        if(c.moveToFirst())
        {
            do {
                Materia  materia = new Materia();
                materia.setCodMateria(c.getString(0));
                materia.setNomMateria(c.getString(1));
                materiaList.add( materia);
            }while(c.moveToNext());
        }

        return  materiaList;
    }
}
