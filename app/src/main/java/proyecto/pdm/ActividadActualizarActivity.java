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
        setContentView(R.layout.activity_actividad_actualizar_actividad);

        helper = new ActividadBD(this);
        editID = (EditText) findViewById(R.id.editActividad);
        editNombre = (EditText) findViewById(R.id.editNombreActividad);
        editDetalle = (EditText) findViewById(R.id.editDetalle);

        editHoraI = (EditText) findViewById(R.id.editHoraI);
        editHoraF = (EditText) findViewById(R.id.editHoraF);
        editDocente = (EditText) findViewById(R.id.editDocente);
        editMinI = (EditText) findViewById(R.id.editMinI);
        editMinF = (EditText) findViewById(R.id.editMinF);

        editDia = (EditText) findViewById(R.id.editDia);
        editMes = (EditText) findViewById(R.id.editMes);
        editAnio = (EditText) findViewById(R.id.editAnio);

    }


    public void ConsultarActividad(View v) {
        Actividad actividad = helper.consultar(editID.getText().toString());
        if (actividad == null) {
            Toast.makeText(this, "Actividad " + editID.getText().toString() + "no encontrada",
                    Toast.LENGTH_LONG).show();
        } else {


            String fecha = actividad.getFecha().toString();

            String dia = fecha.substring(8, 10);
            String mes = fecha.substring(5, 7);
            String anio = fecha.substring(0, 4);


            String horaI = actividad.getHoraIni().toString();

            String hi = horaI.substring(0, 2);
            String mi = horaI.substring(3, 5);


            String horaF = actividad.getHoraFin().toString();

            String hf = horaF.substring(0, 2);
            String mf = horaF.substring(3, 5);


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

            editID.setEnabled(false);

        }
    }



    public void reiniciarConsulta (View v){

        editID.setEnabled(true);
        editID.setText("");
        editNombre.setText("");
        editDetalle.setText("");
        editDocente.setText("");
        editHoraF.setText("");
        editHoraI.setText("");
        editMinF.setText("");
        editMinI.setText("");


    }


    public void actualizarActividad(View v){

        String regInsertados = "";

        Actividad actividad = new Actividad();

        actividad.setIdActividad(Integer.parseInt(editID.getText().toString()));
        actividad.setDetalleActividad(editDetalle.getText().toString());
        actividad.setNomActividad(editNombre.getText().toString());
        actividad.setHoraFin(Time.valueOf((editHoraF.getText().toString()) + ":" + (editMinI.getText().toString())+ ":00"));
        actividad.setHoraIni(Time.valueOf((editHoraI.getText().toString()) + ":" +(editMinF.getText().toString()) + ":00"));
        actividad.setDocente(editDocente.getText().toString());
        actividad.setFecha(Date.valueOf(editAnio.getText() + "-" + editMes.getText() + "-" + editDia.getText()));
        regInsertados = helper.actualizar(actividad);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }
}









