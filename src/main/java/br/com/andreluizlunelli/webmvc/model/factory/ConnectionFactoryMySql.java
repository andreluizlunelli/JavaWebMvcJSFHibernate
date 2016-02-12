/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.factory;

/**
 *
 * @author Alunos
 */
public class ConnectionFactoryMySql extends ConnectionFactory{

    @Override
    public String getSenha() {
        return "root";
    }

    @Override
    public String getUrl() {
       return "jdbc:mysql://localhost/transpobrasil";
    }

    @Override
    public String getUsuario() {
        return "root";
    }
    
}
