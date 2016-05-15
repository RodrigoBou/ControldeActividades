package proyecto.pdm;

import android.content.Context;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by kevin on 05-11-16.
 */
public class DatabaseHelper extends SQLiteOpenHelper {
    private static final String BASE_DATOS = "PDMv4.s3db";
    private static final int VERSION = 1;

    private static DatabaseHelper ourInstance;

    public static DatabaseHelper getInstance(Context ctx) {
        if (ourInstance == null){
            ourInstance = new DatabaseHelper(ctx.getApplicationContext());
        }
        return ourInstance;
    }

    private DatabaseHelper (Context context) {
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

            db.execSQL("CREATE TABLE [Reserva] ( [id_reservacion] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [recurso] INTEGER NOT NULL, [grupo] INTEGER NOT NULL );");

            db.execSQL("CREATE TABLE [Actividad] ( [id_actividad] INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, [nom_actividad] VARCHAR(100) NOT NULL, [detalle_actividad] VARCHAR(500) NOT NULL, [fecha] VARCHAR(9) NOT NULL, [hora_ini] VARCHAR(8) NOT NULL, [hora_fin] VARCHAR(8) NOT NULL, [docente] VARCHAR(7) NOT NULL );");

            db.execSQL("CREATE TABLE Materia (cod_materia VARCHAR (6) PRIMARY KEY NOT NULL, nom_materia VARCHAR (50) NOT NULL);");

            db.execSQL("CREATE TABLE [CargaAcademica] ( [materia] VARCHAR(50) NOT NULL, [docente] VARCHAR(50) NOT NULL, [ciclo] INTEGER (2) NOT NULL, [cargo] INTEGER (2) NOT NULL, PRIMARY KEY ([materia],[docente],[ciclo],[cargo]) );");

            db.execSQL("CREATE TABLE [GrupoMateria] ( [id_grupo] INTEGER NOT NULL PRIMARY KEY AUTOINCREMENT, [tipo_grupo] VARCHAR(2) NOT NULL, [materia] VARCHAR(6) NOT NULL, [docente] VARCHAR(7) NOT NULL, [ciclo] INTEGER NOT NULL, [LOCAL] VARCHAR(10) NOT NULL, [diasImpartida] VARCHAR(50) NOT NULL, [num_grupo] VARCHAR(2) NOT NULL, [horario] INTEGER NOT NULL );");


            // TRIGGERS

            db.execSQL("CREATE TRIGGER [InsertarActividad] BEFORE INSERT ON [Actividad] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cod_docente FROM Docente WHERE Docente.cod_docente = NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") END; END");

            db.execSQL("CREATE TRIGGER [InsertarRecurso] BEFORE INSERT ON [Recurso] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_categoria_recurso FROM CategoriaRecurso WHERE CategoriaRecurso.id_categoria_recurso = NEW.cat_recurso) IS NULL) THEN RAISE(ABORT, \"No existe esa categoria de recurso\") END; END");

            db.execSQL("CREATE TRIGGER [InsertarReservaActividad] BEFORE INSERT ON [ReservaActividad] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_actividad FROM Actividad WHERE Actividad.id_actividad = NEW.Actividad) IS NULL) THEN RAISE(ABORT, \"No existe la Actividad\") WHEN ((SELECT id_recurso FROM Recurso WHERE Recurso.id_recurso = NEW.recurso) IS NULL) THEN RAISE(ABORT, \"No existe el recurso\")END; END\n");

            db.execSQL("CREATE TRIGGER InsertarCargaAcademica BEFORE INSERT ON CargaAcademica BEGIN SELECT CASE WHEN ((SELECT cod_materia FROM Materia WHERE cod_materia = NEW.materia) IS NULL) THEN RAISE(ABORT, \"No existe la materia\") WHEN ((SELECT cod_docente FROM Docente WHERE cod_docente = NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") WHEN ((SELECT id_ciclo FROM Ciclo WHERE id_ciclo = NEW.ciclo) IS NULL) THEN RAISE(ABORT, \"No existe el ciclo\") WHEN ((SELECT id_cargo FROM CARGO WHERE id_cargo = NEW.cargo) IS NULL) THEN RAISE(ABORT, \"No existe el cargo\") END; END\n");

            db.execSQL("CREATE TRIGGER [InsertarReserva] BEFORE INSERT ON [Reserva] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT GrupoMateria.id_grupo FROM GrupoMateria WHERE GrupoMateria.id_grupo = NEW.grupo) IS NULL) THEN RAISE(ABORT, \"No existe el grupo\") WHEN ((SELECT Recurso.id_recurso FROM Recurso WHERE Recurso.id_recurso = NEW.recurso) IS NULL) THEN RAISE (ABORT, \"No existe ese recurso\") END; END\n");

            db.execSQL("CREATE TRIGGER [InsertarGrupoMateria] BEFORE INSERT ON [GrupoMateria] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cod_tipo_grupo FROM TipoGrupo WHERE TipoGrupo.cod_tipo_grupo = NEW.tipo_grupo) IS NULL) THEN RAISE (ABORT, \"No existe el tipo de grupo\" WHEN ((SELECT cod_docente FROM Docente WHERE Docente.cod_docente = NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") WHEN ((SELECT cod_materia FROM Materia WHERE Materia.cod_materia = NEW.materia) IS NULL) THEN RAISE(ABORT, \"No existe la materia\") WHEN ((SELECT id_ciclo FROM Ciclo WHERE Ciclo.id_ciclo = NEW.ciclo) IS NULL) THEN RAISE(ABORT, \"No existe el ciclo\") WHEN ((SELECT id_horario FROM Horario WHERE Horario.id_horario = NEW.horario) IS NULL) THEN RAISE(ABORT, \"No existe el horario\") END; END");

            db.execSQL("CREATE TRIGGER EliminarMateria BEFORE DELETE ON Materia FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT materia FROM GrupoMateria WHERE GrupoMateria.materia = OLD.cod_materia) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla GrupoMateria\") WHEN ((SELECT materia FROM CargaAcademica WHERE CargaAcademica.materia = OLD.cod_materia) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla CargaAcademica\" END; END");

            db.execSQL("CREATE TRIGGER EliminarHorario BEFORE DELETE ON Horario FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT horario from GrupoMateria WHERE GrupoMateria.horario = OLD.id_horario) IS NOT NULL) THEN raise (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla GrupoMateria\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarDocente BEFORE DELETE ON Docente FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT docente FROM GrupoMateria WHERE GrupoMateria.docente = OLD.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla GrupoMateria\") WHEN ((SELECT docente FROM CargaAcademica WHERE CargaAcademica.docente = OLD.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla CargaAcademica\") WHEN ((SELECT docente FROM Actividad WHERE Actividad.docente = OLD.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encunetra asociado a otro/otros registros en la tabla Actividad\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarCargo BEFORE DELETE ON Cargo FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cargo FROM CargaAcademica WHERE CargaAcademica.cargo = OLD.id_cargo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla CargaAcademica\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarCargo] BEFORE UPDATE ON [Cargo] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cargo FROM CargaAcademica WHERE CargaAcademica.cargo = OLD.id_cargo AND OLD.id_cargo != NEW.id_cargo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla CargaAcademica\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarReservacion] BEFORE UPDATE ON [Reserva] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_recurso FROM Recurso WHERE Recurso.id_recurso = NEW.recurso ) IS NULL) THEN RAISE (ABORT, \"No existe el recurso\") WHEN ((SELECT id_grupo FROM GrupoMateria WHERE GrupoMateria.id_grupo = NEW.grupo) IS NULL) THEN RAISE(ABORT, \"No existe el grupo\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarTipoGrupo BEFORE DELETE ON TipoGrupo FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT tipo_grupo from GrupoMateria WHERE GrupoMateria.tipo_grupo = OLD.cod_tipo_grupo) IS NOT NULL) THEN raise (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla GrupoMateria\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarMateria] BEFORE UPDATE ON [Materia] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT materia FROM GrupoMateria WHERE GrupoMateria.materia = OLD.cod_materia AND OLD.cod_materia != NEW.cod_materia) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla GrupoMateria\") WHEN ((SELECT materia FROM CargaAcademica WHERE CargaAcademica.materia = OLD.cod_materia AND OLD.cod_materia != NEW.cod_materia) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla CargaAcademica\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarReservaActividad] BEFORE UPDATE ON [ReservaActividad] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT id_recurso FROM Recurso WHERE Recurso.id_recurso = NEW.recurso ) IS NULL) THEN RAISE (ABORT, \"No existe el recurso\") WHEN ((SELECT id_actividad FROM Actividad WHERE Actividad.id_actividad = NEW.actividad) IS NULL) THEN RAISE(ABORT, \"No existe la actividad\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarCategoriaRecurso BEFORE DELETE ON CategoriaRecurso FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cat_recurso FROM Recurso WHERE Recurso.cat_recurso = OLD.id_categoria_recurso) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla Recurso\") END; END;");

            db.execSQL("CREATE TRIGGER ActualizarRecurso BEFORE UPDATE ON Recurso FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT recurso FROM Reserva WHERE Reserva.recurso = OLD.id_recurso AND OLD.id_recurso != NEW.id_recurso) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro ya que tiene registros relacionados con la tabla Reserva\") WHEN ((SELECT recurso FROM ReservaActividad WHERE ReservaActividad.recurso = OLD.id_recurso AND OLD.id_recurso != NEW.id_recurso) IS NOT NULL) THEN RAISE(ABORT, \"No se puede actualizar el ID del registro ya que tiene registros relacionados con la tabla ReservaActividad\") WHEN ((SELECT id_categoria_recurso FROM CategoriaRecurso WHERE CategoriaRecurso.id_categoria_recurso = NEW.cat_recurso) IS NULL) THEN RAISE(ABORT, \"No existe esa categoria de recurso\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarTipoGrupo] BEFORE UPDATE ON [TipoGrupo] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT tipo_grupo FROM GrupoMateria WHERE GrupoMateria.tipo_grupo = OLD.cod_tipo_grupo AND OLD.cod_tipo_grupo != NEW.cod_tipo_grupo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla GrupoMateria\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarCategoriaRecurso] BEFORE UPDATE ON [CategoriaRecurso] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cat_recurso FROM Recurso WHERE Recurso.cat_recurso = OLD.id_categoria_recurso AND OLD.id_categoria_recurso != NEW.id_categoria_recurso) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla Recurso\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarCiclo] BEFORE UPDATE ON [Ciclo] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ciclo FROM GrupoMateria WHERE GrupoMateria.ciclo = OLD.id_ciclo AND OLD.id_ciclo != NEW.id_ciclo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla GrupoMateria\") WHEN ((SELECT ciclo FROM CargaAcademica WHERE CargaAcademica.ciclo = OLD.id_ciclo AND OLD.id_ciclo != NEW.id_ciclo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla CargaAcademica\") END; END; ");

            db.execSQL("CREATE TRIGGER [ActualizarGrupoMateria] BEFORE UPDATE ON [GrupoMateria] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cod_tipo_grupo from TipoGrupo WHERE TipoGrupo.cod_tipo_grupo = NEW.tipo_grupo) IS NULL) THEN RAISE(ABORT, \"No existe el tipo de grupo\") WHEN ((SELECT cod_docente from Docente WHERE Docente.cod_docente = NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") WHEN ((SELECT cod_materia from Materia WHERE Materia.cod_materia = NEW.Materia) IS NULL) THEN RAISE(ABORT, \"No existe la materia\") WHEN ((SELECT id_ciclo from Ciclo WHERE Ciclo.id_ciclo = NEW.ciclo) IS NULL) THEN RAISE(ABORT, \"No existe el ciclo\") WHEN ((SELECT id_horario from Horario WHERE Horario.id_horario = NEW.horario) IS NULL) THEN RAISE(ABORT, \"No existe el horario\") WHEN ((SELECT grupo FROM Reserva WHERE Reserva.grupo = OLD.id_grupo AND NEW.id_grupo != OLD.id_grupo) IS NOT NULL) THEN RAISE(ABORT, \"No se puede actualizar el ID del registro debido a que se encuentra relacionado con otro registros de la tabla Reserva\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarCiclo BEFORE DELETE ON Ciclo FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT ciclo FROM GrupoMateria WHERE GrupoMateria.ciclo = OLD.id_ciclo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla GrupoMateria\") WHEN ((SELECT ciclo FROM CargaAcademica WHERE CargaAcademica.ciclo = OLD.id_ciclo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla CargaAcademica\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarGrupoMateria BEFORE DELETE ON GrupoMateria FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT grupo FROM Reserva WHERE Reserva.grupo = OLD.id_grupo) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla Reserva\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarActividad BEFORE DELETE ON Actividad FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT actividad FROM ReservaActividad WHERE ReservaActividad.actividad = OLD.id_actividad) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla ReservaActividad\") END; END;");

            db.execSQL("CREATE TRIGGER ActualizarDocente BEFORE UPDATE ON Docente FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT docente FROM GrupoMateria WHERE GrupoMateria.docente = OLD.cod_docente AND OLD.cod_docente != NEW.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla GrupoMateria\") WHEN ((SELECT docente FROM CargaAcademica WHERE CargaAcademica.docente = OLD.cod_docente AND OLD.cod_docente != NEW.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla CargaAcademica\") WHEN ((SELECT docente from Actividad WHERE Actividad.docente = OLD.cod_docente AND OLD.cod_docente != NEW.cod_docente) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla Actividad\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarHorario] BEFORE UPDATE ON [Horario] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT horario FROM GrupoMateria WHERE GrupoMateria.horario = OLD.id_horario AND OLD.id_horario != NEW.id_horario) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla GrupoMateria\") END; END;");

            db.execSQL("CREATE TRIGGER EliminarRecurso BEFORE DELETE ON Recurso FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT recurso FROM Reserva WHERE Reserva.recurso = OLD.id_recurso) IS NOT NULL) THEN RAISE (ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla Reserva\") WHEN ((SELECT recurso FROM ReservaActividad WHERE ReservaActividad.recurso = OLD.id_recurso) IS NOT NULL) THEN RAISE(ABORT, \"No se puede eliminar el registro porque se encuentra asociado a otro/otros registros en la tabla ReservaActividad\") END; END;");

            db.execSQL("CREATE TRIGGER ActualizarActividad BEFORE UPDATE ON Actividad FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT actividad FROM ReservaActividad WHERE ReservaActividad.actividad = OLD.id_actividad AND OLD.id_actividad != NEW.id_actividad) IS NOT NULL) THEN RAISE (ABORT, \"No se puede actualizar el ID del registro debido a que esta relacionado con otro/otros registros de la tabla ReservaActividad\") WHEN ((SELECT cod_docente FROM Docente WHERE Docente.cod_docente = NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") END; END;");

            db.execSQL("CREATE TRIGGER [ActualizarCargaAcademica] BEFORE UPDATE ON [CargaAcademica] FOR EACH ROW BEGIN SELECT CASE WHEN ((SELECT cod_materia FROM Materia WHERE cod_materia=NEW.materia) IS NULL) THEN RAISE(ABORT, \"No existe la materia\") WHEN ((SELECT cod_docente FROM Docente WHERE cod_docente=NEW.docente) IS NULL) THEN RAISE(ABORT, \"No existe el docente\") WHEN ((SELECT id_ciclo FROM Ciclo WHERE id_ciclo=NEW.ciclo) IS NULL) THEN RAISE(ABORT, \"No existe el ciclo\") WHEN ((SELECT id_cargo FROM CARGO WHERE id_cargo=NEW.cargo) IS NULL) THEN RAISE(ABORT, \"No existe el cargo\") END; END;");
        }catch(SQLException e){

            e.printStackTrace();
        }
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
        // TODO Auto-generated method stub

    }



}

