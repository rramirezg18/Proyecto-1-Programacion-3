/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1programacion3;

/*Proyecto 1 - Arbol De Expresion
 * El proyecto consiste en ingresar una expresion matematica a+b(c-d)-e y generar al arbol de expresion 
 * sus reccoridos y la notacion polaca como respuesta
 * Roberto Antonio Ramírez Gómez	7690-22-12700
 * Jean Klaus Castañeda Santos		7690-22-892		
 * Jonathan Joel Chan Cuellar		7690-22-1805
 * Bryan Manuel Pineda Orozco           7690-16-8869
 */
import java.util.Stack;
public class ConstruirArbolExpresion implements Metodos {
    
    public Nodo inicio; //variable de tipo nodo que representara la raiz del árbol es decir el inicia
	
	public ConstruirArbolExpresion() {
		this.inicio = null;
	}
        
    public void construirArbol(String expresionMatematica) {
        String expresionPosfija = convertirAPosfija(expresionMatematica);
        Stack<Nodo> pila = new Stack<>();
        boolean ultimoFueOperador = true; //variable para encontrar el ultimo caracter y que sea negativo
        String[] caracteresIndividuales = expresionPosfija.split(" ");//divide la expresion posfica en caracteres individuales separados " " y los almacena en un array de caracteres
        for (String caracterIndividual : caracteresIndividuales) {//recorre sobre cada caracter
            if (!caracterIndividual.isEmpty()) {//comprueba que el ultimo caracter es numero negativo o no
                //luego se agrega el nodo con el caracter y se cambia el estado de la variable ultimoFueOperador si el dato no fue negativo
                if (Character.isDigit(caracterIndividual.charAt(0)) || (caracterIndividual.startsWith("-") && caracterIndividual.length() > 1)) {
                    Nodo nodo = new Nodo(caracterIndividual);
                    pila.push(nodo);
                    ultimoFueOperador = false;
                } else if (caracterIndividual.equals("-")) {//Si el ultimo caracter fue "-" lo agrega a la pila como un solo nodo junto con el numero que lo acompaña
                    if (ultimoFueOperador) {
                        pila.push(new Nodo("-")); // 
                    } else {
                        Nodo nodoOperador = new Nodo("-");
                        Nodo hijoDerecho = pila.pop();
                        Nodo hijoIzquierdo = pila.pop();
                        nodoOperador.setHijoIzquierdo(hijoIzquierdo);
                        nodoOperador.setHijoDerecho(hijoDerecho);
                        pila.push(nodoOperador);
                    }
                    ultimoFueOperador = true;//cambia nuevamentne el valor de la variable a true porque el ultimo caracter si fue negativo
                } else {
                    Nodo nodoOperador = new Nodo(caracterIndividual);
                    Nodo hijoDerecho = pila.pop();
                    Nodo hijoIzquierdo = pila.pop();
                    nodoOperador.setHijoIzquierdo(hijoIzquierdo);
                    nodoOperador.setHijoDerecho(hijoDerecho);
                    pila.push(nodoOperador);
                    ultimoFueOperador = true;
                }
            }
        }
        this.inicio = pila.pop();
    }
    
    private String convertirAPosfija(String expresionMatematica) {
        StringBuilder posfija = new StringBuilder();
        Stack<Character> operadores = new Stack<>();
        boolean ultimoFueOperador = true;
        for (int i = 0; i < expresionMatematica.length(); i++) {
            char caracter = expresionMatematica.charAt(i);
            if (Character.isDigit(caracter) || (caracter == '-' && ultimoFueOperador)) {
                if (caracter == '-') {
                    posfija.append(caracter);
                    i++;
                    caracter = expresionMatematica.charAt(i);
                    while (i < expresionMatematica.length() && Character.isDigit(caracter)) {
                        posfija.append(caracter);
                        i++;
                        if (i < expresionMatematica.length())
                            caracter = expresionMatematica.charAt(i);
                    }
                    posfija.append(" ");
                    i--;
                } else {
                    posfija.append(caracter).append(" ");
                }
                ultimoFueOperador = false;
            } else if (caracter == '(') {
                operadores.push(caracter);
                ultimoFueOperador = true;
            } else if (caracter == ')') {
                while (!operadores.isEmpty() && operadores.peek() != '(') {
                    posfija.append(operadores.pop()).append(" ");
                }
                operadores.pop();
                ultimoFueOperador = false;
            } else {
                int prioridad = jerarquia(caracter);

                while (!operadores.isEmpty() && jerarquia(operadores.peek()) >= prioridad) {
                    posfija.append(operadores.pop()).append(" ");
                }
                operadores.push(caracter);
                ultimoFueOperador = true;
            }
        }

        while (!operadores.isEmpty()) {
            posfija.append(operadores.pop()).append(" ");
        }
        return posfija.toString().trim();
    }

    private int jerarquia(char operador) { // metodo para determinar la jerarquia de un operador agrupando los por niveles .
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            case '^':
            //case '√': No funciona como simbolo de raiz F :c
                return 3;
            default:
                return 0;
        }
    }
    //IRD
    private void recorrerInorden(Nodo nodo) {
        if (nodo != null) {
            recorrerInorden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            recorrerInorden(nodo.getHijoDerecho());
        }
    }
    //RID
    private void recorrerPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            recorrerPreorden(nodo.getHijoIzquierdo());
            recorrerPreorden(nodo.getHijoDerecho());
        }
    }
    //IDR
    private void recorrerPosorden(Nodo nodo) {
        if (nodo != null) {
            recorrerPosorden(nodo.getHijoIzquierdo());
            recorrerPosorden(nodo.getHijoDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }
        
    @Override//IRD
    public void mostrarInOrden() {
        recorrerInorden(inicio);
    }

    @Override//RID
    public void mostrarPreOrden() {
        recorrerPreorden(inicio);
    }

    @Override//IDR
    public void mostrarPosOrden() {
        recorrerPosorden(inicio);
    }
    
    public double resolverExpresion() {
        return resolverExpresion(this.inicio);
    }
    
    private double resolverExpresion(Nodo nodo) {
        if (nodo == null)
            return 0;
        if (Character.isDigit(nodo.getDato().charAt(0)) || (nodo.getDato().startsWith("-") && nodo.getDato().length() > 1)) {//startswith para verificar si la expresion incia con -
            return Double.parseDouble(nodo.getDato());
        } else {
            double izquierdo = resolverExpresion(nodo.getHijoIzquierdo());
            double derecho = resolverExpresion(nodo.getHijoDerecho());
            switch (nodo.getDato().charAt(0)) {
                case '+':
                    return izquierdo + derecho;
                case '-':
                    return izquierdo - derecho;
                case '*':
                    return izquierdo * derecho;
                case '/':
                    if (derecho != 0) {
                        return izquierdo / derecho;
                    } else {
                        throw new ArithmeticException("División por cero no permitida");
                    }
                case '^':
                    return Math.pow(izquierdo, derecho);
                default:
                    throw new IllegalArgumentException("Operador no válido: " + nodo.getDato());
            }
        }
    }
}
