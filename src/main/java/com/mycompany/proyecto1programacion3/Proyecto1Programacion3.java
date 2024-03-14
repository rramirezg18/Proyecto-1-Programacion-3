/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 */

package com.mycompany.proyecto1programacion3;

/**
 *
 * @author ianto
 */
public class Proyecto1Programacion3 {

    public static void main(String[] args) {
        System.out.println("Hello World!");
    }
    
    private static boolean contieneVariables(String expresion) {
        for (char c : expresion.toCharArray()) {
            if (Character.isLetter(c)) {
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
