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

        String nomeOperacao = getIntent().getStringExtra(UmCampoActivity.NOMEOPERACAO);
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);
        String nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        String resultadoPerimetro = getIntent().getStringExtra(UmCampoActivity.RESULTADOPERIMETRO);
        String resultadoVolume = getIntent().getStringExtra(UmCampoActivity.RESULTADOVOLUME);
        String resultadoArea = getIntent().getStringExtra(UmCampoActivity.RESULTADOAREA);

        TextView textViewNomeOperacao = findViewById(R.id.textViewNomeOperacao);
        TextView textViewPlano = findViewById(R.id.textViewPlano);
        TextView textViewNomeFigura = findViewById(R.id.textViewNomeFigura);
        TextView textViewResultadoAdap = findViewById(R.id.textViewResultadoAdap);
        TextView textViewTituloAdap = findViewById(R.id.textViewTituloAdap);
        TextView textViewResultadoArea = findViewById(R.id.textViewResultadoArea);

        textViewNomeOperacao.setText(nomeOperacao);
        textViewPlano.setText(plano);
        textViewNomeFigura.setText(nomeFigura);

        if(plano.equals("Tridimensional")){
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
