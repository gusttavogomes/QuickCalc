package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class UmCampoActivity extends Activity {

    public static final String RESULTADOAREA = "com.example.aluno.calculosgeometricos.RESULTADOAREA";
    public static final String RESULTADOPERIMETRO = "com.example.aluno.calculosgeometricos.RESULTADOPERIMETRO";
    public static final String RESULTADOVOLUME = "com.example.aluno.calculosgeometricos.RESULTADOVOLUME";
    public static final String NOMEOPERACAO = "com.example.aluno.calculosgeometricos.NOMEOPERACAO";
    private EditText editTextCampo1;
    private String idFigura;
    private String nomeFigura;
    private String nomeFiguraEng;
    private String resultadoArea;
    private String resultadoPerimetro;
    private String resultadoVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_um_campo);

        editTextCampo1 =  findViewById(R.id.editTextUmCampo1);

        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        nomeFiguraEng = getIntent().getStringExtra(ListFiguras.NOMEFIGURAENG);
        idFigura = getIntent().getStringExtra(ListFiguras.IDFIGURA);


        if(nomeFigura.equals("Quadrado")){
            editTextCampo1.setHint(R.string.aresta);
        }
        else if(nomeFigura.equals("Circulo")){
            editTextCampo1.setHint(R.string.raio);
        }
        else if(nomeFigura.equals("Cubo")){
            editTextCampo1.setHint(R.string.aresta);
        }
        else if(nomeFigura.equals("Esfera")){
            editTextCampo1.setHint(R.string.raio);
        }
    }

    public void calcular(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        EditText editTextNomeOperacao = (EditText) findViewById(R.id.editTextNomeOperacao2);

        String lado1 = editTextCampo1.getText().toString();
        String nomeOperacao = editTextNomeOperacao.getText().toString();
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);

        try{
            Double v1 = Double.parseDouble(lado1);
            Double area;
            Double perimetro;
            Double volume;
            if(nomeFigura.equals("Quadrado")){
                area = Math.pow(v1,2);
                perimetro = v1 * 4;

                resultadoArea = area.toString();
                resultadoPerimetro = perimetro.toString();
            }
            else if(nomeFigura.equals("Circulo")){
                area = Math.pow(v1,2) * 3.1415926;
                perimetro = 2 * 3.1415926 * v1;

                resultadoArea = area.toString();
                resultadoPerimetro = perimetro.toString();
            }
            else if(nomeFigura.equals("Cubo")){
                area = 6 * Math.pow(v1,2);
                volume = Math.pow(v1,3);

                resultadoArea = area.toString();
                resultadoVolume = volume.toString();
            }
            else if(nomeFigura.equals("Esfera")){
                area = 4 * 3.1415926 * Math.pow(v1,2);
                volume = (4 * 3.1415926 * Math.pow(v1,3)) / 3;

                resultadoArea = area.toString();
                resultadoVolume = volume.toString();
            }

            intent.putExtra(NOMEOPERACAO, nomeOperacao);
            intent.putExtra(MenuActivity.PLANO, plano);
            intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
            intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
            intent.putExtra(ListFiguras.IDFIGURA, idFigura);
            intent.putExtra(RESULTADOPERIMETRO, resultadoPerimetro);
            intent.putExtra(RESULTADOVOLUME, resultadoVolume);
            intent.putExtra(RESULTADOAREA, resultadoArea);

            startActivity(intent);
        }catch (Exception ex){
            Toast toast = Toast.makeText(this, R.string.erroCalcular, Toast.LENGTH_LONG);
            toast.show();

        }
    }
}
