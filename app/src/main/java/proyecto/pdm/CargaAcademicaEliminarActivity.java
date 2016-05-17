package proyecto.pdm;

import android.app.Activity;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.HashMap;
import java.util.List;

import proyecto.pdm.CRUDTablas.CargaAcademicaBD;
import proyecto.pdm.CRUDTablas.CargoDB;
import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.UsuarioBD;
import proyecto.pdm.ClasesModelo.CargaAcademica;
import proyecto.pdm.ClasesModelo.Cargo;
import proyecto.pdm.ClasesModelo.Ciclo;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ClasesModelo.Materia;

public class CargaAcademicaEliminarActivity extends Activity {
    private Spinner SpinDocente;
    private Spinner SpinCargo;
    private Spinner SpinCiclo;
    private Spinner SpinMateria;

    private CargaAcademicaBD helper;
    private DocenteBD docenteBD;
    private CicloBD cicloBD;
    private CargoDB cargoBD;
    private MateriaBD materiaBD;
    private UsuarioBD credencialesUsuario;

    private HashMap<String, String> spinnerMapDocente = new HashMap<String, String>();
    private HashMap<String, String> spinnerMapMateria = new HashMap<String, String>();
    private HashMap<String, String> spinnerMapCiclo = new HashMap<String, String>();
    private HashMap<String, Integer> spinnerMapCargo = new HashMap<String, Integer>();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_carga_academica_eliminar);

        helper = new CargaAcademicaBD(this);
        docenteBD = new DocenteBD(this);
        cargoBD = new CargoDB(this);
        cicloBD = new CicloBD(this);
        materiaBD = new MateriaBD(this);
        credencialesUsuario = new UsuarioBD(this);

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        int id = session.getInt("id", 0);

        if(!credencialesUsuario.validarPermiso("Eliminacion de CargaAcademica", id)){
            Toast.makeText(this, "Usted no tiene permiso para acceder a esta parte de la app",
                    Toast.LENGTH_LONG).show();

            Intent intent = new Intent(this, LoginActivity.class);
            startActivity(intent);
        }

        List<Docente> docenteList = docenteBD.getDocentes();
        String[] spinnerResource = new String[docenteList.size()];
        int i = 0;
        for (Docente d : docenteList) {
            spinnerMapDocente.put(d.getNomDocente(), d.getCodDocente());
            spinnerResource[i] = d.getNomDocente();
            i++;
        }
        List<Materia> materiaList = materiaBD.getMaterias();
        String[] spinnerRosurce02 = new String[materiaList.size()];
        int j = 0;
        for (Materia m : materiaList) {
            spinnerMapMateria.put(m.getNomMateria(), m.getCodMateria());
            spinnerRosurce02[j] = m.getNomMateria();
            j++;
        }
        List<Ciclo> cicloList = cicloBD.getCiclos();
        String[] spinnerRosurse03 = new String[cicloList.size()];
        int k = 0;
        for (Ciclo c : cicloList) {
            spinnerMapCiclo.put(c.getCiclo_num(), c.getId_ciclo());
            spinnerRosurse03[k] = c.getId_ciclo();
            k++;
        }
        List<Cargo> cargoList = cargoBD.getCargos();
        String[] spinnerRosurse04 = new String[cargoList.size()];
        int l = 0;
        for (Cargo ca : cargoList) {
            spinnerMapCargo.put(ca.getNomCargo(), ca.getIdCargo());
            spinnerRosurse04[l] = ca.getNomCargo();
            l++;
        }


        SpinDocente = (Spinner) findViewById(R.id.spinnerDocente);
        SpinMateria = (Spinner) findViewById(R.id.spinnerMateria);
        SpinCiclo = (Spinner) findViewById(R.id.spinnerCiclo);
        SpinCargo = (Spinner) findViewById(R.id.spinnerCargo);
        ArrayAdapter<String> adapter01 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinDocente.setAdapter(adapter01);
        ArrayAdapter<String> adapter02 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerRosurce02);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinMateria.setAdapter(adapter02);
        ArrayAdapter<String> adapter03 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerRosurse03);
        adapter03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCiclo.setAdapter(adapter03);
        ArrayAdapter<String> adapter04 = new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerRosurse04);
        adapter04.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCargo.setAdapter(adapter04);


    }

    public void eliminarCargaAcademica(View v) {
        String doc = SpinDocente.getSelectedItem().toString();
        String mat = SpinMateria.getSelectedItem().toString();
        String cic = SpinCiclo.getSelectedItem().toString();
        String car = SpinCargo.getSelectedItem().toString();
        String regEliminados = "";
        CargaAcademica cargaAcademica = new CargaAcademica();
        cargaAcademica.setMateria(spinnerMapMateria.get(mat));
        cargaAcademica.setDocente(spinnerMapDocente.get(doc));
        cargaAcademica.setCiclo(Integer.valueOf(cic));
        cargaAcademica.setCargo(Integer.valueOf(spinnerMapCargo.get(car)));
        regEliminados = helper.eliminar(cargaAcademica);
        Toast.makeText(this, regEliminados, Toast.LENGTH_SHORT).show();
    }
}
