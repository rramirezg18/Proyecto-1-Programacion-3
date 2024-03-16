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
        JFrame Ventana = new JFrame("Árbol de Expresión");
        Ventana.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//cuando se cierre ventana termina
        Ventana.setSize(1200, 900);//dimenciones de ventana como html :v

        // Crea un panel para mostrar el árbol
        ArbolGrafico panel = new ArbolGrafico(arbol.inicio);//se crea el panel grafico
        Ventana.add(panel);//se agrega el panel de arbol grafico sobre la ventana

        Ventana.setVisible(true);//para hacer visible el panel
    }


    @Override
    protected void paintComponent(Graphics g) {//paint componet sirve para redibujar se utiliza en java sWing
        super.paintComponent(g);
        if (raiz != null) {
            dibujarNodo(g, getWidth() / 2, 140, raiz, DISTANCIA_HORIZONTAL);//funcion de para dibujar el arbol en k punto del panel se va a dubujar aqui sepuede desplazar x y y

        }
    }

    private void dibujarNodo(Graphics g, int x, int y, Nodo nodo, int espacio) {//funcion para dibujar el nodo recibe parametors x yx el nodo y el espacio entre los nodos
        g.setColor(Color.BLACK);//define el color con el cual se va a dibujar en toda la ventana en este caso el negor
        g.drawOval(x, y, ANCHO_NODO, ALTO_NODO);//dibuja un circulo en la pos x y x de un acho del nodo y algo del dono
        g.drawString(nodo.getDato(), x + 12, y + 24);// numeros circulo

        if (nodo.getHijoIzquierdo() != null) {//verifica si existe un hijo izquierdo si llega a existir entonces hace lo que esta adentro
            int nuevoEspacio = DISTANCIA_HORIZONTAL-25;//este espacio es el que se utlizia distanciar un nodo de otro en su distancia horizontal
            g.drawLine(x + ANCHO_NODO /2, y + ALTO_NODO, x - espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);//se dubuja la lina de separacion desde el centro del nodo hasta la mitad de separacion entre el nodo actual y su nodo en en su nodo iz y desde la parte inf en la pos y + alto de nood hastta una distancia vertical por distancia_Vertical
            dibujarNodo(g, x - espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoIzquierdo(), nuevoEspacio);//se llama al meotdo dibujar para que dubuje los nodos en su espacio x y y
        }

        if (nodo.getHijoDerecho() != null) { //lo mismo que el izquiero solo que del lado derecho :v
            int nuevoEspacio = DISTANCIA_HORIZONTAL-80;
            g.drawLine(x + ANCHO_NODO / 2, y + ALTO_NODO, x + espacio / 2 + ANCHO_NODO / 2, y + DISTANCIA_VERTICAL);
            dibujarNodo(g, x + espacio / 2, y + DISTANCIA_VERTICAL, nodo.getHijoDerecho(), nuevoEspacio);
        }
    }
}