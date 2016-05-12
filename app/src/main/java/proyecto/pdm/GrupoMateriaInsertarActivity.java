package proyecto.pdm;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;
import android.view.View;

import java.util.HashMap;
import java.util.List;

import proyecto.pdm.CRUDTablas.CicloBD;
import proyecto.pdm.CRUDTablas.DocenteBD;
import proyecto.pdm.CRUDTablas.GrupoMateriaBD;

import proyecto.pdm.CRUDTablas.MateriaBD;
import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.Ciclo;
import proyecto.pdm.ClasesModelo.Docente;
import proyecto.pdm.ClasesModelo.GrupoMateria;
import proyecto.pdm.ClasesModelo.Horario;
import proyecto.pdm.ClasesModelo.Materia;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class GrupoMateriaInsertarActivity extends Activity {
    GrupoMateriaBD helper;
    private Spinner SpinDocente1;
    private Spinner SpinHorario;
    private Spinner SpinCiclo1;
    private Spinner SpinMateria1;
    private Spinner SpinTipoGrupo;
    private HashMap<String, String> spinnerMapDocente = new HashMap<String, String>();
    private HashMap<String, String> spinnerMapMateria = new HashMap<String, String>();
    private HashMap<String, Integer> spinnerMapHorario = new HashMap<String, Integer>();
    private HashMap<String, Integer> spinnerMapCiclo= new HashMap<String,Integer>();
    private HashMap<String,String> spinnerMapTipoGrupo = new HashMap<String, String>();
    EditText editTipoGrupo;
    EditText editIdGrupo;
    EditText editMateria;
    EditText editDocente;
    EditText editCiclo;
    EditText editLocal;
    EditText editDiasImpartida;
    EditText editHorario;
    EditText editNumGrupo;
    private DocenteBD docenteBD;
    private CicloBD cicloBD;
   // private HorarioBD horarioBD;
    private MateriaBD materiaBD;
    private TipoGrupoBD tipoGrupoBD;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_grupo_materia_insertar);
        helper = new GrupoMateriaBD(this);
        docenteBD =new DocenteBD(this);
        cicloBD=new CicloBD(this);
        materiaBD=new MateriaBD(this);
        tipoGrupoBD=new TipoGrupoBD(this);
        List<Docente> docenteList = docenteBD.getDocentes();
        String[] spinnerResource = new String[docenteList.size()];
        int i = 0;
        for (Docente d: docenteList){
            spinnerMapDocente.put(d.getNomDocente(), d.getCodDocente());
            spinnerResource[i]=d.getNomDocente();
            i++;
        }
        SpinDocente1=(Spinner)findViewById(R.id.spinnerDocente1);
        ArrayAdapter<String> adapter01 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerResource);
        adapter01.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinDocente1.setAdapter(adapter01);

        List<Materia>materiaList = materiaBD.getMaterias();
        String[] spinnerRosurce02=new String[materiaList.size()];
        int j = 0;
        for (Materia m: materiaList){
            spinnerMapMateria.put(m.getNomMateria(), m.getCodMateria());
            spinnerRosurce02[j]=m.getNomMateria();
            j++;
        }
        SpinMateria1=(Spinner)findViewById(R.id.spinnerMateria1);
        ArrayAdapter<String> adapter02 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item, spinnerRosurce02);
        adapter02.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinMateria1.setAdapter(adapter02);


        List<Ciclo>cicloList = cicloBD.getCiclos();
        Integer[] spinnerRosurse03= new Integer[cicloList.size()];
        int k = 0;
        for (Ciclo c : cicloList){
            spinnerMapCiclo.put(c.getCiclo_num(),c.getId_ciclo());
            spinnerRosurse03[k]=c.getId_ciclo();
            k++;
        }
        SpinCiclo1=(Spinner)findViewById(R.id.spinnerCiclo1);
        ArrayAdapter<Integer> adapter03 =new ArrayAdapter<Integer>(this, android.R.layout.simple_spinner_item,  spinnerRosurse03);
        adapter03.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinCiclo1.setAdapter(adapter03);

        List<TipoGrupo>tipoGrupoListList = tipoGrupoBD.getTipoGrupos();
        String[] spinnerRosurse04= new String[cicloList.size()];
        int z = 0;
        for (TipoGrupo o : tipoGrupoListList){
            spinnerMapTipoGrupo.put(o.getTipoGrupo(), o.getTipoGrupo());
            spinnerRosurse04[z]=o.getcodTipoGrupo();
            z++;
        }
        SpinTipoGrupo=(Spinner)findViewById(R.id.spinnerTipoGrupo);
        ArrayAdapter<String> adapter04 =new ArrayAdapter<String>(this, android.R.layout.simple_spinner_item,  spinnerRosurse04);
        adapter04.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        SpinTipoGrupo.setAdapter(adapter04);


        editIdGrupo = (EditText) findViewById(R.id.editIdGrupo);
        editLocal = (EditText) findViewById(R.id.editLocal);
        editDiasImpartida = (EditText) findViewById(R.id.editDiasImpartida);
        editNumGrupo= (EditText) findViewById(R.id.editNumGrupo);


    }

    public void insertarGrupoMateria(View v){
        String idGrupo = editIdGrupo.getText().toString();
        String tipoGrupo = SpinTipoGrupo.getSelectedItem().toString();
        String materia = SpinMateria1.getSelectedItem().toString();
        String docente = SpinDocente1.getSelectedItem().toString();
        String ciclo = SpinCiclo1.getSelectedItem().toString();
        String local = editLocal.getText().toString();
        String diasImpartida = editDiasImpartida.getText().toString();
        String horario = SpinHorario.getSelectedItem().toString();
        String numGrupo = editNumGrupo.getText().toString();
        String regInsertados;

        GrupoMateria grupoMateria = new GrupoMateria();
        grupoMateria.setIdGrupo(Integer.parseInt(idGrupo));
        grupoMateria.setTipoGrupo(spinnerMapTipoGrupo.get(tipoGrupo));
        grupoMateria.setMateria(spinnerMapMateria.get(materia));
        grupoMateria.setDocente(spinnerMapDocente.get(docente));
        grupoMateria.setCiclo(spinnerMapCiclo.get(ciclo));
        grupoMateria.setLocal(local);
        grupoMateria.setDiasImpartida(diasImpartida);
        grupoMateria.setHorario(spinnerMapHorario.get(horario));
        grupoMateria.setNumGrupo(numGrupo);

        regInsertados=helper.insertar(grupoMateria);
        Toast.makeText(this, regInsertados, Toast.LENGTH_SHORT).show();
    }


    public void limpiarTexto(View v){
        editTipoGrupo.setText("");
        editIdGrupo.setText("");
        editMateria.setText("");
        editDocente.setText("");
        editCiclo.setText("");
        editLocal.setText("");
        editDiasImpartida.setText("");
        editHorario.setText("");
        editNumGrupo.setText("");
    }

}
