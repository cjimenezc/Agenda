package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;


public class Detalles extends ActionBarActivity {

    private Button btnCerrar;
    private TextView txtTitulo, txtDescri, txtHora, txtLugar;

    private BaseDatos dataBase ;
    private SQLiteDatabase conn ;
    private Tarea tarea;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detalles);

        txtTitulo = (TextView) findViewById(R.id.txtTitulo);
        txtDescri = (TextView) findViewById(R.id.txtDescr);
        txtHora = (TextView) findViewById(R.id.txtHor);
        txtLugar = (TextView) findViewById(R.id.txtLugar);
        btnCerrar = (Button) findViewById(R.id.btnCancelar);

        /*
        cierra el activity
         */
        btnCerrar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        //recibe el id del que seleciono en el item anterior
        Bundle bundle;
        bundle = getIntent().getExtras();

        if ((bundle != null) && (bundle.containsKey("TAREA"))) {
            tarea = (Tarea)bundle.getSerializable("TAREA");
            Muetra();
        } else {
            tarea = new Tarea();
        }
            dataBase = new BaseDatos(this);
            conn = dataBase.getWritableDatabase();
        //MetodosBaseDa repositorioLivro =
                new MetodosBaseDa(conn);
    }

    /*
    Muestra los datos de la base de datos en los TextView
     */
    private void Muetra(){
        txtTitulo.setText(tarea.getTitulo());
        txtDescri.setText(tarea.getDes());
        txtHora.setText(tarea.getHora());
        txtLugar.setText(tarea.getLugar());
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_detalles, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
