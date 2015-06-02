/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package persistence;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author daniel
 */
public class ConnectionFactory {
    
    static final String DATABASE_URL = "jdbc:postgresql://localhost:5432/ERPArrayEnterprises";
    
    public static Connection getConexao(){
        Connection conn = null;
        
        try {
            Class.forName("org.postgresql.Driver");
            conn = DriverManager.getConnection(DATABASE_URL,"postgres","root");
        } catch (SQLException ex) {
            System.out.println("Falha na Conexão");
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ConnectionFactory.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return conn;
    }
}
