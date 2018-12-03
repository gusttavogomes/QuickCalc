package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;
import android.widget.TextView;

public class DoisCamposActivity extends Activity {

    public static final String RESULTADOAREA = "com.example.aluno.calculosgeometricos.RESULTADOAREA";
    public static final String RESULTADOPERIMETRO = "com.example.aluno.calculosgeometricos.RESULTADOPERIMETRO";
    public static final String NOMEOPERACAO = "com.example.aluno.calculosgeometricos.NOMEOPERACAO";
    private EditText editTextCampo1;
    private EditText editTextCampo2;
    private String nomeFigura;
    private String nomeFiguraEng;
    private String resultadoArea;
    private String resultadoPerimetro;
    private String tipoTriangulo;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dois_campos);

        editTextCampo1 = findViewById(R.id.editTextDoisCampo1);
        editTextCampo2 = findViewById(R.id.editTextDoisCampo2);

        tipoTriangulo = getIntent().getStringExtra(TipoTrianguloActivity.TIPOTRIANGULO);
        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        nomeFiguraEng = getIntent().getStringExtra(ListFiguras.NOMEFIGURAENG);
    }

    public void calcular(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        EditText editTextNomeOperacao = findViewById(R.id.editTextNomeOperacao2);

        String campo1 = editTextCampo1.getText().toString();
        String campo2 = editTextCampo2.getText().toString();
        String nomeOperacao = editTextNomeOperacao.getText().toString();
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);

        Double v1 = Double.parseDouble(campo1);
        Double v2 = Double.parseDouble(campo2);
        Double area;
        Double v3;
        Double perimetro;

        if (nomeFigura.equals("Triangulo")){

            if(tipoTriangulo.equals("Retangulo")){
                area = (v1 * v2) / 2;
                v3 = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));
                perimetro = v1 + v2 + v3;

                resultadoArea = area.toString();
                resultadoPerimetro = perimetro.toString();
            }
            else if(tipoTriangulo.equals("Isosceles")){
                area = (v1 * v2) / 2;
                v3 = Math.sqrt(Math.pow(v1, 2) + Math.pow(v2, 2));
                perimetro = 2 * v3 + v1;

                resultadoArea = area.toString();
                resultadoPerimetro = perimetro.toString();
            }
            else if(tipoTriangulo.equals("Equilatero")){
                area = (v1 * v2) / 2;
                perimetro = 3 * v1;

                resultadoArea = area.toString();
                resultadoPerimetro = perimetro.toString();
            }
        }
        else if(nomeFigura.equals("Retangulo")){
            area = v1 * v2 ;
            perimetro = 2 * (v1 + v2) ;

            resultadoArea = area.toString();
            resultadoPerimetro = perimetro.toString();
        }

        intent.putExtra(NOMEOPERACAO, nomeOperacao);
        intent.putExtra(MenuActivity.PLANO, plano);
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
        intent.putExtra(RESULTADOPERIMETRO, resultadoPerimetro);
        intent.putExtra(RESULTADOAREA, resultadoArea);

        startActivity(intent);
    }
}
