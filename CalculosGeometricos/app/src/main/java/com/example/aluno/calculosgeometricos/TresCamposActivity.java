package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

public class TresCamposActivity extends Activity {
    public static final String RESULTADOAREA = "com.example.aluno.calculosgeometricos.RESULTADOAREA";
    public static final String RESULTADOVOLUME = "com.example.aluno.calculosgeometricos.RESULTADOVOLUME";
    public static final String NOMEOPERACAO = "com.example.aluno.calculosgeometricos.NOMEOPERACAO";
    private EditText editTextCampo1;
    private EditText editTextCampo2;
    private EditText editTextCampo3;
    private String nomeFigura;
    private String nomeFiguraEng;
    private String resultadoArea;
    private String resultadoVolume;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tres_campos);

        editTextCampo1 = findViewById(R.id.editTextTresCampo1);
        editTextCampo2 = findViewById(R.id.editTextTresCampo2);
        editTextCampo3 = findViewById(R.id.editTextTresCampo3);

        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        nomeFiguraEng = getIntent().getStringExtra(ListFiguras.NOMEFIGURAENG);

        if(nomeFigura.equals("Prisma retangular")){
            editTextCampo2.setHint(R.string.largura_base);
        }
        else if(nomeFigura.equals("Prisma triangular")){
            editTextCampo2.setHint(R.string.altura_base);
        }
    }

    public void calcular(View view) {
        Intent intent = new Intent(this, ResultadoActivity.class);
        EditText editTextNomeOperacao = findViewById(R.id.editTextNomeOperacao3);

        String campo1 = editTextCampo1.getText().toString();
        String campo2 = editTextCampo2.getText().toString();
        String campo3 = editTextCampo3.getText().toString();
        String nomeOperacao = editTextNomeOperacao.getText().toString();
        String plano = getIntent().getStringExtra(MenuActivity.PLANO);

        Double v1 = Double.parseDouble(campo1);
        Double v2 = Double.parseDouble(campo2);
        Double v3 = Double.parseDouble(campo3);
        Double areaBase;
        Double volume;
        Double areaTotal;

        if(nomeFigura.equals("Prisma retangular")){
            areaBase = (v1 * v2);
            volume = areaBase * v3;

            areaTotal = 2 * v1 * (v2 + (2 * v3));

            resultadoVolume = volume.toString();
            resultadoArea = areaTotal.toString();
        }
        else if(nomeFigura.equals("Prisma Triangular")){
            areaBase = (v1 *v2) / 2;
            volume = areaBase * v3;

            areaTotal = v1 * (v2 + (3 * v3));

            resultadoVolume = volume.toString();
            resultadoArea = areaTotal.toString();
        }

        intent.putExtra(NOMEOPERACAO, nomeOperacao);
        intent.putExtra(MenuActivity.PLANO, plano);
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
        intent.putExtra(RESULTADOVOLUME, resultadoVolume);
        intent.putExtra(RESULTADOAREA, resultadoArea);

        startActivity(intent);
    }
}
