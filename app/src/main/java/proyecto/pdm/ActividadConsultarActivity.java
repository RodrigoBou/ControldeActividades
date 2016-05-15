package proyecto.pdm;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.ActividadBD;
import proyecto.pdm.ClasesModelo.Actividad;

public class ActividadConsultarActivity extends AppCompatActivity {

    ActividadBD helper;
    EditText ActividadID;

    EditText editID;
    EditText editNombre;
    EditText editDetalle;
    EditText editFecha;
    EditText editHoraI;
    EditText editMinI;
    EditText editHoraF;
    EditText editMinF;
    EditText editDocente;


    EditText editDia;
    EditText editMes;
    EditText editAnio;


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
        editMinI=(EditText) findViewById(R.id.editMinI);
        editMinF=(EditText) findViewById(R.id.editMinF);

        editDia=(EditText) findViewById(R.id.editDia);
        editMes= (EditText) findViewById(R.id.editMes);
        editAnio=(EditText) findViewById(R.id.editAnio);


    }

    public void ConsultarActividad(View v){
        Actividad actividad = helper.consultar(editID.getText().toString());
        if (actividad == null){
            Toast.makeText(this, "Actividad " + editID.getText().toString() + "no encontrada",
                    Toast.LENGTH_LONG).show();
        }
        else {


            String fecha=actividad.getFecha().toString();

            String dia=fecha.substring(8,10);
            String mes=fecha.substring(5,7);
            String anio=fecha.substring(0,4);





            String horaI=actividad.getHoraIni().toString();

            String hi=horaI.substring(0,2);
            String mi=horaI.substring(3,5);


            String horaF=actividad.getHoraFin().toString();

            String hf=horaF.substring(0,2);
            String mf=horaF.substring(3,5);



            editDetalle.setText(actividad.getDetalleActividad());
            editDocente.setText(actividad.getDocente());
            editDia.setText(dia);
            editMes.setText(mes);
            editAnio.setText(anio);


            editHoraF.setText(hf);
            editMinF.setText(mf);
            editHoraI.setText(hi);
            editMinI.setText(mi);
            editNombre.setText(actividad.getNomActividad());

        }
    }



    public void limpiarTexto(View v){


        editID.setText("");
        editNombre.setText("");
        editDetalle.setText("");
        editDocente.setText("");
        editHoraF.setText("");
        editHoraI.setText("");
        editFecha.setText("");


    }

}
