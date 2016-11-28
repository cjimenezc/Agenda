package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;

public class Main extends ActionBarActivity implements View.OnClickListener, AdapterView.OnItemClickListener{


    private Button btnGuardar;
    private ListView lstTareas;
    private BaseDatos bd;
    private SQLiteDatabase conn ;
    private MetodosBaseDa metodosBaseDa;
    private ArrayAdapter<Tarea> adapTareas;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        btnGuardar = (Button)findViewById(R.id.btnIngresar);
        lstTareas = (ListView)findViewById(R.id.lsvTareas);

        btnGuardar.setOnClickListener(this);
        lstTareas.setOnItemClickListener(this);

            bd = new BaseDatos(this);
            conn = bd.getWritableDatabase();
            metodosBaseDa = new MetodosBaseDa(conn);
            adapTareas = metodosBaseDa.BuscaTarea(this);
            lstTareas.setAdapter(adapTareas);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_activity_main, menu);
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

    /*
     Llamar a la clase de ingresar
     */
    @Override
    public void onClick(View v) {

            Intent it = new Intent(this, Ingresar.class);
            startActivityForResult(it, 0);

    }

    /*
    buscar si hay tareas para mostrarlas
     */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        adapTareas = metodosBaseDa.BuscaTarea(this);
        lstTareas.setAdapter(adapTareas);
    }

    /*
     Cuando el usuario seleciona una item  selecciona el id y lo envia al activity de detalles
     */
    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Tarea tarea = adapTareas.getItem(position);
        Intent it = new Intent(this, Detalles.class);
        it.putExtra("TAREA", tarea);
        startActivityForResult(it, 1);
    }
}
