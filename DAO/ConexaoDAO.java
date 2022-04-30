package DAO;


import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

import javax.swing.JOptionPane;


public class ConexaoDAO {
    
    //Método de modo visibilidade público, tipo Connection que precisa retorno tipo Connection
    public Connection conectaBD(){
        Connection conn = null; 
        
        try {
            String url = "jdbc:mysql://localhost:3306/banco_teste?user=root&password=";
            conn = DriverManager.getConnection(url);
        } catch (SQLException erro) {
            JOptionPane.showMessageDialog(null, "erro em conexaoDAO" + erro.getMessage());
        }
        return conn;
    }
    
}
