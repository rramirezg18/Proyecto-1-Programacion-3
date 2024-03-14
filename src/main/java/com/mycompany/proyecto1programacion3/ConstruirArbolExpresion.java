/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1programacion3;

/**
 *
 * @author ianto
 */
import java.util.Stack;
public class ConstruirArbolExpresion implements Metodos {
    
    public Nodo inicio; //variable de tipo nodo que representara la raiz del árbol
	
	public ConstruirArbolExpresion() {
		this.inicio = null;
	}
        
  public void construirArbol(String expresionMatematica) {
        String expresionPosfija = convertirAPosfija(expresionMatematica);
        Stack<Nodo> pila = new Stack<>();
        for (char caracter : expresionPosfija.toCharArray()) {
            if (Character.isLetterOrDigit(caracter)) { // Verifica si el caracter es una letra o un dígito.
                Nodo nodo = new Nodo(String.valueOf(caracter));
                pila.push(nodo);
            } else {
                Nodo nodoOperador = new Nodo(String.valueOf(caracter));
                Nodo hijoDerecho = pila.pop();
                Nodo hijoIzquierdo = pila.pop();
                nodoOperador.setHijoIzquierdo(hijoIzquierdo);
                nodoOperador.setHijoDerecho(hijoDerecho);
                pila.push(nodoOperador);
            }
        }
        this.inicio = pila.pop();
    }
    
    private String convertirAPosfija(String expresionMatematica) {
        //StringBuilder compara cadenas dinamicamente y en la variable posfija guarda la cadena inversa polaca
        StringBuilder posfija = new StringBuilder();
        Stack<Character> operadores = new Stack<>(); // pila que almacena los operadores
        for (int i = 0; i < expresionMatematica.length(); i++) { // recorre la expresion matematica
            char caracter = expresionMatematica.charAt(i); // obtiene los caracter por caracter de la expresion matematica
            if (Character.isDigit(caracter)) { 
                posfija.append(caracter); // Agrega el dígito directamente o variable a la expresión posfija que es la cadena polaca
            } else if (caracter == '(') { // Si es un paréntesis izquierdo, lo apila en la pila de operadores hasta encontrar su correspondiente dreecho.
                operadores.push(caracter);
            } else if (caracter == ')') { // Si es un paréntesis derecho, desapila operadores hasta encontrar el izquierdo correspondiente.
                while (!operadores.isEmpty() && operadores.peek() != '(') { //mientras la pila de operadores sea diferente de limpio y operadores.peek osea el primer parantesis de la pila sea diferente de izquierdo
                    posfija.append(operadores.pop());
                }
                operadores.pop(); // Elimina el paréntesis izquierdo de la pila.
            } else { // Si es un operador, desapila operadores de mayor o igual precedencia y los agrega a la expresión posfija.
                while (!operadores.isEmpty() && jerarquia(operadores.peek()) >= jerarquia(caracter)) {
                    posfija.append(operadores.pop());
                }
                operadores.push(caracter); // Agrega el operador actual a la pila.
            }
        }
        while (!operadores.isEmpty()) { // Desapila los operadores restantes y los agrega a la expresión posfija.
            posfija.append(operadores.pop());
        }
        return posfija.toString(); // Devuelve la expresión posfija como una cadena.
    }
    
    private int jerarquia(char operador) { // metodo para determinar la precedencia de un operador.
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
             
                return 2;
            case '^':
            //case '√': No funciona como simbolo de raiz
                return 3;
            default:
                return 0;
        }
    }        
        
    @Override
    public void mostrarInOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarPreOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public void mostrarPosOrden() {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
    
}
