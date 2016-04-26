/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Vues;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Anas
 */

public class connection {
    //CTRL + SHIFT + O pour générer les imports
    public static void main(String[] args) {      
        try{
            Class.forName("oracle.jdbc.driver.OracleDriver");
            System.out.println("Driver OK");

            String url = "jdbc:oracle:thin:@172.15.11.102:1521:orcl";
            String user = "ora_2slamppe_eq2";
            String password = "equipe02";

            Connection con = DriverManager.getConnection(url, user, password);
            System.out.println("Connection réussis !");

        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
