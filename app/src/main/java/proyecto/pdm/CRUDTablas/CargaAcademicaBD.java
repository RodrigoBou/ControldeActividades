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
            cargaAcademica.setDocente(cursor.getString(1));
            cargaAcademica.setCargo(Integer.valueOf(cursor.getInt(3)));
            cargaAcademica.setMateria(cursor.getString(0));
            cargaAcademica.setCiclo(Integer.valueOf(cursor.getInt(2)));
            controlBD.close();
            return cargaAcademica;
        }else{
            controlBD.close();
            return null;
        }
    }
    public String actualizar(CargaAcademica cargaAcademica){
        db=controlBD.getWritableDatabase();

        if(verificarIntegriad(cargaAcademica, 1)){
            String[] id={cargaAcademica.getDocente(), String.valueOf(cargaAcademica.getCiclo())};
            ContentValues cv = new ContentValues();
            cv.put("CargaAcademica", cargaAcademica.getCargo());
            cv.put("CargaAcademica", cargaAcademica.getMateria());
            db.update("CargaAcademica", cv, "docente = ? AND ciclo =?", id);
            controlBD.close();
            return "Registro Actualizado correctamente";

        }else{
            controlBD.close();
            return "No existe";
        }
    }
    public String eliminar(CargaAcademica cargaAcademica){
        db=controlBD.getWritableDatabase();
        String regAfectados ="filas afectadas= ";
        int contador = 0;

        contador+=db.delete("CargaAcademica", "docente='"+cargaAcademica.getDocente()+"' AND ciclo='"+cargaAcademica.getCiclo()+"'",null);
        regAfectados+=contador;
        controlBD.close();
        return regAfectados;
    }





    private boolean verificarIntegriad(Object dato, int relacion) throws SQLException{
        db=controlBD.getWritableDatabase();
        switch (relacion){
            case 1:
            {
                //verifica si el docente, el cargo, la meteria y el ciclo existe
                CargaAcademica cargaAcademica=(CargaAcademica)dato;
                String[] id2 = {cargaAcademica.getDocente()};
                String[] id4 ={String.valueOf(cargaAcademica.getCargo())};
                String[] id1 = {cargaAcademica.getMateria()};
                String[] id3 = {String.valueOf(cargaAcademica.getCiclo())};

                //abrir;
                Cursor cursor1=db.query("Docente", null,"nom_docente=?", id2,null,null,null);
                Cursor cursor2=db.query("Cargo", null,"nom_cargo=?",id4,null,null,null);
                Cursor cursor3=db.query("Materia", null,"nom_materia=?",id1,null,null,null);
                Cursor cursor4=db.query("Ciclo", null, "ciclo_num=?", id3, null, null, null);
                if (cursor1.moveToFirst()&&cursor2.moveToFirst()&&cursor3.moveToFirst()&&cursor4.moveToFirst()){
                    controlBD.close();
                    return true;
                }
                controlBD.close();
                return false;
            }
            case 2:{
                //verificar si la carga academica existe
                CargaAcademica cargaAcademica2 =(CargaAcademica)dato;
                String[] id ={cargaAcademica2.getDocente(), String.valueOf(cargaAcademica2.getCiclo()) };

                Cursor c2 =db.query("CargaAcademica", null,"docente=? AND ciclo =?",id,null,null,null);
                if(c2.moveToFirst()){
                    controlBD.close();
                    return true;
                }
                controlBD.close();
                return false;
            }
            case 3:{
                controlBD.close();
                return true;


            }
            default: controlBD.close();
                return false;

        }

    }


}
