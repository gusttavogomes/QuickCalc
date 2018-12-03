package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

public class ResultadoActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_resultado);
        String v1;

        String idiomaBase = "Idioma";
        String idiomaUtilizado = getString(R.string.idioma);

        String plano;
        String nomeFigura;

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


        String nomeOperacao = getIntent().getStringExtra(UmCampoActivity.NOMEOPERACAO);
        String resultadoPerimetro = getIntent().getStringExtra(UmCampoActivity.RESULTADOPERIMETRO);
        String resultadoVolume = getIntent().getStringExtra(UmCampoActivity.RESULTADOVOLUME);
        String resultadoArea = getIntent().getStringExtra(UmCampoActivity.RESULTADOAREA);

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
        Intent intent = new Intent(this, HistoricoActivity.class);
        startActivity(intent);
    }
}
