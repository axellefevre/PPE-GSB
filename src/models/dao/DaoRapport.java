
package models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.metier.RapportVisite;

/**
 *
 * @author btssio
 */
public class DaoRapport {
    
    public static void insertRapport(String visiteMatricule, int numeroRapport, int numeroPracticien, String dateRapport, String bilanRapport, String motifRapport) throws SQLException, ClassNotFoundException {
        Connection con = models.Connect.Connection();
        Statement state = con.createStatement();
        state.executeQuery("INSERT INTO RAPPORT_VISITE values ('" + visiteMatricule + "','" + numeroRapport +
                             "','" + numeroPracticien + "','" + dateRapport + "','" + bilanRapport + "','" + motifRapport +"')");
        state.close();
        con.close();
    }
    
    public static void insertOffre(String visiteMatricule, int numeroRapport, String med_depotlegal, int qte) throws SQLException, ClassNotFoundException{
        Connection con = models.Connect.Connection();
        Statement state = con.createStatement();
        state.executeQuery("insert into offrir values('" + visiteMatricule + "','" + numeroRapport + "','" + med_depotlegal + "','" + qte + "')");
        
        state.close();
        con.close();
    }
    
}
