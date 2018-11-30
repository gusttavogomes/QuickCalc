package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class UmCampoActivity extends Activity {

    public static final String RESULTADOAREA = "com.example.aluno.calculosgeometricos.RESULTADOAREA";
    public static final String RESULTADOPERIMETRO = "com.example.aluno.calculosgeometricos.RESULTADOPERIMETRO";
    public static final String RESULTADOVOLUME = "com.example.aluno.calculosgeometricos.RESULTADOVOLUME";
    public static final String NOMEOPERACAO = "com.example.aluno.calculosgeometricos.NOMEOPERACAO";
    private EditText editTextLado1;
    private String nomeFigura;
    private String resultadoArea;
    private String resultadoPerimetro;
    private String resultadoVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_um_campo);

        editTextLado1 =  findViewById(R.id.editTextLado1);
        TextView textViewNomeFigura =  findViewById(R.id.textViewNomeFigura);
        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);

        textViewNomeFigura.setText(nomeFigura);

        if(nomeFigura.equals("Quadrado")){
            editTextLado1.setHint("Valor de uma das aresta");
        }
        else if(nomeFigura.equals("Circulo")){
            editTextLado1.setHint("Valor do raio");
        }
        else if(nomeFigura.equals("Cubo")){
            editTextLado1.setHint("Valor de uma das aresta");
        }
        else if(nomeFigura.equals("Esfera")){
            editTextLado1.setHint("Valor do raio");
        }
    }

    public void calcular(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        EditText editTextNomeOperacao = (EditText) findViewById(R.id.editTextNomeOperacao);

        String lado1 = editTextLado1.getText().toString();
        String nomeOperacao = editTextNomeOperacao.getText().toString();
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);

        Double v1 = Double.parseDouble(lado1);
        Double area;
        Double perimetro;
        Double volume;

        if(nomeFigura.equals("Quadrado")){
            area = v1 * v1;
            perimetro = v1 * 4;

            resultadoArea = area.toString();
            resultadoPerimetro = perimetro.toString();
        }
        else if(nomeFigura.equals("Circulo")){
            area = 3.1415926 * (v1 * v1);
            perimetro = (3.1415926 * 3.1415926) * v1;

            resultadoArea = area.toString();
            resultadoPerimetro = perimetro.toString();
        }
        else if(nomeFigura.equals("Cubo")){
            area = 6 * (v1 * v1);
            volume = v1 * v1 * v1;

            resultadoVolume = volume.toString();
            resultadoArea = area.toString();
        }
        else if(nomeFigura.equals("Esfera")){
            area = 4 * 3.1415926 * (v1 * v1);
            volume = (4 * 3.1415926 * (v1 * v1 * v1)) / 3;

            resultadoVolume = volume.toString();
            resultadoArea = area.toString();
        }

        intent.putExtra(NOMEOPERACAO, nomeOperacao);
        intent.putExtra(MenuActivity.PLANO, plano);
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(RESULTADOPERIMETRO, resultadoPerimetro);
        intent.putExtra(RESULTADOVOLUME, resultadoVolume);
        intent.putExtra(RESULTADOAREA, resultadoArea);

        startActivity(intent);
    }
}
