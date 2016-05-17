package proyecto.pdm;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.CRUDTablas.ReservaActividadBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.Actividad;
import proyecto.pdm.ClasesModelo.Recurso;
import proyecto.pdm.ClasesModelo.ReservaActividad;

public class ReservaActividadEliminarActivity extends Activity {

    private Spinner SpinRecursos;
    private ReservaActividadBD helper;
    private RecursoDB recursoBD;
    private Spinner SpinActividad;
    private ActividadBD actividadBD;
    private UsuarioBD credencialesUsuario;

    private HashMap<String, Integer> spinnerMapRecurso = new HashMap<String, Integer>();
    private HashMap<String, Integer> spinnerMapActividad = new HashMap<String, Integer>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_reserva_actividad_eliminar);




        helper= new ReservaActividadBD(this);
        recursoBD=new RecursoDB(this);
        actividadBD=new ActividadBD(this);
        credencialesUsuario = new UsuarioBD(this);


        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de ReservaActividad", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


        SpinRecursos=(Spinner)findViewById(R.id.spinRecurso);

        List<Recurso> recursoList = recursoBD.getRecursos();
        String[] spinnerResource = new String[recursoList.size()];
        int i = 0;
        for (Recurso r: recursoList){
            spinnerMapRecurso.put(r.getNomRecurso(), r.getIdRecurso());
            spinnerResource[i]=r.getNomRecurso();
            i++;
        }


        ArrayAdapter <String> adapter01 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinRecursos.setAdapter(adapter01);





        SpinActividad=(Spinner)findViewById(R.id.spinActividad);

        List<Actividad> actividadList = actividadBD.getActividades();
        String[] spinnerResource2 = new String[actividadList.size()];
        int j = 0;
        for (Actividad a: actividadList){
            spinnerMapActividad.put(a.getNomActividad(), a.getIdActividad());
            spinnerResource2[j]=a.getNomActividad();
            j++;
        }


        ArrayAdapter <String> adapter02 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource2);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);

        SpinActividad.setAdapter(adapter02);


    }

    public void eliminarReservaActividad(View v){


        String act =SpinActividad.getSelectedItem().toString();
        String rec= SpinRecursos.getSelectedItem().toString();


        String regInsertados = "";

        ReservaActividad reservaActividad = new ReservaActividad();

        reservaActividad.setActividad(spinnerMapActividad.get(act));
        reservaActividad.setRecurso(spinnerMapRecurso.get(rec));

        regInsertados = helper.eliminar(reservaActividad);
        Toast.makeText(this,regInsertados,Toast.LENGTH_SHORT).show();

    }




}
