/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1programacion3;

/**
 *
 * @author ianto
 */
import java.awt.*;
import javax.swing.*;



public class ArbolGrafico extends JPanel {
    private static final int ANCHO_NODO = 40;
    private static final int ALTO_NODO = 40;
    private static final int DISTANCIA_HORIZONTAL = 200;
    private static final int DISTANCIA_VERTICAL = 80;

    public Nodo raiz;

    public ArbolGrafico(Nodo raiz) {
        this.raiz = raiz;
    }

    public static void mostrarArbolExpresion(ConstruirArbolExpresion arbol) {
        JFrame frame = new JFrame("Árbol de Expresión");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(1200, 900);

        // Crea un panel para mostrar el árbol
        ArbolGrafico panel = new ArbolGrafico(arbol.inicio);
        frame.add(panel);

        frame.setVisible(true);
    }


    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        if (raiz != null) {
            dibujarNodo(g, getWidth() / 2, 140, raiz, DISTANCIA_HORIZONTAL);
        }
    }

    private void dibujarNodo(Graphics g, int x, int y, Nodo nodo, int espacio) {
        g.setColor(Color.BLACK);
        g.drawOval(x, y, ANCHO_NODO, ALTO_NODO);
        g.drawString(nodo.getDato(), x + 12, y + 24);// numeros circulo

        if (nodo.getHijoIzquierdo() != null) {
            int nuevoEspacio = DISTANCIA_HORIZONTAL-25;
            g.drawLine(x + ANCHO_NODO /2, y + ALTO_NODO, x - espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);
            dibujarNodo(g, x - espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoIzquierdo(), nuevoEspacio);
        }

        if (nodo.getHijoDerecho() != null) {
            int nuevoEspacio = DISTANCIA_HORIZONTAL-80;
            g.drawLine(x + ANCHO_NODO / 2, y + ALTO_NODO, x + espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);
            dibujarNodo(g, x + espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoDerecho(), nuevoEspacio);
        }
    }
}