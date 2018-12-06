package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.database.SQLException;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import static com.example.aluno.calculosgeometricos.CalculoDataBaseHelper.insertHistorico;

public class ResultadoActivity extends Activity {

    private String nomeOperacao;
    private String resultadoPerimetro;
    private String resultadoVolume;
    private String resultadoArea;
    private int idFigura;
    private SQLiteDatabase db;
    private String nomeFigura;
    private String plano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        SQLiteOpenHelper calculoDataBaseHelper = new CalculoDataBaseHelper(this);
        db = calculoDataBaseHelper.getWritableDatabase();

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        String v1;

        String idiomaBase = "Idioma";
        String idiomaUtilizado = getString(R.string.idioma);

        if(idiomaBase.equals(idiomaUtilizado)){
            plano = getIntent().getStringExtra(MenuActivity.PLANO);
            nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        }
        else{
            v1 = getIntent().getStringExtra(MenuActivity.PLANO);
            if(v1.equals("Bidimensional")){
                plano = getString(R.string.bidimensional);
            }
            else{
                plano = getString(R.string.tridimensional);
            }
            nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURAENG);
        }


        nomeOperacao = getIntent().getStringExtra(UmCampoActivity.NOMEOPERACAO);
        resultadoPerimetro = getIntent().getStringExtra(UmCampoActivity.RESULTADOPERIMETRO);
        resultadoVolume = getIntent().getStringExtra(UmCampoActivity.RESULTADOVOLUME);
        resultadoArea = getIntent().getStringExtra(UmCampoActivity.RESULTADOAREA);
        String idFig = getIntent().getStringExtra(ListFiguras.IDFIGURA);

        idFigura = Integer.parseInt(idFig);

        TextView textViewNomeOperacao = findViewById(R.id.textViewNomeOperacao);
        TextView textViewPlano = findViewById(R.id.textViewPlano);
        TextView textViewNomeFigura = findViewById(R.id.textViewDoisNomeFigura);
        TextView textViewResultadoAdap = findViewById(R.id.textViewResultadoAdap);
        TextView textViewTituloAdap = findViewById(R.id.textViewTituloAdap);
        TextView textViewResultadoArea = findViewById(R.id.textViewResultadoArea);

        textViewNomeOperacao.setText(nomeOperacao);
        textViewPlano.setText(plano);
        textViewNomeFigura.setText(nomeFigura);

        if(plano.equals("Tridimensional") || plano.equals("Three-dimensional")){
            textViewResultadoAdap.setText(resultadoVolume);
            textViewTituloAdap.setText(R.string.volume);
        }
        else {
            textViewResultadoAdap.setText(resultadoPerimetro);
            textViewTituloAdap.setText(R.string.perimetro);
        }
        textViewResultadoArea.setText(resultadoArea);

    }

    public void listarHistorico(View view) {
        Intent intent = new Intent(this, HomeActivity.class);

        try{
            insertHistorico(db, nomeOperacao, resultadoArea, resultadoVolume, resultadoPerimetro , idFigura);
        }catch (SQLException e){
            Toast toast = Toast.makeText(this, "DB indisponível", Toast.LENGTH_LONG);
            toast.show();
        }
        startActivity(intent);
    }

    public void enviarSMS(View view) {
        String msg;

        if(resultadoPerimetro == null){
            msg ="Nome da operação: " + nomeOperacao + "\nnome da figura: " + nomeFigura + "\ntipo da figura: " + plano + "\nresultado da área: " + resultadoArea + "\nresultado do volume: " + resultadoVolume;
        }
        else{
            msg ="Nome da operação: " + nomeOperacao + "\nnome da figura: " + nomeFigura + "\ntipo da figura: " + plano + "\nresultado da área: " + resultadoArea + "\nresultado do perimetro: " + resultadoPerimetro;
        }

        Intent intent = new Intent();
        intent.setAction(android.content.Intent.ACTION_SEND);
        intent.putExtra(Intent.EXTRA_TEXT, msg);

        intent.setType("text/plain");
        startActivity(intent);
    }
}
