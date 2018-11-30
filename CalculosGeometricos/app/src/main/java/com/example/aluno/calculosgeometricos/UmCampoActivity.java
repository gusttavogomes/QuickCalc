package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UmCampoActivity extends Activity {

    public static final String RESULTADOAREA = "com.example.aluno.calculosgeometricos.RESULTADOAREA";
    public static final String RESULTADOPERIMETRO = "com.example.aluno.calculosgeometricos.RESULTADOPERIMETRO";
    public static final String NOMEOPERACAO = "com.example.aluno.calculosgeometricos.NOMEOPERACAO";
    private String nomeFigura;
    private String resultadoArea;
    private String resultadoPerimetro;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_um_campo);


        TextView textViewNomeFigura = (TextView) findViewById(R.id.textViewNomeFigura);
        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        textViewNomeFigura.setText(nomeFigura);
    }

    public void calcular(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        EditText editTextLado1 = (EditText) findViewById(R.id.editTextLado1);
        EditText editTextNomeOperacao = (EditText) findViewById(R.id.editTextNomeOperacao);

        String lado1 = editTextLado1.getText().toString();
        String nomeOperacao = editTextNomeOperacao.getText().toString();
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);
        Double v1 = Double.parseDouble(lado1);
        Double area;
        Double perimetro;

        if(nomeFigura.equals("Quadrado")){
            area = v1*v1;
            perimetro = v1*4;

            resultadoArea = area.toString();
            resultadoPerimetro = perimetro.toString();
        }
        if(nomeFigura.equals("Circulo")){
            area = 3.1415926 * (v1 * v1);
            perimetro = (3.1415926 * 3.1415926) * v1;

            resultadoArea = area.toString();
            resultadoPerimetro = perimetro.toString();
        }

        intent.putExtra(RESULTADOAREA, resultadoArea);
        intent.putExtra(RESULTADOPERIMETRO, resultadoPerimetro);
        intent.putExtra(NOMEOPERACAO, nomeOperacao);
        intent.putExtra(MenuActivity.PLANO, plano);

        startActivity(intent);
    }
}
