package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class HistoricoActivity extends Activity {
    private Cursor cursor;
    private Cursor cursor2;
    private String valorVolume;
    private String valorPerimetro;
    private int idFigura;

    public static final String EXTRA_NUMEROFIGURA = "numerofigura";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_historico);

        String idiomaBase = "Idioma";
        String idiomaUtilizado = getString(R.string.idioma);

        int numeroFigura = (Integer) getIntent().getExtras().get(EXTRA_NUMEROFIGURA);

        try{
            SQLiteOpenHelper figurasDatabaseHelper = new CalculoDataBaseHelper(this);
            SQLiteDatabase db = figurasDatabaseHelper.getReadableDatabase();

            cursor = db.query("historicos",
                    new String[]{"nome", "area", "volume", "perimetro", "idFigura"},
                    "_id = ?",
                    new String[] {Integer.toString(numeroFigura)},
                    null, null, null, null);

            if (cursor.moveToFirst()) {
                String valorNome = cursor.getString(0);
                String valorArea = cursor.getString(1);
                valorVolume = cursor.getString(2);
                valorPerimetro = cursor.getString(3);
                String idFig = cursor.getString(4);
                idFigura = Integer.parseInt(idFig);

                TextView textViewNomeOperacao = findViewById(R.id.textViewHistoricoNomeOperacao);
                TextView textViewResultadoArea = findViewById(R.id.textViewHistoricoResultadoArea);

                textViewNomeOperacao.setText(valorNome);
                textViewResultadoArea.setText(valorArea);

            }

            cursor2 = db.query("figuras",//Tabela
                    new String[]{"nome", "tipo", "nomeEng"},//Coluna
                    "_id = ?", //where ou selection
                    new String[]{Integer.toString(idFigura)},//substitui o ? no where
                    null,null,null,null);//restante dos parametros null)

            if(cursor2.moveToFirst()){
                String v1;
                String nomeFigura;
                String plano;

                TextView textViewNomeFigura = findViewById(R.id.textViewHistoricoNomeFigura);
                TextView textViewTituloAdaptativo = findViewById(R.id.textViewHistoricoTituloAdaptativo);
                TextView textViewResultadoAdaptativo = findViewById(R.id.textViewHistoricoResultadoAdaptativo);
                TextView textViewPlano = findViewById(R.id.textViewHistoricoPlano);

                if(idiomaBase.equals(idiomaUtilizado)){
                    plano = cursor2.getString(1);
                    nomeFigura = cursor2.getString(0);
                }
                else{
                    v1 = cursor2.getString(1);
                    if(v1.equals("Bidimensional")){
                        plano = getString(R.string.bidimensional);
                    }
                    else{
                        plano = getString(R.string.tridimensional);
                    }
                    nomeFigura = cursor2.getString(2);
                }

                textViewNomeFigura.setText(nomeFigura);
                textViewPlano.setText(plano);

                if(plano.equals("Tridimensional") || plano.equals("Three-dimensional")){
                    textViewResultadoAdaptativo.setText(valorVolume);
                    textViewTituloAdaptativo.setText(R.string.volume);
                }
                else {
                    textViewResultadoAdaptativo.setText(valorPerimetro);
                    textViewTituloAdaptativo.setText(R.string.perimetro);
                }
            }

        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
