package proyecto.pdm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.TypedValue;
import android.view.View;
import android.widget.ImageButton;
import android.widget.LinearLayout;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.TextView;
import android.widget.Toast;

import java.util.List;

import proyecto.pdm.CRUDTablas.CategoriaRecursoDB;
import proyecto.pdm.ClasesModelo.CategoriaRecurso;

public class CategoriaRecursoConsultarActivity extends AppCompatActivity implements View.OnClickListener {

    private CategoriaRecursoDB helper;
    private TableLayout tableCategoriaRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_categoria_recurso_consultar);

        tableCategoriaRecurso = (TableLayout) findViewById(R.id.tablaCatRecursos);

        helper = new CategoriaRecursoDB(this);

        crearTabla();

    }

    private void crearTabla(){

        tableCategoriaRecurso.removeAllViews();

        List<CategoriaRecurso> categoriaRecursoList = helper.getCategorias();

        int width;


        for (CategoriaRecurso cat: categoriaRecursoList){


            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                    getResources().getDisplayMetrics());
            TextView id = new TextView(this);
            id.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            id.setText(String.valueOf(cat.getIdCatRecurso()));

            width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180,
                    getResources().getDisplayMetrics());
            TextView catRecurso = new TextView(this);
            catRecurso.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            catRecurso.setText(cat.getCategoriaRecurso());

            width = (int)TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                    getResources().getDisplayMetrics());
            LinearLayout buttonsWrapper = new LinearLayout(this);
            buttonsWrapper.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            buttonsWrapper.setOrientation(LinearLayout.HORIZONTAL);

            ImageButton editar = new ImageButton(this);
            editar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            editar.setImageResource(R.drawable.files_edit_file_icon24);
            editar.setId(100+cat.getIdCatRecurso());
            editar.setOnClickListener(this);

            ImageButton eliminar = new ImageButton(this);
            eliminar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            eliminar.setImageResource(R.drawable.delete_file_icon24);
            eliminar.setId(-cat.getIdCatRecurso());
            eliminar.setOnClickListener(this);

            buttonsWrapper.addView(editar);
            buttonsWrapper.addView(eliminar);

            row.addView(id);
            row.addView(catRecurso);
            row.addView(buttonsWrapper);

            tableCategoriaRecurso.addView(row);
        }


    }

    @Override
    public void onClick(View v){

        final int idButton = v.getId();
        if(idButton >= 100){
            Intent intent = new Intent(this, CategoriaRecursoActualizarActivity.class);
            intent.putExtra("idCatRecurso", idButton - 100);
            startActivity(intent);

        }else if(idButton < 0){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("¿De verdad desea eliminar ese registro?");

            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idDialog) {

                    String mensaje = helper.eliminar(Math.abs(idButton));
                    crearTabla();
                    Toast.makeText(CategoriaRecursoConsultarActivity.this, mensaje,
                            Toast.LENGTH_LONG).show();
                }
            });

            alertDialogBuilder.setNegativeButton("No",new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idDialog) {
                   dialog.dismiss();
                }
            });

            AlertDialog alertDialog = alertDialogBuilder.create();
            alertDialog.show();
        }
    }

}
