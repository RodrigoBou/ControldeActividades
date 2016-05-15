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
import android.widget.ToggleButton;

import java.util.List;

import proyecto.pdm.CRUDTablas.RecursoDB;
import proyecto.pdm.ClasesModelo.Recurso;

public class RecursoConsultarActivity extends AppCompatActivity implements View.OnClickListener{

    private RecursoDB dbHelper;
    private TableLayout tableRecurso;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recurso_consultar);

        tableRecurso = (TableLayout) findViewById(R.id.tableRecurso);

        dbHelper = new RecursoDB(this);

        crearTabla();
    }

    private void crearTabla() {

        tableRecurso.removeAllViews();

        List<Recurso> recursoList = dbHelper.getRecursos();

        int width;


        for (Recurso recurso : recursoList) {


            TableRow row = new TableRow(this);
            row.setLayoutParams(new TableLayout.LayoutParams(TableLayout.LayoutParams.MATCH_PARENT,
                    TableLayout.LayoutParams.WRAP_CONTENT));

            width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 40,
                    getResources().getDisplayMetrics());
            TextView id = new TextView(this);
            id.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            id.setText(String.valueOf(recurso.getIdRecurso()));

            width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 180,
                    getResources().getDisplayMetrics());
            TextView nomCargo = new TextView(this);
            nomCargo.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            nomCargo.setText(recurso.getNomRecurso());
            nomCargo.setOnClickListener(this);
            nomCargo.setTag(recurso.getIdRecurso());

            width = (int) TypedValue.applyDimension(TypedValue.COMPLEX_UNIT_DIP, 100,
                    getResources().getDisplayMetrics());
            LinearLayout buttonsWrapper = new LinearLayout(this);
            buttonsWrapper.setLayoutParams(new TableRow.LayoutParams(width, TableRow.LayoutParams.WRAP_CONTENT));
            buttonsWrapper.setOrientation(LinearLayout.HORIZONTAL);

            ImageButton editar = new ImageButton(this);
            editar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            editar.setImageResource(R.drawable.files_edit_file_icon24);
            editar.setId(100 + recurso.getIdRecurso());
            editar.setOnClickListener(this);

            ImageButton eliminar = new ImageButton(this);
            eliminar.setLayoutParams(new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT,
                    LinearLayout.LayoutParams.WRAP_CONTENT));
            eliminar.setImageResource(R.drawable.delete_file_icon24);
            eliminar.setId(-recurso.getIdRecurso());
            eliminar.setOnClickListener(this);

            buttonsWrapper.addView(editar);
            buttonsWrapper.addView(eliminar);

            row.addView(id);
            row.addView(nomCargo);
            row.addView(buttonsWrapper);

            tableRecurso.addView(row);
        }
    }

    @Override
    public void onClick(View v){
        final int idButton = v.getId();

        if (v.getTag() != null){
            Intent intent = new Intent(this, InformacionRecursoActivity.class);
            intent.putExtra("idRecurso", (int) v.getTag());
            startActivity(intent);
        }
        else if(idButton >= 100){
            Intent intent = new Intent(this, RecursoActualizarActivity.class);
            intent.putExtra("idRecurso", idButton - 100);
            startActivity(intent);

        }else if(idButton < 0){
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("¿De verdad desea eliminar ese registro?");

            alertDialogBuilder.setPositiveButton("Si", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idDialog) {

                    String mensaje = dbHelper.eliminar(Math.abs(idButton));
                    crearTabla();
                    Toast.makeText(RecursoConsultarActivity.this, mensaje,
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
