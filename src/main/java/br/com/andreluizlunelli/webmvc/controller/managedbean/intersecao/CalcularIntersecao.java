/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller.managedbean.intersecao;

/**
 *
 * @author ANDRE
 */
public class CalcularIntersecao {
    public static boolean calcular(int[] valores) throws MatrizInvalida {
        if (valores.length != 4) {
            throw new MatrizInvalida();
        }
        int val11 = valores[0];
        int val12 = valores[1];
        int val21 = valores[2];
        int val22 = valores[3];                
        if ((val21 >= val11) && (val21 <= val12)) {
            return true;
        } else if ((val22 >= val11) && (val22 <= val12)) {
            return true;
        } else {
            return false;
        }
    }
}
