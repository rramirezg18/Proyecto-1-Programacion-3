/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto1programacion3;

/* clase nodo con atributos constructor y metodos setters y getters
 * Roberto Antonio Ramírez Gómez	7690-22-12700
 * Jean Klaus Castañeda Santos		7690-22-892		
 * Jonathan Joel Chan Cuellar		7690-22-1805
 * Bryan Manuel Pineda Orozco           7690-16-8869
 */
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
    //Setters y Getters
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
