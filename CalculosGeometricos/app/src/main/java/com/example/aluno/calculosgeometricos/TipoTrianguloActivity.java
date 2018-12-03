package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;

public class TipoTrianguloActivity extends Activity {
    public static final String TIPOTRIANGULO = "com.example.aluno.calculosgeometricos.TIPOTRIANGULO";
    private String nomeFigura;
    private String nomeFiguraEng;
    private String plano;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tipo_triangulo);
        nomeFigura = getIntent().getStringExtra(ListFiguras.NOMEFIGURA);
        nomeFiguraEng = getIntent().getStringExtra(ListFiguras.NOMEFIGURAENG);
        plano = getIntent().getStringExtra(MenuActivity.PLANO);

    }

    public void equilatero(View view) {
        Intent intent = new Intent(this, DoisCamposActivity.class);
        intent.putExtra(TIPOTRIANGULO, "Equilatero");
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
        intent.putExtra(MenuActivity.PLANO, plano);
        startActivity(intent);
    }

    public void isosceles(View view) {
        Intent intent = new Intent(this, DoisCamposActivity.class);
        intent.putExtra(TIPOTRIANGULO, "Isosceles");
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
        intent.putExtra(MenuActivity.PLANO, plano);
        startActivity(intent);
    }

    public void retangulo(View view) {
        Intent intent = new Intent(this, DoisCamposActivity.class);
        intent.putExtra(TIPOTRIANGULO, "Retangulo");
        intent.putExtra(ListFiguras.NOMEFIGURA, nomeFigura);
        intent.putExtra(ListFiguras.NOMEFIGURAENG, nomeFiguraEng);
        intent.putExtra(MenuActivity.PLANO, plano);
        startActivity(intent);
    }
}
