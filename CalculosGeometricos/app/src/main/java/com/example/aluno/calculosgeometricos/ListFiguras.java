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

import com.example.aluno.calculosgeometricos.CalculoDataBaseHelper;
import com.example.aluno.calculosgeometricos.MenuActivity;
import com.example.aluno.calculosgeometricos.R;
import com.example.aluno.calculosgeometricos.UmCampoActivity;

public class ListFiguras extends ListActivity {
    public static final String NOMEFIGURA = "com.example.aluno.calculosgeometricos.NOMEFIGURA" ;
    public static final String NOMEFIGURAENG = "com.example.aluno.calculosgeometricos.NOMEFIGURAENG";
    private SQLiteDatabase db;
    private Cursor cursor;
    private String plano;
    private String valorNome;
    private String valorNomeEng;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        String idiomaBase = "Idioma";
        String idiomaUtilizado = getString(R.string.idioma);
        CursorAdapter listAdapter;

        super.onCreate(savedInstanceState);
        ListView listFiguras = getListView();

        plano = getIntent().getStringExtra(MenuActivity.PLANO);

        if(plano.equals("Bidimensional")){
            try{
                SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
                db = figurasDatabaseHelper.getReadableDatabase();

                cursor = db.query("figuras",//Tabela
                        new String[]{ "_id", "nome", "tipo", "nomeEng"},//Coluna
                        "tipo = ?", //where ou selection
                        new String[]{"Bidimensional"},//substitui o ? no where
                        null,null,null,null);//restante dos parametros null

                if(idiomaBase.equals(idiomaUtilizado)){
                    listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                            cursor,
                            new String[]{"nome"},
                            new int[]{android.R.id.text1},
                            0);
                }
                else{
                    listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                            cursor,
                            new String[]{"nomeEng"},
                            new int[]{android.R.id.text1},
                            0);
                }

                listFiguras.setAdapter(listAdapter);
            }catch (SQLiteException e) {
                Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
                toast.show();
            }
        }
        else if (plano.equals("Tridimensional")){
            try{
                SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
                db = figurasDatabaseHelper.getReadableDatabase();

                cursor = db.query("figuras",//Tabela
                        new String[]{ "_id", "nome", "tipo", "nomeEng"},//Coluna
                        "tipo = ?", //where ou selection
                        new String[]{"Tridimensional"},//substitui o ? no where
                        null,null,null,null);//restante dos parametros null

                if(idiomaBase.equals(idiomaUtilizado)){
                    listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                            cursor,
                            new String[]{"nome"},
                            new int[]{android.R.id.text1},
                            0);
                }
                else{
                    listAdapter = new SimpleCursorAdapter(this, android.R.layout.simple_list_item_1,
                            cursor,
                            new String[]{"nomeEng"},
                            new int[]{android.R.id.text1},
                            0);
                }

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
        int identificador = (int) id;

        try{
            SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
            db = figurasDatabaseHelper.getReadableDatabase();

            cursor = db.query("figuras",
                    new String[] {"nome", "tipo", "nomeEng"},
                    "_id = ?",
                    new String[] {Integer.toString(identificador)},
                    null, null, null);

            if(cursor.moveToFirst()){
                valorNome = cursor.getString(0);
                valorNomeEng = cursor.getString(2);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
            toast.show();
        }

        if (valorNome.equals("Quadrado")){
            Intent intent = new Intent(this, UmCampoActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if (valorNome.equals("Circulo")){
            Intent intent = new Intent(this, UmCampoActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if (valorNome.equals("Cubo")){
            Intent intent = new Intent(this, UmCampoActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if (valorNome.equals("Esfera")){
            Intent intent = new Intent(this, UmCampoActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if (valorNome.equals("Triangulo")){
            Intent intent = new Intent(this, TipoTrianguloActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if(valorNome.equals("Retangulo")){
            Intent intent = new Intent(this, DoisCamposActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if(valorNome.equals("Prisma triangular")){
            Intent intent = new Intent(this, TresCamposActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
        else if(valorNome.equals("Prisma retangular")){
            Intent intent = new Intent(this, TresCamposActivity.class);
            intent.putExtra(NOMEFIGURA, valorNome);
            intent.putExtra(NOMEFIGURAENG, valorNomeEng);
            intent.putExtra(MenuActivity.PLANO, plano);
            startActivity(intent);
        }
    }
}

