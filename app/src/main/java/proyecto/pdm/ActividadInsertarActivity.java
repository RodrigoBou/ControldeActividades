package proyecto.pdm;

import android.app.Activity;
import android.content.res.TypedArray;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.ClasesModelo.Actividad;

public class ActividadInsertarActivity extends Activity {
    EditText editID;
    EditText editNombre;
    EditText editDetalle;
    Spinner dia;
    Spinner mes;
    Spinner anio;
    EditText editHoraI;
    EditText editMinI;
    EditText editHoraF;
    EditText editMinF;
    EditText editDocente;



    String vDia;
    String vMes;
    String vAnio;

    private ActividadBD dbHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_insertar);

        dbHelper=new ActividadBD(this);

        editID = (EditText) findViewById(R.id.editActividad);
        editNombre = (EditText) findViewById(R.id.editNombreActividad);
        editDetalle = (EditText) findViewById(R.id.editDetalle);

        String[] meses = {"01","02","03","04","05","06","07","08","09","10","11","12",};


        String[] dias = {"01","02","03","04","05","06","07","08","09","10","11","12",
                "13","14","15","16","17","18","19","20","21","22","23","24","25","26","27",
                "28","29","30","31"};


        String[] anios = {"2013","2014","2015","2016",};





        dia = (Spinner) findViewById(R.id.dia);

        dia.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, dias));
        dia.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                vDia = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });

        mes = (Spinner) findViewById(R.id.mes);

        mes.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, meses));
        mes.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                vMes = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });


        anio = (Spinner) findViewById(R.id.anio);

        anio.setAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, anios));
        anio.setOnItemSelectedListener(new OnItemSelectedListener() {

            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int position, long id) {
                vAnio = (String) adapterView.getItemAtPosition(position);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
                // vacio

            }
        });


















       editHoraI = (EditText) findViewById(R.id.editHoraI);
        editHoraF = (EditText) findViewById(R.id.editHoraF);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editMinI=(EditText) findViewById(R.id.editMinI);
        editMinF=(EditText) findViewById(R.id.editMinF);





























    }


    public void insertarActividad(View v){

        String regInsertados = "";

        Actividad actividad = new Actividad();

        actividad.setIdActividad(Integer.parseInt(editID.getText().toString()));
        actividad.setDetalleActividad(editDetalle.getText().toString());
        actividad.setNomActividad(editNombre.getText().toString());
        actividad.setHoraFin(Time.valueOf((editHoraF.getText().toString()) + ":" + (editMinI.getText().toString())+ ":00"));
        actividad.setHoraIni(Time.valueOf((editHoraI.getText().toString()) + ":" +(editMinF.getText().toString()) + ":00"));
        actividad.setDocente(editDocente.getText().toString());
        actividad.setFecha(Date.valueOf(vAnio + "-" + vMes + "-" + vDia));
        regInsertados = dbHelper.Insertar(actividad);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v){


        editID.setText("");
        editNombre.setText("");
        editDetalle.setText("");
        editDocente.setText("");
        editHoraF.setText("");
        editHoraI.setText("");
        editMinF.setText("");
        editMinI.setText("");


    }
}











