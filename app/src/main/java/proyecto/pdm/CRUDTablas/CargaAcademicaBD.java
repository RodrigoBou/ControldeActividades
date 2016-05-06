package proyecto.pdm.CRUDTablas;

/**
 * Created by pc on 05/05/2016.
 */
import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import proyecto.pdm.ClasesModelo.CargaAcademica;
import proyecto.pdm.ControlBD;

public class CargaAcademicaBD {
    private static final String [] camposCargaAcademica=new String[]{"cargo","materia","docente","ciclo"};
    private SQLiteDatabase db;
    private ControlBD controlBD;

    public CargaAcademicaBD(Context ctx) {
        controlBD = new ControlBD(ctx);
        db = controlBD.getDb();
    }
    public String insertar(CargaAcademica cargaAcademica){
        String regIngresados="Registro Insertado N°=";
        long contador =0;
        ContentValues cargaA = new ContentValues();
        cargaA.put("cargo", cargaAcademica.getCargo());
        cargaA.put("materia", cargaAcademica.getMateria());
        cargaA.put("docente", cargaAcademica.getDocente());
        cargaA.put("ciclo", cargaAcademica.getCiclo());
        contador=db.insert("CagaAcademica", null,cargaA);
        if (contador==-1||contador==0){
            regIngresados="Error al Insertar el registro. Registro duplicado. Verificar la insercion";

        }else{
            regIngresados=regIngresados+contador;
        }
        return regIngresados;
    }
    public CargaAcademica consultarCargaAcademica(String docente){
        String[] id = {docente};
        Cursor cursor= db.query("CargaAcademica",camposCargaAcademica, "docente=?",id,null,null,null);
        if (cursor.moveToFirst()){
            CargaAcademica cargaAcademica= new CargaAcademica();
            cargaAcademica.setDocente(cursor.getString(0));
            cargaAcademica.setCargo(cursor.getInt(1));
            cargaAcademica.setMateria(cursor.getString(2));
            cargaAcademica.setCiclo(cursor.getInt(3));
            return cargaAcademica;
        }else{
            return null;
        }
    }
    public String actualizar(CargaAcademica cargaAcademica){
       return null;
    }


    public void abrir() throws SecurityException{
        controlBD.abrir();
        return;

    }

    public void cerrar() {
        controlBD.cerrar();
        return;

    }
}
