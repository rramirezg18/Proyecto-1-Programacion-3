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
        String expresionPosfija = convertirAPosfija(expresionMatematica);//se llama al afuncion convertir a posfija
        Stack<Nodo> pila = new Stack<>();//pila temporal apra almacenar los caracteres
        for (char caracter : expresionPosfija.toCharArray()) {//itera la expresionposfija
            if (Character.isLetterOrDigit(caracter)) { // Verifica si el caracter es un idgito y se añade a la pila
                Nodo nodo = new Nodo(String.valueOf(caracter));
                pila.push(nodo);
            } else {
                Nodo nodoOperador = new Nodo(String.valueOf(caracter)); // operadores aqui se crea un nodo para operador
                Nodo hijoDerecho = pila.pop();//Se retira un nodo y el primero que se retire va hacer el derecho
                Nodo hijoIzquierdo = pila.pop();// el segundo va hacer el nodo izquierdo
                nodoOperador.setHijoIzquierdo(hijoIzquierdo);//aqui se establecen
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
            if (Character.isDigit(caracter)) { //si caracter es digito
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
        while (!operadores.isEmpty()) { // Desapila los operadores restantes y los agrega a la expresión posfija la cul es la inversa polaca
            posfija.append(operadores.pop());
        }
        return posfija.toString(); // Devuelve la expresión posfija como una cadena.
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
        return resolverExpresion(inicio);
    }

    private double resolverExpresion(Nodo nodo) {
        if (nodo == null)
            return 0;

        if (Character.isDigit(nodo.getDato().charAt(0))) { // Si el nodo es un número, lo convierte a double y lo retorna.
            return Double.parseDouble(nodo.getDato());
        } else { // Si el nodo es un numero, realiza la operación correspondiente.
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
