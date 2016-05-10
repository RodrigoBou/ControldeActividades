package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.widget.ToggleButton;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ClasesModelo.Materia;

public class CargaAcademicaInsertarActivity extends Activity {
    private Spinner SpinDocente;
    private Spinner SpinCargo;
    private Spinner SpinCiclo;
    private Spinner SpinMateria;
    private CargaAcademicaBD helper;
    private DocenteBD docenteBD;
    private CicloBD cicloBD;
    private CargoDB cargoBD;
    private MateriaBD materiaBD;
    private HashMap<String, Integer> spinnerMap = new HashMap<String, Integer>();
    @Override
    protected void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_insertar);
        helper=new CargaAcademicaBD(this);
        docenteBD =new DocenteBD(this);
        cargoBD=new CargoDB(this);
        cicloBD=new CicloBD(this);
        materiaBD=new MateriaBD(this);
        List<Docente> docenteList = docenteBD.getDocentes();
        String[] spinnerResource = new String[docenteList.size()];
        int i = 0;
        for (Docente d: docenteList){
            spinnerMap.put(d.getNomDocente(), i);
            spinnerResource[i]=d.getNomDocente();
            i++;
        }
        List<Materia>materiaList = materiaBD.getMaterias();
        String[] spinnerRosurce02=new String[materiaList.size()];
        int j = 0;
        for (Materia m: materiaList){
            spinnerMap.put(m.getNomMateria(), j);
            spinnerRosurce02[j]=m.getNomMateria();
            j++;
        }
        SpinDocente=(Spinner)findViewById(R.id.spinnerDocente);
        SpinMateria=(Spinner)findViewById(R.id.spinnerMateria);
        ArrayAdapter <String> adapter01 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinDocente.setAdapter(adapter01);
        ArrayAdapter<String> adapter02 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerRosurce02);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinMateria.setAdapter(adapter02);
        
    }



    public void limpiarTexto(View v){

    }

}
