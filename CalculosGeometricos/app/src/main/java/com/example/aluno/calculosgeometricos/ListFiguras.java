package com.example.aluno.calculosgeometricos;

import android.app.ListActivity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.CursorAdapter;
import android.widget.ListView;
import android.widget.SimpleCursorAdapter;
import android.widget.Toast;

public class ListFiguras extends ListActivity {
    private SQLiteDatabase db;
    private Cursor cursor;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ListView listFiguras = getListView();

        String plano = getIntent().getStringExtra(MenuActivity.PLANO);
        if(plano.equals("bidimensional")){
            try{
                SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
                db = figurasDatabaseHelper.getReadableDatabase();

                cursor = db.query("figuras",
                        new String[]{"_id", "nome"},
                        null, null, null, null,null);

                CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{"nome"},
                        new int[]{android.R.id.text1},
                        0);

                listFiguras.setAdapter(listAdapter);
            }catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else if (plano.equals("tridimensional")){
            try{
                SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
                db = figurasDatabaseHelper.getReadableDatabase();

                cursor = db.query("figuras",
                        new String[]{"_id", "nome"},
                        null, null, null, null,null);

                CursorAdapter listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                        cursor,
                        new String[]{"nome"},
                        new int[]{android.R.id.text1},
                        0);

                listFiguras.setAdapter(listAdapter);
            }catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
                toast.show();
            }
        }
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        cursor.close();
        db.close();
    }

    @Override
    public void onListItemClick(ListView listView, View itemView, int position, long id) {
        Intent i = new Intent(this, ListFigura.class);
        i.putExtra(ListFigura.EXTRA_NUMEROCURSO, (int) id);
        startActivity(i);
    }
}
