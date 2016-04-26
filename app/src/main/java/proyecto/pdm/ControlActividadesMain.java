package proyecto.pdm;

import android.app.ListActivity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

public class ControlActividadesMain extends ListActivity  {

    String[] menu={"Reserva","Materia","Docente","Recurso","Carga academica","Cargo","Actividad",
            "Ciclo", "Horario","Tipo de grupo","Grupo-Materia","Categoria de recursos","Reserva para actividades"};


    String[] activities={"ReservaMenuActivity","MateriaMenuActivity", "DocenteMenuActivity",
            "RecursoMenuActivity","CargaAcademicaMenuActivity","CargoMenuActivity","ActividadMenuActivity","CicloMenuActivity",
            "HorarioMenuActivity","TipoGrupoMenuActivity","GrupoMateriaMenuActivity",
            "CategoriaRecursoMenuActivity","ReservaActividadMenuActivity",};

    ControlBD BDHelper;




    @Override

    public void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);

    setListAdapter(new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, menu));

        BDHelper=new ControlBD(this);

        BDHelper.abrir();
        //String tost=BDHelper.llenarBDCarnet();
        BDHelper.cerrar();
       // Toast.makeText(this, tost, Toast.LENGTH_SHORT).show();

    }



   @Override

    protected void onListItemClick(ListView l,View v,int position,long id){

        super.onListItemClick(l, v, position, id);




            String nombreValue=activities[position];

            try{

                Class<?>
                        clase=Class.forName("proyecto.pdm."+nombreValue);

                Intent inte = new Intent(this,clase);

                this.startActivity(inte);

            }catch(ClassNotFoundException e){

                e.printStackTrace();

            }



    }
}
