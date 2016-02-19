/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.controller;

/**
 *
 * @author ANDRE
 */
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author ANDRE
 */
public class Primos {

    private int inicio;
    private int fim;

    private List<Integer> pesquisarNumerosPrimos(int faixaInicio, int faixaFim) {
        List<Integer> numerosPrimos = new ArrayList<>();
        for (int numero = faixaInicio; numero <= faixaFim; numero++) {
            for (int i = 2; i <= numero; i++) {
                if (i == numero) {
                    numerosPrimos.add(numero);
                } else if (numero % i == 0) {
                    break;
                }
            }
        }
        return numerosPrimos;
    }

    private void imprimirNumeros(List<Integer> primos) {
        for (Integer primo : primos) {
            System.out.println(String.format("%d", primo.intValue()));
        }
    }

    public static void main(String[] args) {
        Primos primos = new Primos();
        int inicio = 41;
        int fim = 5002;
        List<Integer> listaNumerosPrimos = primos.pesquisarNumerosPrimos(inicio, fim);
        primos.imprimirNumeros(listaNumerosPrimos);
    }
}
