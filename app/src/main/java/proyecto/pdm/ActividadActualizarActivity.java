package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import java.sql.Date;
import java.sql.Time;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.ClasesModelo.Actividad;

public class ActividadActualizarActivity extends AppCompatActivity {

    ActividadBD helper;
    EditText ActividadID;

    EditText editID;
    EditText editNombre;
    EditText editDetalle;
    EditText editFecha;
    EditText editHoraI;
    EditText editHoraF;
    EditText editDocente;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_actividad_consultar);

        helper=new ActividadBD(this);
        editID = (EditText) findViewById(R.id.editActividad);
        editNombre = (EditText) findViewById(R.id.editNombreActividad);
        editDetalle = (EditText) findViewById(R.id.editDetalle);
        editFecha=(EditText) findViewById(R.id.editFecha);
        editHoraI = (EditText) findViewById(R.id.editHoraI);
        editHoraF = (EditText) findViewById(R.id.editHoraF);
        editDocente = (EditText) findViewById(R.id.editDocente);


    }

    public void ConsultarActividad(View v){

        Actividad actividad = helper.consultar(editID.getText().toString());

        if (actividad == null){
            Toast.makeText(this, "Actividad " + editID.getText().toString() + "no encontrada",
                    Toast.LENGTH_LONG).show();
        }
        else {

            editFecha.setText(actividad.getFecha().toString());
            editDetalle.setText(actividad.getDetalleActividad());
            editDocente.setText(actividad.getDocente());
            editHoraF.setText(actividad.getHoraFin().toString());
            editHoraI.setText(actividad.getHoraIni().toString());
            editNombre.setText(actividad.getNomActividad());

        }
    }


/*
    public void actualizarActividad(View v){

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


*/






}
