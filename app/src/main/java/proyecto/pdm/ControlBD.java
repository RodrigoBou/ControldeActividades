package proyecto.pdm;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import proyecto.pdm.ClasesModelo.Ciclo;

public class ControlBD {

    private static final String[] camposCiclo = new String[]{"id_ciclo", "anio_ciclo", "ciclo_num"};

    private final Context context;
    private DatabaseHelper DBHelper;
    private SQLiteDatabase db;


    public ControlBD(Context ctx) {
        this.context = ctx;
        DBHelper = new DatabaseHelper(context);
    }


    private static class DatabaseHelper extends SQLiteOpenHelper {

        private static final String BASE_DATOS = "PDM.s3db";
        private static final int VERSION = 1;

        public DatabaseHelper(Context context) {
            super(context, BASE_DATOS, null, VERSION);

        }

        @Override
        public void onCreate(SQLiteDatabase db) {
            try{



                //CREACIÓN DE TABLAS

                db.execSQL("CREATE TABLE [Cargo] ( [id_cargo] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [nom_cargo] VARCHAR(50) NOT NULL );");

                db.execSQL("CREATE TABLE [Recurso] ( [id_recurso] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [nom_recurso] VARCHAR(50) NOT NULL, [detalle_recurso] VARCHAR(500) NOT NULL, [estado] INTEGER NOT NULL, [cat_recurso] INTEGER NOT NULL );");

                db.execSQL("CREATE TABLE [TipoGrupo] ( [cod_tipo_grupo] VARCHAR(5) NOT NULL PRIMARY KEY, [tipo_grupo] VARCHAR(25) NOT NULL );");

                db.execSQL("CREATE TABLE Docente (cod_docente VARCHAR (7) PRIMARY KEY NOT NULL, nom_docente VARCHAR (150) NOT NULL);");

                db.execSQL("CREATE TABLE [Ciclo] ( [id_ciclo] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [anio_ciclo] VARCHAR(4) NOT NULL, [ciclo_num] VARCHAR(2) NOT NULL );");

                db.execSQL("CREATE TABLE [CategoriaRecurso] ( [id_categoria_recurso] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [categoria_recurso] VARCHAR(50) NOT NULL );");

                db.execSQL("CREATE TABLE [ReservaActividad] ( [recurso] INTEGER NOT NULL, [actividad] INTEGER NOT NULL );");

                db.execSQL("CREATE TABLE Horario (id_horario INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, hora_ini VARCHAR (8) NOT NULL, hora_fin VARCHAR (8) NOT NULL);");

                db.execSQL("CREATE TABLE [Reservacion] ( [id_reservacion] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [recurso] INTEGER NOT NULL, [grupo] INTEGER NOT NULL );");

                db.execSQL("CREATE TABLE [Actividad] ( [id_actividad] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [nom_actividad] VARCHAR(100) NOT NULL, [detalle_actividad] VARCHAR(500) NOT NULL, [fecha] VARCHAR(9) NOT NULL, [hora_ini] VARCHAR(8) NOT NULL, [hora_fin] VARCHAR(8) NOT NULL, [docente] VARCHAR(7) NOT NULL );");

                db.execSQL("CREATE TABLE Materia (cod_materia VARCHAR (6) PRIMARY KEY NOT NULL, nom_materia VARCHAR (50) NOT NULL);");

                db.execSQL("CREATE TABLE [CargaAcademica] ( [materia] VARCHAR(6) NOT NULL, [docente] VARCHAR(7) NOT NULL, [ciclo] INTEGER NOT NULL, [cargo] INTEGER NOT NULL, PRIMARY KEY ([materia],[docente],[ciclo],[cargo]) );");

                db.execSQL("CREATE TABLE [GrupoMateria] ( [id_grupo] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [tipo_grupo] VARCHAR(2) NOT NULL, [materia] VARCHAR(6) NOT NULL, [docente] VARCHAR(7) NOT NULL, [ciclo] INTEGER NOT NULL, [LOCAL] VARCHAR(10) NOT NULL, [diasImpartida] VARCHAR(50) NOT NULL, [num_grupo] VARCHAR(2) NOT NULL, [horario] INTEGER NOT NULL );");




                //INICIALIZANDO DATOS


                db.execSQL("INSERT INTO Cargo (id_cargo, nom_cargo) VALUES (1, 'Coordinador de Discusiones');");
                db.execSQL("INSERT INTO Recurso (id_recurso, nom_recurso, detalle_recurso, estado, cat_recurso) VALUES (1, 'Ca�on HP 456TZ', 'Ca�on Blanco Bien Cuidadito', 1, 1);");
                db.execSQL("INSERT INTO TipoGrupo (cod_tipo_grupo, tipo_grupo) VALUES ('GT', 'Grupo Te�rico');");
                db.execSQL("INSERT INTO TipoGrupo (cod_tipo_grupo, tipo_grupo) VALUES ('GD', 'Grupo de Discusi�n');");
                db.execSQL("INSERT INTO TipoGrupo (cod_tipo_grupo, tipo_grupo) VALUES ('GL', 'Grupo de Laboratorio');");
                db.execSQL("INSERT INTO Docente (cod_docente, nom_docente) VALUES ('MM13014', 'Kevin Mejia');");
                db.execSQL("INSERT INTO Ciclo (id_ciclo, anio_ciclo, ciclo_num) VALUES (1, '2016', 'I');");
                db.execSQL("INSERT INTO CategoriaRecurso (id_categoria_recurso, categoria_recurso) VALUES (1, 'Ca�on');");
                db.execSQL("INSERT INTO ReservaActividad (recurso, actividad) VALUES (1, 1);");
                db.execSQL("INSERT INTO Horario (id_horario, hora_ini, hora_fin) VALUES (1, '06:20:00', '08:00:00');");
                db.execSQL("INSERT INTO Reservacion (id_reservacion, recurso, grupo) VALUES (1, 1, 1);");
                db.execSQL("INSERT INTO Actividad (id_actividad, nom_actividad, detalle_actividad, fecha, hora_ini, hora_fin, docente) VALUES (1, 'Algo', 'Hicieron algo pero no se que', '23/03/2016', '8:00:00', '10:00:00', 'MM13014');");
                db.execSQL("INSERT INTO Materia (cod_materia, nom_materia) VALUES ('MAT115', 'Matematica 1');");
                db.execSQL("INSERT INTO CargaAcademica (materia, docente, ciclo, cargo) VALUES ('MAT115', 'MM13014', 1, 1);");
                db.execSQL("INSERT INTO GrupoMateria (id_grupo, tipo_grupo, materia, docente, ciclo, LOCAL, diasImpartida, num_grupo, horario) VALUES (1, 'GT', 'MAT115', 'MM13014', 1, 'B31', 'Lunes, Martes', '02', 1);");




            }catch(SQLException e){

                e.printStackTrace();
            }
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            // TODO Auto-generated method stub

        }


    }

