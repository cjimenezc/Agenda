package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

import android.app.TimePickerDialog;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TimePicker;

import java.util.Calendar;
import java.util.Date;


public class Ingresar extends ActionBarActivity {

    private Button btCancela,btnGuardar;
    private EditText txtTitulo,txtDescripcion,txtHora, txtlugar;

    private BaseDatos dataBase ;
    private SQLiteDatabase conn;
    private MetodosBaseDa metodoBD;
    private Tarea tarea;
    private Date data;
    private int horas, minutos;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ingresar);

        txtTitulo = (EditText) findViewById(R.id.txtTitu);
        txtDescripcion = (EditText) findViewById(R.id.txtDescr);
        txtHora = (EditText) findViewById(R.id.txtHora);
        txtlugar = (EditText) findViewById(R.id.txtLugar);
        btCancela = (Button) findViewById(R.id.btnCancela);
        btnGuardar = (Button) findViewById(R.id.btnGuardar);

        //Establecer las acciones de los botones
        btCancela.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();
            }
        });

        /*
        * realiza la accion del evento clik para guardar los datos
        */

        btnGuardar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (tarea.getId() == 0) {
                    salvar();
                    Intent result = new Intent();
                    result.putExtra("TAREA", tarea.getTitulo());
                    setResult(RESULT_OK, result);
                }
                finish();
            }
        });

        //acoes para exibicao da data
        TimeListener hora = new TimeListener();

        txtHora.setOnClickListener(hora);
        txtHora.setOnFocusChangeListener(hora);

        Bundle bundle;
        bundle = getIntent().getExtras();

        //verifica los datos
        if ((bundle != null) && (bundle.containsKey("Tareas"))) {
            tarea = (Tarea)bundle.getSerializable("Tareas");
            Obtiene();
        } else {
            tarea = new Tarea();
        }
            dataBase = new BaseDatos(this);
            conn = dataBase.getWritableDatabase();
            metodoBD = new MetodosBaseDa(conn);
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

    /*
    obtine los datos
     */
    private void Obtiene(){
        txtTitulo.setText(tarea.getTitulo());
        txtDescripcion.setText(tarea.getDes());
        txtHora.setText(tarea.getHora());
        txtlugar.setText(tarea.getLugar());
    }

    /*
    Metodo que obtine los datos para ser guardados en la base de datos
    */
    private void salvar() {
            tarea.setTitulo(txtTitulo.getText().toString());
            tarea.setDes(txtDescripcion.getText().toString());
            tarea.setHora(txtHora.getText().toString());
            tarea.setLugar(txtlugar.getText().toString());
            metodoBD.Insertar(tarea);
    }
    /*
     *se encarga de lanzar el reloj para que el usuario selecione la hora corespondiente
     */
    private class TimeListener implements View.OnClickListener, View.OnFocusChangeListener {
        @Override
        public void onClick(View v) {
            Time();
        }
        @Override
        public void onFocusChange(View v, boolean hasFocus) {
            if (hasFocus)
                Time();
        }

    }

    /*
     metodo que realiza la accion de que el usuario seleccione la hora y
     luego la muestre en un EditText
     */
    public void Time(){
        // Proceso para obtener la hora
        final Calendar c = Calendar.getInstance();
        horas = c.get(Calendar.HOUR);
        minutos = c.get(Calendar.MINUTE);
        TimePickerDialog tpd = new TimePickerDialog(this,
                new TimePickerDialog.OnTimeSetListener() {

                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay,
                                          int minute) {
                        // muestra la hora en el EditText
                        txtHora.setText(hourOfDay + ":" + minute);
                    }
                }, horas, minutos, false);
        tpd.show();
    }

}
