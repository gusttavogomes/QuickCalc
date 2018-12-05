package com.example.aluno.calculosgeometricos;

import android.content.ContentValues;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

public class CalculoDataBaseHelper extends SQLiteOpenHelper {
    private static final String DB_NAME = "calculodb";
    private static final int DB_VERSION = 1;

    CalculoDataBaseHelper(Context context) {super(context, DB_NAME,null, DB_VERSION);}

    @Override
    public void onCreate(SQLiteDatabase db){
        db.execSQL("CREATE TABLE figuras (" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "tipo TEXT, " +
                "nomeEng);");

        db.execSQL("CREATE TABLE historicos (_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                "nome TEXT, " +
                "area TEXT, " +
                "volume TEXT, " +
                "perimetro TEXT, " +
                "idFigura INTEGER, " +
                "FOREIGN KEY(idFigura) REFERENCES figuras(_id));");

        insertFiguras(db, "Quadrado", "Bidimensional", "Square");
        insertFiguras(db, "Retangulo", "Bidimensional", "Rectangle");
        insertFiguras(db, "Triangulo", "Bidimensional", "Triangle");
        insertFiguras(db, "Circulo", "Bidimensional", "Circle");

        insertFiguras(db, "Cubo", "Tridimensional", "Cube");
        insertFiguras(db, "Prisma retangular", "Tridimensional", "Rectangular Prism");
        insertFiguras(db, "Prisma triangular", "Tridimensional", "Triangular Prism");
        insertFiguras(db, "Esfera", "Tridimensional", "Sphere");
    }
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
    }

    public static void insertHistorico(SQLiteDatabase db, String nome, String area, String volume, String Perimetro , int _idFigura) {
        ContentValues valoresHistorico = new ContentValues();
        valoresHistorico.put("nome", nome);
        valoresHistorico.put("area", area);
        valoresHistorico.put("volume", volume);
        valoresHistorico.put("perimetro", Perimetro);
        valoresHistorico.put("idFigura", _idFigura);
        db.insert("historicos", null, valoresHistorico);
    }

    public static void insertFiguras(SQLiteDatabase db, String nome, String tipo, String nomeEng) {
        ContentValues valoresFiguras = new ContentValues();
        valoresFiguras.put("nome", nome);
        valoresFiguras.put("tipo", tipo);
        valoresFiguras.put("nomeEng", nomeEng);
        db.insert("figuras", null, valoresFiguras);
    }
}
