package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
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

    public CicloBD(Context ctx) {
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();

    }

    public String insertar(Ciclo ciclo) {
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

        return regInsertados;
    }

   /*public String actualizar(Ciclo ciclo) {

        if (verificarIntegridad(ciclo, 1)) {
            String[] id = {ciclo.getId_ciclo()};
            ContentValues cicl = new ContentValues();
            cicl.put("id_ciclo", ciclo.getId_ciclo());
            cicl.put("anio_ciclo", ciclo.getAnio_ciclo());
            cicl.put("ciclo_num", ciclo.getAnio_ciclo());
            db.update("Ciclo", cicl, "id_ciclo=?", id);
            return "Registro Actualizado Correctamente";
        } else {
            return "Registro con Codigo ciclo: " + ciclo.getId_ciclo() + "no existe";
        }

    }*/

    /*public String eliminar(Ciclo ciclo){

        String regAfectados = "filas afectadas";
        int contador = 0;

        if (verificarIntegridad(ciclo, 1)) {
            contador+= db.delete("GrupoMateria", "cod_docente='"+ docente.getCodDocente()+"'", null);
        }
        contador+=db.delete("Docente", "cod_docente='"+docente.getCodDocente()+"'", null);
        regAfectados+=contador;
        return  regAfectados;
    }*/

    public void abrir() {
        controlBD.abrir();
        return;
    }

    public void cerrar() {
        controlBD.cerrar();
        return;
    }

    /*public boolean verificarIntegridad(Object dato, int relacion) throws SQLException {
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
    }*/

    /*public List<Ciclo> getCiclos() {
        Cursor c = db.query("Ciclo", camposCiclo, null, null, null, null, null, null);
        List<Ciclo> cicloList = new ArrayList<Ciclo>();
        if (c.moveToFirst()) {
            do {
                Ciclo ciclo = new Ciclo();
                ciclo.setId_ciclo(c.getInt(0));
                ciclo.setAnio_ciclo(c.getString(1));
                ciclo.setCiclo_num(c.getString(2));
                cicloList.add(ciclo);
            } while (c.moveToNext());

        }
        return cicloList;
    }*/
}
