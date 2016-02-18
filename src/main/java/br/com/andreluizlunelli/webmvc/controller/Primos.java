/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller;

import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author ANDRE
 */
public class Primos {
    public static void main(String[] args) {
        int[] l = new int[4];
        l[0] = 5;
        l[1] = 7;
        l[2] = 8;
        l[3] = 3;
        Primos primos = new Primos();
        primos.imprimirNumeros(l);
        // try {
        // } catch (PesquisaPrimosInvalida ex) {
        //     Logger.getLogger(Primos.class.getName()).log(Level.SEVERE, null, ex);
        // }
    }
    
    private int inicio;
    private int fim;
    
    private int[] pesquisarNumerosPrimos(int faixaInicio, int faixaFim) {
        return null;
    }
    
    // private void imprimirNumeros(int[] primos) throws PesquisaPrimosInvalida {
    private void imprimirNumeros(int[] primos) {
        if (primos == null && primos.length < 1) {
            return;
        }
        for (int primo : primos) {
            System.out.println(String.format("%i\n", primo));
        }
    }
}
