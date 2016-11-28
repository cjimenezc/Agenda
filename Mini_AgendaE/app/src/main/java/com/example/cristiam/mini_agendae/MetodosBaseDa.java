package com.example.cristiam.mini_agendae;

/**
 * Creado por Cristiam
 */

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.widget.ArrayAdapter;


public class MetodosBaseDa {

    private SQLiteDatabase conn;
    public MetodosBaseDa(SQLiteDatabase conn){
        this.conn = conn;
    }

    /*
    Obtienen los datos
    se resiven los datos que el ussuario escribio
     */
    public void Values(Tarea tarea, ContentValues values){
        values.put("TITULO", tarea.getTitulo());
        values.put("DESCRIPCION", tarea.getDes());
        values.put("HORA", tarea.getHora());
        values.put("LUGAR", tarea.getLugar());
    }

    /*
    Guarda los datos
    recibe los datos que se van a guardar
     */
    public void Insertar(Tarea tarea){
        ContentValues values = new ContentValues();
        Values(tarea, values);
        conn.insertOrThrow("TAREA", null, values);
    }
    /*
     aplica el array adapter a las tareas
     */
    public ArrayAdapter<Tarea> BuscaTarea(Context context){
        ArrayAdapter<Tarea> adapTareas = new ArrayAdapter<Tarea>(context, android.R.layout.simple_list_item_1);

        Cursor cursor = conn.query("TAREA", null, null, null, null, null, null);

        if (cursor.getCount() > 0){
            cursor.moveToFirst();
            do {
                Tarea livro = new Tarea();
                livro.setId(cursor.getLong(0));
                livro.setTitulo(cursor.getString(1));
                livro.setDes(cursor.getString(2));
                livro.setHora(cursor.getString(3));
                livro.setLugar(cursor.getString(4));
                adapTareas.add(livro);
            } while (cursor.moveToNext());
        }
        return adapTareas;
    }

}
