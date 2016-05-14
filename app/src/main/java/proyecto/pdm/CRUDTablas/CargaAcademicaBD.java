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

import java.util.ArrayList;
import java.util.List;

import proyecto.pdm.ClasesModelo.CargaAcademica;

import proyecto.pdm.DatabaseHelper;

public class CargaAcademicaBD {
    private static final String [] camposCargaAcademica=new String[]{"cargo","materia","docente","ciclo"};
    private SQLiteDatabase db;
    private DatabaseHelper controlBD;

    public CargaAcademicaBD(Context ctx) {
        controlBD = DatabaseHelper.getInstance(ctx);
    }

    public String insertar(CargaAcademica cargaAcademica){
        String regIngresados="Registro Insertados N°= ";
        long contador=0;
        if(verificarIntegriad(cargaAcademica,3)){
            ContentValues ca= new ContentValues();
            ca.put("materia", cargaAcademica.getMateria());
            ca.put("docente",cargaAcademica.getDocente());
            ca.put("ciclo",cargaAcademica.getCiclo());

            ca.put("cargo", cargaAcademica.getCargo());

            db= controlBD.getWritableDatabase();
            contador =db.insert("CargaAcademica",null,ca);
            controlBD.close();
        }
        if (contador==-1||contador==0){
            regIngresados="Error al insertar el registro, registro duplicado, verificar insercion";
        }else{
            regIngresados=regIngresados+contador;
        }
        controlBD.close();
        return regIngresados;
    }

    public CargaAcademica consultar(String docente, String ciclo){
        db=controlBD.getWritableDatabase();
        String[] id = {docente,ciclo};
        Cursor cursor= db.query("CargaAcademica",camposCargaAcademica, "docente=? AND ciclo=?",id,null,null,null);
        if (cursor.moveToFirst()){
            CargaAcademica cargaAcademica= new CargaAcademica();
            cargaAcademica.setDocente(cursor.getString(0));
            cargaAcademica.setCargo(cursor.getString(3));
            cargaAcademica.setMateria(cursor.getString(2));
            cargaAcademica.setCiclo(cursor.getString(1));
            return cargaAcademica;
        }else{
            return null;
        }
    }
    public String actualizar(CargaAcademica cargaAcademica){

        if(verificarIntegriad(cargaAcademica, 1)){
            String[] id={cargaAcademica.getDocente(), cargaAcademica.getCiclo()};
            ContentValues cv = new ContentValues();
            cv.put("CargaAcademica", cargaAcademica.getCargo());
            cv.put("CargaAcademica", cargaAcademica.getMateria());
            db.update("CargaAcademica", cv, "docente = ? AND ciclo =?", id);
            return "Registro Actualizado correctamente";

        }else{
            return "No existe";
        }
    }
    public String eliminar(CargaAcademica cargaAcademica){
        String regAfectados ="filas afectadas= ";
        int contador = 0;
        db=controlBD.getWritableDatabase();
        String mat = cargaAcademica.getMateria().toString();
        String doc = cargaAcademica.getDocente().toString();
        String cic = cargaAcademica.getCiclo().toString();
        String car = cargaAcademica.getCargo().toString();
        String[]id={mat, doc, cic, car};
        try{
            contador+=db.delete("CargaAcademica", "materia=? AND docente=? AND ciclo=? AND cargo=?", id);
            regAfectados="filas afectadas";
            regAfectados+=contador;

        }catch (SQLException e){
            e.printStackTrace();
        }
        controlBD.close();
        return  regAfectados;
    }


    public void abrir() throws SQLException{
        controlBD.getWritableDatabase();
        return;

    }

    public void cerrar() {
        controlBD.close();
        return;

    }
    private boolean verificarIntegriad(Object dato, int relacion) throws SQLException{
        switch (relacion){
            case 1:
            {
                //verifica si el docente, el cargo, la meteria y el ciclo existe
                CargaAcademica cargaAcademica=(CargaAcademica)dato;
                String[] id2 = {cargaAcademica.getDocente()};
                String[] id4 ={cargaAcademica.getCargo()};
                String[] id1 = {cargaAcademica.getMateria()};
                String[] id3 = {cargaAcademica.getCiclo()};

                //abrir;
                Cursor cursor1=db.query("Docente", null,"nom_docente=?", id2,null,null,null);
                Cursor cursor2=db.query("Cargo", null,"nom_cargo=?",id4,null,null,null);
                Cursor cursor3=db.query("Materia", null,"nom_materia=?",id1,null,null,null);
                Cursor cursor4=db.query("Ciclo", null, "ciclo_num=?", id3, null, null, null);
                if (cursor1.moveToFirst()&&cursor2.moveToFirst()&&cursor3.moveToFirst()&&cursor4.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 2:{
                //verificar si la carga academica existe
                CargaAcademica cargaAcademica2 =(CargaAcademica)dato;
                String[] id ={cargaAcademica2.getDocente(), cargaAcademica2.getCiclo(), };
                abrir();
                Cursor c2 =db.query("CargaAcademica", null,"docente=? AND ciclo =?",id,null,null,null);
                if(c2.moveToFirst()){
                    return true;
                }
                return false;
            }
            case 3:{
                return true;
            }
            default:return false;

        }

    }


}
