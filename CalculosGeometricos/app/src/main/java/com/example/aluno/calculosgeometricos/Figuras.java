package com.example.aluno.calculosgeometricos;

public class Figuras {
    public String nome;
    public String tipo;

    public static final Figuras[] figuras = {
        new Figuras("Quadrado", "Bidimensional"),
        new Figuras("Retangulo", "Bidimensional"),
        new Figuras("Trapezio", "Bidimensional"),
        new Figuras("Triangulo", "Bidimensional"),
        new Figuras("Circulo", "Bidimensional"),
        new Figuras("Cubo", "Tridimensional"),
        new Figuras("Prisma Retangular", "Tridimensional"),
        new Figuras("Prisma Trapezoidal", "Tridimensional"),
        new Figuras("Prisma Triangular", "Tridimensional"),
        new Figuras("Esfera", "Tridimensional")
    };
    public Figuras(String nome, String tipo) {
        this.nome = nome;
        this.tipo = tipo;
    }

    public String getNome(){
        return nome;
    }

    public String getTipo(){
        return tipo;
    }

    public String toString(){
        return this.nome;
    }
}
