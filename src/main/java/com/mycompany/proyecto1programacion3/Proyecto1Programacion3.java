/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1programacion3;

/**
 *
 * @author ianto
 */
import java.util.*;
public class Proyecto1Programacion3 {

    public static void main(String[] args) {
        Scanner entrada = new Scanner(System.in);
        int opcion;
        String expresionMatematica = "";
        ConstruirArbolExpresion arbol = new ConstruirArbolExpresion();
        int salir = 0;
        while(salir !=1) {
            Menu();
            opcion = entrada.nextInt();
            switch (opcion) {
                case 1:
                    //aca solicitara la expresion
                    //ArbolExpresion arbol = new ArbolExpresion();
                    entrada.nextLine();
                    System.out.println("Ingrese una expresión matemática");
                    expresionMatematica =  entrada.nextLine();//"5*6-4+8*3^(4-2)*√3";
                    // Verifica si la expresión contiene variables(letras a,b,c, etc.
                    while(!validarExpresionMatematica(expresionMatematica)) {
                        System.out.println("¡¡¡La expresión matemática ingresada contiene caracteres no validos!!!");
                        System.out.println("Ingresa una expresión matemática valida");
                        expresionMatematica = entrada.nextLine();
                    }
                    if (sonVariables(expresionMatematica)) {
                        System.out.println("Por favor, ingrese valores constantes para las variables:");
                        for (char variable : obtenerVariables(expresionMatematica)) {
                            System.out.print("Ingrese el valor numerico para la variable " + variable + ": ");
                            int valor = entrada.nextInt();
                            expresionMatematica = expresionMatematica.replace(String.valueOf(variable), String.valueOf(valor));
                        }
                        System.out.println("La expresión matemática con valores constantes es: " + expresionMatematica);
                    }
                    break;
                case 2:
                    //aca solo generara el arbol grafico
                    arbol.construirArbol(expresionMatematica);
                    ArbolGrafico.mostrarArbolExpresion(arbol);
                    System.out.println("Se ha generado el árbol de expresión.");
                    break;
                case 3:
                    arbol.construirArbol(expresionMatematica);
                    System.out.println("La expresion matemática con valores constantes es: " + expresionMatematica);
                    System.out.println("\n");
                    System.out.println("Inorden:");
                    arbol.mostrarInOrden();
                    System.out.println();
                    System.out.println("Preorden:");
                    arbol.mostrarPreOrden();
                    System.out.println();
                    System.out.println("Posorden:");
                    arbol.mostrarPosOrden();
                    System.out.println();
                    //aca muestra los recorridos
                    arbol.construirArbol(expresionMatematica);
                    double resultado = arbol.resolverExpresion();
                    System.out.println("El resultado es: " + resultado);
                    break;
                case 4:
                    salir = 1;
            }
        }
    }
    public static void Menu() {
        System.out.println("\n\t MENU\n");
        System.out.println("1.\t Ingresar expresión matemática");
        System.out.println("2.\t Generar árbol de expresión");
        System.out.println("3.\t Recorridos ");
        System.out.println("4.\t Salir");
        System.out.println("\nSeleccione una opción: ");
    }

    
    private static boolean sonVariables(String expresion) {
        for (char caracter : expresion.toCharArray()) {
            if (Character.isLetter(caracter)) {
                return true;
            }
        }
        return false;
    }
    // Método para obtener todas las variables de la expresión
    private static char[] obtenerVariables(String expresion) {
        String variables = expresion.replaceAll("[^a-z]", "");
        return variables.toCharArray();
    }
    
    private static boolean validarExpresionMatematica(String expresionMatematica) {
        String caracteresValidos = "+-*/^(){}[]abcdefghijklmnopqrstuvwxyz0123456789";
        for(char caracter : expresionMatematica.toCharArray()) {
            if(caracteresValidos.indexOf(caracter) == -1) {
                return false;
            }
        }
        return true;
    }
}