    public void abrir() throws SQLException{
        db = DBHelper.getWritableDatabase();
        return;
    }

    public void cerrar(){
        DBHelper.close();
    }

    public String insertar(Ciclo ciclo) {
        String regInsertados = "Registro Insertado No = ";
        long contador = 0;
        ContentValues cicl = new ContentValues();

        cicl.put("idCiclo", ciclo.getId_ciclo());
        cicl.put("anioCiclo", ciclo.getAnio_ciclo());
        cicl.put("CicloNum", ciclo.getCiclo_num());

        contador = db.insert("alumno", null, cicl);

        if (contador == -1 || contador == 0) {
            regInsertados = "Error al Insertar el registro, Registro Duplicado. Verificar inserción";
        } else {
            regInsertados = regInsertados + contador;
        }

        return regInsertados;
    }

    public String actualizar(Ciclo ciclo) {
        return null;
    }

    public String eliminar(Ciclo ciclo) {
        return null;
    }

    public Ciclo consultarCiclo(String idCiclo) {
        String[] id = {idCiclo};
        Cursor cursor = db.query("ciclo", camposCiclo, "id_ciclo = ?", id, null, null, null);

        if (cursor.moveToFirst()) {
            Ciclo ciclo = new Ciclo();
            //ciclo.setId_ciclo(cursor.getString(0));
            ciclo.setAnio_ciclo(cursor.getString(1));
            ciclo.setCiclo_num(cursor.getString(2));
            return ciclo;
        } else {

            return null;
        }
    }







}
