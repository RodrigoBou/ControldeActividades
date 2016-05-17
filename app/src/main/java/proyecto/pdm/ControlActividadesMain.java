package proyecto.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Toast;

import proyecto.pdm.CRUDTablas.TipoGrupoBD;
import proyecto.pdm.ClasesModelo.TipoGrupo;

public class ControlActividadesMain extends ListActivity {
    private static DatabaseHelper ourInstance;
    private SQLiteDatabase db;
    public TipoGrupoBD TG;

    String[] menu = {"Reserva", "Materia", "Docente", "Recurso", "Carga academica", "Cargo", "Actividad",
            "Ciclo", "Horario", "Tipo de grupo", "Grupo-Materia", "Categoria de recursos", "Reserva para actividades"};


    String[] activities = {"ReservaMenuActivity", "MateriaMenuActivity", "DocenteMenuActivity",
            "RecursoMenuActivity", "CargaAcademicaMenuActivity", "CargoMenuActivity", "ActividadMenuActivity", "CicloMenuActivity",
            "HorarioMenuActivity", "TipoGrupoMenuActivity", "GrupoMateriaMenuActivity",
            "CategoriaRecursoMenuActivity", "ReservaActividadMenuActivity",};



    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        TG = new TipoGrupoBD(this);
        llenarControlDeActividades();

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
    }
    public String llenarControlDeActividades(){
        //tipoGrupo
        final String[] VTGcod_tipo_grupo ={"GT","GD","GL"};
        final String[] VTGtipo_grupo={"Grupo Teórico","Grupo de Laboratorio","Grupo d Discusión"};
        TipoGrupo tipoGrupo=new TipoGrupo();
        for (int i=0;i<1;i++){
            tipoGrupo.setcodTipoGrupo(VTGcod_tipo_grupo[i]);
            tipoGrupo.setTipoGrupo(VTGtipo_grupo[i]);
            TG.insertar(tipoGrupo);
        }
        return "Guardado correctamente";
    }


}
