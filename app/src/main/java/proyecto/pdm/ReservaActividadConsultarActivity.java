package proyecto.pdm;

import android.app.Activity;
import android.app.ListActivity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.graphics.Color;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
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

public class ReservaActividadConsultarActivity extends ListActivity {

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
        setContentView(R.layout.activity_reserva_actividad_consultar);

        helper= new ReservaActividadBD(this);
        recursoBD=new RecursoDB(this);
        actividadBD=new ActividadBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int idUsuario = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Consulta de ReservaActividad", idUsuario)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }


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



    public void consultarReservaActividad(View v){



        String act =SpinActividad.getSelectedItem().toString();

        Actividad actividad=new Actividad();
        actividad.setIdActividad(spinnerMapActividad.get(act));


        String regInsertados = "";



        List<String> recs = helper.getRecursosActividad(actividad);




        ListView listView = getListView();



        ArrayAdapter<String> adapter= new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, recs);
        setListAdapter(adapter);







    }



}
