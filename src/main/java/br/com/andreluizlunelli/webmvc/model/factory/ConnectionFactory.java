/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.andreluizlunelli.webmvc.model.factory;

import com.mysql.jdbc.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import org.gjt.mm.mysql.Driver;


/**
 *
 * @author Alunos
 */
public abstract class ConnectionFactory {
    
    public Connection CreateConnection() throws SQLException {
        return (Connection) DriverManager.getConnection(getUrl(), getUsuario(), getSenha());
    }
    
    public abstract String getUrl();
    public abstract String getUsuario();
    public abstract String getSenha();

}
