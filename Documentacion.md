# `PROYECTO 1 - PROGRAMACION III`
**Proyecto desarrollado por:** 

Roberto Antonio Ramirez Gomez    7690-22-12700

Jean Klaus Castañeda Santos      7690-22-892

Jonathan Joel Chan Cuellar       7690-22-1805
___
[**ENLACE DEL REPOSITORIO EN GITHUB**](https://github.com/rramirezg18/Proyecto-1-Programacion-3 "REPOSITORIO DEL PROYECTO")
___

## Descripcion Del Proyecto:
   
El presente proyecto tiene consiste en realizar un **algoritmo** utilizando el lenguaje de programación *Java* el cual debera solicitar una expresion matematica que puede incluir *sumas, restas, multiplicaciones, divisiones, prencias y raices* **a+b-(c-b)+e** y crear el árbol de expresión. Deberá mostrar los recorridos del órbol y el evaluar el reccorrido postorden como notación polaca es decir resolver la expresión matematica ingresada. 

**El proyecto incluira las siguientes clases**
* Proyecto1_Expresion.java
* Nodo.java
* ConstruccionArbol.java
* Grafica
* Interface **Metodos**


Para el desarrollo del proyecto crearemos una clase llamada **Nodo** la cual incluira los siguientes atributos y variables del mismo tipo de la clase:
*String dato es el dato se ingresara para cada nodo o sub arbol en el arbol de expresion
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
//IRD
    private void recorrerInorden(Nodo nodo) {
        if (nodo != null) {
            recorrerInorden(nodo.getHijoIzquierdo());
            System.out.print(nodo.getDato() + " ");
            recorrerInorden(nodo.getHijoDerecho());
        }
    }
```

funcion para mostrar en pantalla el recorrido **PREORDEN**
```java
  //RID
    private void recorrerPreorden(Nodo nodo) {
        if (nodo != null) {
            System.out.print(nodo.getDato() + " ");
            recorrerPreorden(nodo.getHijoIzquierdo());
            recorrerPreorden(nodo.getHijoDerecho());
        }
    }
```


funcion para mostrar en pantalla el recorrido **POSORDEN**
```java
//IDR
    private void recorrerPosorden(Nodo nodo) {
        if (nodo != null) {
            recorrerPosorden(nodo.getHijoIzquierdo());
            recorrerPosorden(nodo.getHijoDerecho());
            System.out.print(nodo.getDato() + " ");
        }
    }
```


Metodos de la interfaz para mostrar los el resultado de las funciones en pantalla

```java
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
```

Creamos una funcion de tipo *double* para resolver la expresion matematica.
```java
    private double resolverExpresion(Nodo nodo) {
        if (nodo == null)
            return 0;

        if (Character.isDigit(nodo.getDato().charAt(0))) { // Si el nodo es un número, lo convierte a double y lo retorna.
            return Double.parseDouble(nodo.getDato());
        } else { // Si el nodo es un operador, realiza la operación correspondiente.
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
```

Creamos una clase **ArbolGrafico** Para poder realizar el árbol en forma gráfica utilizamos las librerias que nos permiten tener una interfaz gráfica en Java
```java
import java.awt.*;
import javax.swing.*;
```

En la clase **main** del proyecto creamos se solicita al usuario que ingrese una expresión mátematica y para poder continuar con el proceso primero se verifica que
la expresión sea valida, utilizamos la función de tipo *boolean validarExpresionMatematica* la cual retornara **true** si la expresión ingresada es correcta.
```java
private static boolean validarExpresionMatematica(String expresionMatematica) {
        String caracteresValidos = "+-*/^(){}[]abcdefghijklmnopqrstuvwxyz0123456789";
        for(char caracter : expresionMatematica.toCharArray()) {
            if(caracteresValidos.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }
```

Para seguier con el proceso se identifica si la expresión ingresada contiene variables, por ejemplo: 2+a+3 -> la letra **a** es una variable al identificarla procede a pedir al usuario que ingrese su valor númerico o constante, para ello utilizamos la siguiente funciónes donde se identificán las variables y donde se le da valor a la variable.
```java
    private static boolean sonVariables(String expresion) {
        for (char caracter : expresion.toCharArray()) {
            if (Character.isLetter(caracter)) {
                return true;
            }
        }
        return false;
    }

    private static char[] obtenerVariables(String expresion) {
        String variables = expresion.replaceAll("[^a-z]", "");
        return variables.toCharArray();
    }
```
### Resultados Del Algoritmo
Al compilar el algoritmo nos muestra un menu con las opciones:
1. Ingresar Expresion Matematica
2. Generar Arbol De Expresion
3. Recorridos
4. Salir

**Primero** se ingresa la expresion puede ser solo con numeros o con variables que posteriormente solicitara el valor constante. **Segundo** igresar la opcion 2 para generar el arbol y por ultimo ingresar a la opcion 3 para ver los recorridos y el resultado de la expresion matematica ingresada.

![Imagen](imagen1.png)
![Imagen](imagen2.png)
