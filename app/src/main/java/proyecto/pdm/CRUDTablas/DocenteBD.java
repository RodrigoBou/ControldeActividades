package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.content.Context;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ControlBD;
import java.util.ArrayList;
import java.util.List;
import android.database.Cursor;
/**
 * Created by kelly on 06/05/2016.
 */
public class DocenteBD {

    private String[] camposDocente = {"cod_docente", "nom_docente"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public DocenteBD(Context ctx){
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();

    }

    public String insertar(Docente docente){
        String regInsertados ="Registro Insertado N°:";
        long contador = 0;

        ContentValues doc = new ContentValues();
        doc.put("codDocente", docente.getCodDocente());
        doc.put("nomDocente", docente.getNomDocente());

        contador = db.insert("Docente", null, doc);
        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        controlBD.cerrar();
        return regInsertados;
    }

    public Docente consultar(String codDocente){
        String id[] = {codDocente};
        Cursor c = db.query("Docente", camposDocente, "cod_docente=?", id, null, null, null);
        if (c.moveToFirst()){
            Docente docente = new Docente();
            docente.setCodDocente(c.getString(0));
            docente.setNomDocente(c.getString(1));
            return docente;
        }else {

     return  null;
        }
    }

    public String actualizar(Docente docente){
        return  null;
    }

    public String eliminar(Docente docente){

        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(docente, 1)) {
            contador+= db.delete("GrupoMateria", "cod_docente='"+ docente.getCodDocente()+"'", null);
        }
        contador+=db.delete("Docente", "cod_docente='"+docente.getCodDocente()+"'", null);
        regAfectados+=contador;
        return  regAfectados;
    }

    public void abrir() {
        controlBD.abrir();
        return;
    }

    public void cerrar() {
        controlBD.cerrar();
        return;
    }

    public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
        switch(relacion){
            case 1:
            {
                //Verificar que exista el Docente
                Docente docente =(Docente)dato;
                String[] id = {docente.getCodDocente()};
                abrir();
                Cursor c = db.query("Docente",null,"cod_docente=?",id,null,null,null);
                if(c.moveToFirst()){
                    //se encontro Docente
                    return true;
                }
                return false;
            }
            default: return false;
        }
    }

    public List<Docente> getDocentes(){
        Cursor c = db.query("Docente", camposDocente, null, null, null, null, null, null);
        List<Docente> docenteList = new ArrayList<Docente>();
        if(c.moveToFirst())
        {
            do {
                Docente docente = new Docente();
                docente.setCodDocente(c.getString(0));
                docente.setNomDocente(c.getString(1));
                docenteList.add(docente);
            }while(c.moveToNext());
        }

        return docenteList;
    }

}
