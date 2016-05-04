package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ControlBD;

/**
 * Created by PDM115 on 26/04/2016.
 */

// En esta clase haceos las operaciones de CRUD para la tabla CategoriaRecurso
public class CategoriaRecursoDB {

    private String[] camposCatRecurso = {"id_categoria_recurso", "categoria_recurso"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public CategoriaRecursoDB(Context ctx) {
        controlBD = new ControlBD(ctx);
        // Como pueden observar llamamos al metodo getDB desde aqui
        // Creo que esto nos puede generar problemas ya que en algún punto tendŕíamos un montoń de objetos de esta misma clase(ControlDB)
        // que en realidad no cambian nada. Me gustaria hacer la clase ControlBD un Singleton, pero ese valor de Context que tiene que recibir
        // en el constructor nos puede dar problemas. Por el momento lo dejamos asi. 
        db = controlBD.getDb();
    }

    public String insertar(CategoriaRecurso categoria){
        String registrosInsertados="";
        long contador = 0;

        ContentValues cat = new ContentValues();

        cat.put("categoria_recurso", categoria.getCategoriaRecurso());

        contador = db.insert("CategoriaRecurso", null, cat);

        if (contador == 0 || contador == -1){
            registrosInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        }
        else {
            registrosInsertados = "Registro Insertado Nº=" + contador;
        }
        controlBD.cerrar();
        return registrosInsertados;
    }

    public List<CategoriaRecurso> getCategorias(){
        Cursor c = db.query("CategoriaRecurso", camposCatRecurso, null, null, null, null, null, null);
        List<CategoriaRecurso> categoriaRecursoList = new ArrayList<CategoriaRecurso>();
        if(c.moveToFirst())
        {
            do {
                CategoriaRecurso categoriaRecurso = new CategoriaRecurso();
                categoriaRecurso.setIdCatRecurso(c.getInt(0));
                categoriaRecurso.setCategoriaRecurso(c.getString(1));
                categoriaRecursoList.add(categoriaRecurso);
            }while(c.moveToNext());
        }

        return categoriaRecursoList;
    }
}
