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

    DatabaseHelper dbHelper;


    @Override

    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));
        dbHelper = DatabaseHelper.getInstance(this);
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
        } else {
            dbHelper.getWritableDatabase();
          // String tost = llenarControlDeActividades();
            dbHelper.close();
            //Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();
        }
    }
    public String llenarControlDeActividades(){
        //tipoGrupo
        final String[] VTGcod_tipo_grupo ={"01","02","03"};
        final String[] VTGtipo_grupo={"GT","GL","GD"};
        ourInstance.getWritableDatabase();
        db.execSQL("DELETE FROM TipoGrupo");
        TipoGrupo tipoGrupo=new TipoGrupo();
        for (int i=0;i<1;i++){
            tipoGrupo.setcodTipoGrupo(VTGcod_tipo_grupo[i]);
            tipoGrupo.setTipoGrupo(VTGtipo_grupo[i]);
            TG.insertar(tipoGrupo);
        }
        ourInstance.close();
        return "Guardado correctamente";
    }


}
