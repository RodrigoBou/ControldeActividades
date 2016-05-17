package proyecto.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.CRUDTablas.ReservaActividadBD;
import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;
import proyecto.pdm.ClasesModelo.Cargo;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ClasesModelo.Ciclo;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ClasesModelo.Horario;
import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.ClasesModelo.ReservaActividad;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class ControlActividadesMain extends ListActivity {
    private static DatabaseHelper ourInstance;
    private SQLiteDatabase db;
    public TipoGrupoBD TG;
    public CicloBD C;
    public MateriaBD M;
    public DocenteBD D;
    public HorarioBD H;
    public CargoDB Car;
    public CategoriaRecursoDB Cat;
    public RecursoDB Re;
    public CargaAcademicaBD CA;
    public ReservaActividadBD RA;


    String[] menu = {"Reserva", "Materia", "Docente", "Recurso", "Carga academica", "Cargo", "Actividad",
            "Ciclo", "Horario", "Tipo de grupo", "Grupo-Materia", "Categoria de recursos", "Reserva para actividades"};


    String[] activities = {"ReservaMenuActivity", "MateriaMenuActivity", "DocenteMenuActivity",
            "RecursoMenuActivity", "CargaAcademicaMenuActivity", "CargoMenuActivity", "ActividadMenuActivity", "CicloMenuActivity",
            "HorarioMenuActivity", "TipoGrupoMenuActivity", "GrupoMateriaMenuActivity",
            "CategoriaRecursoMenuActivity", "ReservaActividadMenuActivity",};



    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        TG = new TipoGrupoBD(this);
        C= new CicloBD(this);
        M=new MateriaBD(this);
        D=new DocenteBD(this);
        H=new HorarioBD(this);
        Car=new CargoDB(this);
        Cat=new CategoriaRecursoDB(this);
        Re=new RecursoDB(this);
        CA=new CargaAcademicaBD(this);
        RA=new ReservaActividadBD(this);

        llenarControlDeActividades();

    }


    @Override

    protected void onListItemClick(ListView l, View v, int position, long id) {

        super.onListItemClick(l, v, position, id);
        if (position != 13) {

            String nombreValue = activities[position];

            try {
                Class<?> clase = Class.forName("proyecto.pdm." + nombreValue);
                Intent inte = new Intent(this, clase);
                this.startActivity(inte);
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            }
        }
    }
    public String llenarControlDeActividades(){
        //tipoGrupo
        final String[] VTGcod_tipo_grupo ={"GT","GD","GL"};
        final String[] VTGtipo_grupo={"Grupo Teórico","Grupo de Laboratorio","Grupo d Discusión"};
        //Ciclo
        final String[] CidCiclo={"1","2","3"};
        final String[] Canio_ciclo={"2014","2015","2016"};
        final String[] Cciclo_num={"01","02","03"};
        //Materia
        final String[] Mcod_materia={"PDM115", "PRN315", "SYP115"};
        final String[] Mnom_materia={"Programacion para moviles","Programacion III", "Sistemas y Procedimientos"};
        //Docente
        final String[] Dcod_docente={"01","02","03"};
        final String[] Dnom_docente={"Cesar Gonzalez","Yessenia Vigil","Jose Martinez"};
        //Horario
        final Integer[] HidHorario={1,2,3};
        final String[] Hhora_ini={"8","9","10"};
        final String[] Hhora_fin={"10","11","12"};
        //Cargo
        final Integer[] Carid_cargo={1,2,3};
        final String[] Cnom_cargo={"Docente", "Coordinador","Instructor"};
        //CategoriaRecurso
        final Integer[] CTid_recurso={1,2,3};
        final String[] CTnom_recurso={"Equipo","Papeleria","Otros"};
        //Recurso
        final Integer[]Recursoid={1};
        final String[]Recursonom={"Laptop"};
        final String[]RecursoDetalle={"Ninguno"};
        final Integer[]RecursoEstado={1};
        final Integer[]RecursoCat={1};
        //CargaAcademica
        final Integer[] CAcargo={1};
        final String[] CAmateria={"PDM115"};
        final String[] CAdocente={"01"};
        final Integer[] CAciclo={1};

        //Reserva Actividad
        final Integer[] RArecurso={1};
        final  Integer[] RAcat={1};

        //Reserva


        ReservaActividad reservaActividad= new ReservaActividad();
        for(int i=0;i<1;i++){
            reservaActividad.setRecurso(RArecurso[i]);
            reservaActividad.setActividad(RAcat[i]);
            RA.insertar(reservaActividad);

        }


        CargaAcademica cargaAcademica = new CargaAcademica();
        for(int i=0;i<1;i++) {
            cargaAcademica.setCargo(CAcargo[i]);
            cargaAcademica.setMateria(CAmateria[i]);
            cargaAcademica.setDocente(CAdocente[i]);
            cargaAcademica.setCiclo(CAciclo[i]);
            CA.insertar(cargaAcademica);
        }



        Recurso recurso=new Recurso();
        for(int i=0;i<1;i++){
            recurso.setIdRecurso(Recursoid[i]);
            recurso.setNomRecurso(Recursonom[i]);
            recurso.setDetalleRecurso(RecursoDetalle[i]);
            recurso.setEstado(RecursoEstado[i]);
            recurso.setCatRecurso(RecursoCat[i]);
            Re.insertar(recurso);

        }


        CategoriaRecurso categoriaRecurso=new CategoriaRecurso();
        for(int i=0;i<3;i++){
            categoriaRecurso.setIdCatRecurso(CTid_recurso[i]);
            categoriaRecurso.setCategoriaRecurso(CTnom_recurso[i]);
            Cat.insertar(categoriaRecurso);
        }

        Cargo cargo=new Cargo();
        for(int i=0;i<3;i++){
            cargo.setIdCargo(Carid_cargo[i]);
            cargo.setNomCargo(Cnom_cargo[i]);
            Car.insertar(cargo);
        }

        Horario horario=new Horario();
        for(int i=0;i<3;i++){
            horario.setId_horario(HidHorario[i]);
            horario.setHora_ini(Hhora_ini[i]);
            horario.setHora_fin(Hhora_fin[i]);
            H.insertar(horario);
        }

        Docente docente =new Docente();
        for(int i=0;i<3;i++){
            docente.setCodDocente(Dcod_docente[i]);
            docente.setNomDocente(Dnom_docente[i]);
            D.insertar(docente);
        }


        Materia materia=new Materia();
        for (int i=0;i<3;i++){
            materia.setCodMateria(Mcod_materia[i]);
            materia.setNomMateria(Mnom_materia[i]);
            M.insertar(materia);
        }

        Ciclo ciclo=new Ciclo();
        for (int i=0;i<3;i++){
            ciclo.setId_ciclo(CidCiclo[i]);
            ciclo.setAnio_ciclo(Canio_ciclo[i]);
            ciclo.setCiclo_num(Cciclo_num[i]);
            C.insertar(ciclo);
        }
        TipoGrupo tipoGrupo=new TipoGrupo();
        for (int i=0;i<3;i++) {
            tipoGrupo.setcodTipoGrupo(VTGcod_tipo_grupo[i]);
            tipoGrupo.setTipoGrupo(VTGtipo_grupo[i]);
            TG.insertar(tipoGrupo);
        }
        return "Guardado correctamente";
    }


}
