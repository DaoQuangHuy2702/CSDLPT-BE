/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package context;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author LENOVO
 */
public class DBConnect {
    private final String serverName = "DESKTOP-4M2TT6P";
    private final String portNumber = "1433";
    private final String instance = "TEST_1";
    private final String dbName = "DKTC";
    private final String userId = "sa";
    private final String password = "123456";
    
    public Connection getConnection() throws Exception {
        String url = "jdbc:sqlserver://" + serverName + "\\"
                + instance + ";databaseName=" + dbName;
        
        if(instance == null || instance.trim().isEmpty()) {
            url = "jdbc:sqlserver://" + serverName + ":" + portNumber + ";databaseName=" + dbName;
        }
        
        Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
        
        return DriverManager.getConnection(url, userId, password);
    }
}
