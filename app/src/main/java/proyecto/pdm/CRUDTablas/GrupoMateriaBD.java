package proyecto.pdm.CRUDTablas;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.GrupoMateria;
import proyecto.pdm.ControlBD;

/**
 * Created by kelly on 06/05/2016.
 */
public class GrupoMateriaBD {

    private String[] camposGrupoMateria = {"id_grupo", "tipo_grupo", "materia", "docente", "ciclo", "LOCAL", "diasImpartida", "num_grupo", "horario"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public GrupoMateriaBD(Context ctx){
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();

    }
    public String insertar(GrupoMateria grupoMateria){
        String regInsertados="Registro Insertado N°:";
        long contador = 0;

        ContentValues grupMa = new ContentValues();
        grupMa.put("tipoGrupo", grupoMateria.getTipoGrupo());
        grupMa.put("materia", grupoMateria.getMateria());
        grupMa.put("docente", grupoMateria.getDocente());
        grupMa.put("ciclo", grupoMateria.getCiclo());
        grupMa.put("local", grupoMateria.getLocal());
        grupMa.put("diasImpartida", grupoMateria.getDiasImpartida());
        grupMa.put("numGrupo", grupoMateria.getNumGrupo());
        grupMa.put("horario", grupoMateria.getHorario());
        contador = db.insert("GrupoMateria", null, grupMa);

        if (contador == 0 || contador == -1){
            regInsertados="Error al insertar el registro, Registro duplicado. Verificar insercion";
        }
        else{
            regInsertados = regInsertados + contador;
        }

        controlBD.cerrar();
        return regInsertados;

    }

    public GrupoMateria consultar(String idGrupo){

        return  null;
    }

    public String actualizar(GrupoMateria grupoMateria){
        return  null;
    }

    public String eliminar(GrupoMateria grupoMateria){
        return  null;
    }


    public List<GrupoMateria> getGruposMateria(){
        Cursor c = db.query("GrupoMateria", camposGrupoMateria, null, null, null, null, null, null);
        List<GrupoMateria> grupoMateriaList = new ArrayList<GrupoMateria>();
        if(c.moveToFirst())
        {
            do {
                GrupoMateria grupoMateria = new GrupoMateria();
                grupoMateria.setIdGrupo(c.getInt(0));
                grupoMateria.setTipoGrupo(c.getString(1));
                grupoMateria.setMateria(c.getString(2));
                grupoMateria.setDocente(c.getString(3));
                grupoMateria.setCiclo(c.getInt(4));
                grupoMateria.setLocal(c.getString(5));
                grupoMateria.setDiasImpartida(c.getString(6));
                grupoMateria.setNumGrupo(c.getString(7));
                grupoMateria.setHorario(c.getInt(8));
                grupoMateriaList.add(grupoMateria);
            }while(c.moveToNext());
        }

        return grupoMateriaList;
    }
}
