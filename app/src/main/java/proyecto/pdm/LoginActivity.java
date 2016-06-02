package proyecto.pdm;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

import proyecto.pdm.CRUDTablas.UsuarioBD;

public class LoginActivity extends AppCompatActivity {

    private EditText editNomUsuario;
    private EditText editPasswordUsuario;
    private UsuarioBD dbHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        dbHelper = new UsuarioBD(this);

        editNomUsuario = (EditText) findViewById(R.id.editNomUsuario);
        editPasswordUsuario = (EditText) findViewById(R.id.editTextPassword);

        llenarDatosUsuarios();

        SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
        if(session.getInt("id",0) > 0){
            Intent intent = new Intent(this, ControlActividadesMain.class);
            startActivity(intent);
        }

    }

    public void validarCredenciales(View v){
        if(dbHelper.validarUsuario(editNomUsuario.getText().toString(), editPasswordUsuario.getText().toString())){
            SharedPreferences session = PreferenceManager.getDefaultSharedPreferences(getApplicationContext());
            SharedPreferences.Editor editor = session.edit();
            editor.putInt("id", dbHelper.getIdUsuario(editNomUsuario.getText().toString(),
                    editPasswordUsuario.getText().toString()));
            editor.apply();
            Intent intent = new Intent(this, ControlActividadesMain.class);
            startActivity(intent);
        }else {
            AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this);
            alertDialogBuilder.setMessage("El usuario o contraseña son incorrectos");

            alertDialogBuilder.setNeutralButton("OK", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int idDialog) {
                    editNomUsuario.setText("");
                    editPasswordUsuario.setText("");
                    dialog.dismiss();
                }
            });
        }
    }

    public void llenarDatosUsuarios(){
        int idUser1, idUser2;

        String[] tablas = new String[]{"Actividad", "CargaAcademica", "Cargo", "CategoriaRecurso", "Ciclo", "Docente",
        "GrupoMateria", "Horario", "Materia", "Recurso", "ReservaActividad", "Reservacion", "TipoGrupo"};

        String [] operaciones = new String[]{"Menu de ", "Adicion de ", "Modificacion de ", "Eliminacion de ",
                "Consulta de "};

        dbHelper.ingresarUsuarios("Kevin", "kevin");
        dbHelper.ingresarUsuarios("Neko", "neko");

        idUser1 = dbHelper.getIdUsuario("Kevin", "kevin");
        idUser2 = dbHelper.getIdUsuario("Neko", "neko");

        if (idUser1 > 0 && idUser2 > 0) {

            for (int i = 0; i < 13; i++) {
                for (int j = 0; j < 5; j++) {
                    dbHelper.ingresarOpcionesCRUD(operaciones[j] + tablas[i], j);
                    dbHelper.ingresarPermisos(idUser2, operaciones[j] + tablas[i]);
                }
            }

            dbHelper.ingresarPermisos(idUser1, operaciones[0]+tablas[0]);
            dbHelper.ingresarPermisos(idUser1, operaciones[1]+tablas[0]);
            dbHelper.ingresarPermisos(idUser1, operaciones[0]+tablas[9]);
            dbHelper.ingresarPermisos(idUser1, operaciones[2]+tablas[9]);
            dbHelper.ingresarPermisos(idUser1, operaciones[3]+tablas[9]);
            dbHelper.ingresarPermisos(idUser1, operaciones[4]+tablas[9]);
            dbHelper.ingresarPermisos(idUser1, operaciones[0]+tablas[5]);
            dbHelper.ingresarPermisos(idUser1, operaciones[2]+tablas[5]);
            dbHelper.ingresarPermisos(idUser1, operaciones[4]+tablas[5]);


        }
    }
}
