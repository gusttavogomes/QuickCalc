package com.example.aluno.calculosgeometricos;

import android.app.Activity;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteException;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.widget.TextView;
import android.widget.Toast;

public class ListFigura extends Activity {

    public static final String EXTRA_NUMERO = "numerofigura";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_figura);

        int numeroFigura = (Integer) getIntent().getExtras().get(EXTRA_NUMERO);

        try{
            SQLiteOpenHelper cursosDatabaseHelper = new CalculoDataBaseHelper(this);
            SQLiteDatabase db = cursosDatabaseHelper.getReadableDatabase();

            Cursor cursor = db.query("figuras",
                    new String[] {"nome", "tipo"},
                    "_id = ?",
                    new String[] {Integer.toString(numeroFigura)},
                    null, null, null);

            if (cursor.moveToFirst()){
                String valorNome = cursor.getString(0);
                String valorTipo = cursor.getString(1);

                TextView nome = (TextView) findViewById(R.id.nome);
                nome.setText(valorNome);

                TextView tipo = (TextView) findViewById(R.id.tipo);
                nome.setText(valorTipo);
            }
            cursor.close();
            db.close();
        }catch (SQLiteException e) {
            Toast toast = Toast.makeText(this, "DB indispoível", Toast.LENGTH_LONG);
            toast.show();
        }
    }
}
