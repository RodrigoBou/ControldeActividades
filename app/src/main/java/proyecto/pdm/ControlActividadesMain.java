package proyecto.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import java.sql.Time;
import java.util.Date;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.CRUDTablas.GrupoMateriaBD;
import proyecto.pdm.CRUDTablas.HorarioBD;
import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.CRUDTablas.ReservaActividadBD;
import proyecto.pdm.CRUDTablas.ReservaBD;
import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.Actividad;
import proyecto.pdm.ClasesModelo.CargaAcademica;
import proyecto.pdm.ClasesModelo.Cargo;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;
import proyecto.pdm.ClasesModelo.Ciclo;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ClasesModelo.GrupoMateria;
import proyecto.pdm.ClasesModelo.Horario;
import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.ClasesModelo.Reserva;
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
    public GrupoMateriaBD GM;
    public ReservaBD Res;
    public ActividadBD A;


    String[] menu = {"Reserva", "Materia", "Docente", "Recurso", "Carga academica", "Cargo", "Actividad",
            "Ciclo", "Horario", "Tipo de grupo", "Grupo-Materia", "Categoria de recursos", "Reserva para actividades",
    "Cerrar sesión", "Lenar BD"};


    String[] activities = {"ReservaMenuActivity", "MateriaMenuActivity", "DocenteMenuActivity",
            "RecursoMenuActivity", "CargaAcademicaMenuActivity", "CargoMenuActivity", "ActividadMenuActivity", "CicloMenuActivity",
            "HorarioMenuActivity", "TipoGrupoMenuActivity", "GrupoMateriaMenuActivity",
            "CategoriaRecursoMenuActivity", "ReservaActividadMenuActivity"};



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
        GM=new GrupoMateriaBD(this);
        Res=new ReservaBD(this);
        A=new ActividadBD(this);
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
        else if (position == l.getCount() - 2){
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = session.edit();
            editor.remove("id");
            editor.apply();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }else if (position == l.getCount() - 1){
            String msg = llenarControlDeActividades();

            Toast.makeText(ControlActividadesMain.this, msg,
                    Toast.LENGTH_LONG).show();
        }
    }
    public String llenarControlDeActividades(){
        //tipoGrupo
        final String[] VTGcod_tipo_grupo ={"GT","GD","GL"};
        final String[] VTGtipo_grupo={"Grupo Teórico","Grupo de Laboratorio","Grupo d Discusión"};
        //Ciclo
        final String[] CidCiclo={"1","2","3"};
        final String[] Canio_ciclo={"2014","2015","2016"};
        final String[] Cciclo_num={"I","II","III"};
        //Materia
        final String[] Mcod_materia={"PDM115", "PRN315", "SYP115"};
        final String[] Mnom_materia={"Programacion para moviles","Programacion III", "Sistemas y Procedimientos"};
        //Docente
        final String[] Dcod_docente={"CG001","YV001","JM001"};
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
        final String[] CAdocente={"CG001"};
        final Integer[] CAciclo={1};

        //Reserva Actividad
        final Integer[] RArecurso={1};
        final  Integer[] RAcat={1};

        //Reserva
        final Integer[] Rid_recurso={1};
        final Integer[] Rrecurso={1};
        final Integer[] RgrupoMateria={1};

        //GrupoMateria
        final Integer[] GMid={1};
        final String[] GMnum={"01"};
        final String[] GMlocal={"B1"};
        final String[] GMdias={"Lunes"};
        final String[] GMtipo={"GT"};
        final String[] GMmateria={"PDM115"};
        final String[] GMdocente={"CG001"};
        final String[] GMciclo={"1"};
        final Integer[] hiorario={1};

        //Actividad
        final Integer[] Aid={1};
        final String[] Anom={"Actividad01"};
        final String[] Adetalle={"Ninguno"};
        final Date[] Afecha={java.sql.Date.valueOf("2016-01-01")};
        final Time[] Aini={Time.valueOf("7:00:00")};
        final Time[] Afin={Time.valueOf("8:00:00")};
        final String[] Adoc={"CG001"};




        Docente docente =new Docente();
        for(int i=0;i<3;i++){
            docente.setCodDocente(Dcod_docente[i]);
            docente.setNomDocente(Dnom_docente[i]);
            D.insertar(docente);
        }

        Actividad actividad= new Actividad();
        for(int i=0;i<1;i++)
        {
            actividad.setIdActividad(Aid[i]);
            actividad.setNomActividad(Anom[i]);
            actividad.setDetalleActividad(Adetalle[i]);
            actividad.setFecha(Afecha[i]);
            actividad.setHoraIni(Aini[i]);
            actividad.setHoraFin(Afin[i]);
            actividad.setDocente(Adoc[i]);
            A.Insertar(actividad);

        }

        Horario horario=new Horario();
        for(int i=0;i<3;i++){
            horario.setId_horario(HidHorario[i]);
            horario.setHora_ini(Hhora_ini[i]);
            horario.setHora_fin(Hhora_fin[i]);
            H.insertar(horario);
        }


        CategoriaRecurso categoriaRecurso=new CategoriaRecurso();
        for(int i=0;i<3;i++){
            categoriaRecurso.setIdCatRecurso(CTid_recurso[i]);
            categoriaRecurso.setCategoriaRecurso(CTnom_recurso[i]);
            Cat.insertar(categoriaRecurso);
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


        Materia materia=new Materia();
        for (int i=0;i<3;i++){
            materia.setCodMateria(Mcod_materia[i]);
            materia.setNomMateria(Mnom_materia[i]);
            M.insertar(materia);
        }


        TipoGrupo tipoGrupo=new TipoGrupo();
        for (int i=0;i<3;i++) {
            tipoGrupo.setcodTipoGrupo(VTGcod_tipo_grupo[i]);
            tipoGrupo.setTipoGrupo(VTGtipo_grupo[i]);
            TG.insertar(tipoGrupo);
        }


        Ciclo ciclo=new Ciclo();
        for (int i=0;i<3;i++){
            ciclo.setId_ciclo(CidCiclo[i]);
            ciclo.setAnio_ciclo(Canio_ciclo[i]);
            ciclo.setCiclo_num(Cciclo_num[i]);
            C.insertar(ciclo);
        }


        GrupoMateria grupoMateria=new GrupoMateria();
        for(int i=0;i<1;i++){
            grupoMateria.setIdGrupo(GMid[i]);
            grupoMateria.setNumGrupo(GMnum[i]);
            grupoMateria.setLocal(GMlocal[i]);
            grupoMateria.setDiasImpartida(GMdias[i]);
            grupoMateria.setTipoGrupo(GMtipo[i]);
            grupoMateria.setMateria(GMmateria[i]);
            grupoMateria.setDocente(GMdocente[i]);
            grupoMateria.setCiclo(GMciclo[i]);
            grupoMateria.setHorario(hiorario[i]);
            GM.insertar(grupoMateria);

        }

        Reserva reserva=new Reserva();
        for(int i=0;i<1;i++){
            reserva.setIdReserva(Rid_recurso[i]);
            reserva.setRecurso(Rrecurso[i]);
            reserva.setGrupo(RgrupoMateria[i]);
            Res.insertar(reserva);
        }



        Cargo cargo=new Cargo();
        for(int i=0;i<3;i++){
            cargo.setIdCargo(Carid_cargo[i]);
            cargo.setNomCargo(Cnom_cargo[i]);
            Car.insertar(cargo);
        }





        CargaAcademica cargaAcademica = new CargaAcademica();
        for(int i=0;i<1;i++) {
            cargaAcademica.setCargo(CAcargo[i]);
            cargaAcademica.setMateria(CAmateria[i]);
            cargaAcademica.setDocente(CAdocente[i]);
            cargaAcademica.setCiclo(CAciclo[i]);
            CA.insertar(cargaAcademica);
        }





        ReservaActividad reservaActividad= new ReservaActividad();
        for(int i=0;i<1;i++){
            reservaActividad.setRecurso(RArecurso[i]);
            reservaActividad.setActividad(RAcat[i]);
            RA.insertar(reservaActividad);

        }






        return "Datos guardados correctamente";
    }


}
