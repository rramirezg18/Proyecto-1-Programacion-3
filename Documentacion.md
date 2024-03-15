# PROYECTO 1 - PROGRAMACION III
**Proyecto desarrollado por:** 

Roberto Antonio Ramirez Gomez    7690-22-12700

Jean Klaus Castañeda Santos      7690-22-892

Jonathan Joel Chan Cuellar       7690-22-1805
___
[ENLACE DEL REPOSITORIO EN GITHUB](https://github.com/rramirezg18/Proyecto-1-Programacion-3 "REPOSITORIO DEL PROYECTO")
___

## Descripcion Del Proyecto:
   
El presente proyecto tiene consiste en realizar un **algoritmo** utilizando el lenguaje de programacion *Java* el cual debera solicitar una expresion matematica que puede incluir *sumas, restas, multiplicaciones, divisiones, prencias y raices* **a+b-(c-b)+e** y crear el arbol de expresion. Debera mostrar los recorridos del arbol y el evaluar el reccorrido postorden como notacion polaca. 

**El proyecto incluira las siguientes clases**
* Proyecto1_Expresion.java
* Nodo.java
* ConstruccionArbol.java
* Grafica

Y con la interface **Metodos**


Para el desarrollo del proyecto crearemos una clase llamada **Nodo** la cual incluira los siguientes atributosn y variables del mismo tipo de la clase:
*String* dato es el dato se ingresara para cada nodo o sub arbol en el arbol de expresion
*hijoDerecho e HijoIzquierdo funcionarán como apuntadores en el árbol de expresión

```java
public class Nodo {
	private String dato;
	private Nodo hijoDerecho;
	private Nodo hijoIzquierdo;
	
	//Constructor
	public Nodo (String dato) {
		this.dato = dato;
		this.hijoDerecho = null;
		this.hijoIzquierdo = null;
	}
```
De igual forma los metodos **setter y getters** para obtener y asignar o cambiar el dato de las variables:
```java
	public String getDato() {
		return dato;
	}

	public void setDato(String dato) {
		this.dato = dato;
	}

	public Nodo getHijoDerecho() {
		return hijoDerecho;
	}

	public void setHijoDerecho(Nodo hijoDerecho) {
		this.hijoDerecho = hijoDerecho;
	}

	public Nodo getHijoIzquierdo() {
		return hijoIzquierdo;
	}

	public void setHijoIzquierdo(Nodo hijoIzquierdo) {
		this.hijoIzquierdo = hijoIzquierdo;
	}

}
```

Como patron de diceño implementamos una interfaz **Metodos** para organizar los metodos y luego implementarlos en la clase ConstruirArbolExpresion

```java
public interface Metodos {
    
    void mostrarInOrden();
    void mostrarPreOrden();
    void mostrarPosOrden();
    
}
```

En la clase **ConstruirArbolExpresion** se implementó la interfaz metodos. Esta clase es la que contiene la lógica principal del proyecto en la cual se implementan métodos para la construcción del arbol de expresion.

Para la solución se utilizara la logica de **pilas** y para ello es primario importar la libreria:
```java
import java.util.Stack;
```

Creamos una variable **inicio** de tipo de la clase **Nodo** la cual utilizaremos para representar la raíz del árbol y un constructor para darle valor a la variable.


```java
public class ConstruirArbolExpresion implements Metodos {
    
    public Nodo inicio; //variable de tipo nodo que representara la raiz del árbol
	
	public ConstruirArbolExpresion() {
		this.inicio = null;
	}
```

utilizamos una funcion con un paramento *char* para determinar la jerarquia de los operadores matemáticos para ello utilizamos un switch y parametro *operador* y cada uno de sus casos representa la jerarquia de los operadores

```java
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
```

Creamos una función para convertir la expresión a posfija o notación polaca a la cual se le pasa como parametro un *String* en este caso la expresión matemática
Utilizaremos de la clase estandar StringBuilder una variable **posfija** para poder obtener la cadena inversa polaca
En una pila de caracteres se almacenaran los operadores matemáticos dela expresión
Utilizaremos un ciclo **for**  para recorrer la expresión matemática caracter por caracter y cada que encuentre un caracter de tipo numero lo agrega a la variable **posfija** y cada que encuentre un operador matemático lo agregará a la pila de operadores segun la jerarquia establecida en la función **jerarquia**

```java
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
```

La función **construirArbol** y paramento *String expresionMatematica* creamos una variable String y utilizando la funcion convertirAPosfija se realiza el procedimiento para convertirla 
Utilizando una pila de tipo **Nodo** para guardar la expresion posfija y un ciclo for para recorrer la expresion caracter por caracter e ir agregando cada caracter a la pila.

```java
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
```

funcion para mostrar en pantalla el recorrido **INORDEN**
```java
private void mostrarInorden(Nodo nodo) {
        if (nodo != null) {
            mostrarInorden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            mostrarInorden(nodo.getHijoDerecho());
        }
    }
```

funcion para mostrar en pantalla el recorrido **PREORDEN**
```java
private void mostrarPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            mostrarPreorden(nodo.getHijoIzquierdo());
            mostrarPreorden(nodo.getHijoDerecho());
        }
    }
```

funcion para mostrar en pantalla el recorrido **POSORDEN**
```java
    private void mostrarPosorden(Nodo nodo) {
        if (nodo != null) {
            mostrarPosorden(nodo.getHijoIzquierdo());
            mostrarPosorden(nodo.getHijoDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }  
```


Metodos de la interfaz para mostrar los el resultado de las funciones en pantalla

```java

```

En la clase **ArbolGrafico** Para poder realizar el árbol en forma gráfica utilizamos las librerias que nos permiten tener una interfaz gráfica en Java
```java
import java.awt.*;
import javax.swing.*;
```



![Imagen](imagen1.jpeg)
![Imagen](imagen2.jpeg)
