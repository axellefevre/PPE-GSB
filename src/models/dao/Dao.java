package models.dao;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import models.metier.Echantillons;
import models.metier.Labo;
import models.metier.Medicaments;
import models.metier.LieuExercice;
import models.metier.MetierPracticiens;
import models.metier.MetierVisiteur;
import models.metier.RapportVisite;
import models.metier.Secteur;

public class Dao {

    public static List<MetierVisiteur> getAll() throws SQLException, ClassNotFoundException {         
        List<MetierVisiteur> lesVisiteurs = new ArrayList<MetierVisiteur>(); 
        MetierVisiteur unVisiteur;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM visiteur");    

        while (res.next()) {
            String matricule = res.getString("VIS_MATRICULE");
            String nom = res.getString("VIS_NOM");
            String prenom = res.getString("Vis_PRENOM");
            String adresse = res.getString("VIS_ADRESSE");
            String cp = res.getString("VIS_CP");;
            String ville = res.getString("VIS_VILLE");
            Date date = res.getDate("VIS_DATEEMBAUCHE");
            String secCode = res.getString("SEC_CODE");
            String labCode = res.getString("LAB_CODE");
            unVisiteur = new MetierVisiteur(matricule, nom, prenom, adresse, cp, ville, date, secCode, labCode);
            lesVisiteurs.add(unVisiteur);
        }
        
        return lesVisiteurs;
    }
  
    public static List<MetierPracticiens> getAllPracticiens() throws SQLException, ClassNotFoundException {         
        List<MetierPracticiens> lesPracticiens = new ArrayList<MetierPracticiens>(); 
        MetierPracticiens unPracticien;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM PRATICIEN");
        
        while (res.next()) {
            String pra_num = res.getString("PRA_NUM");
            String pra_nom = res.getString("PRA_NOM");
            String pra_prenom = res.getString("PRA_PRENOM");
            String pra_adresse = res.getString("PRA_ADRESSE");
            String pra_cp = res.getString("PRA_CP");
            String pra_ville = res.getString("PRA_VILLE");
            String pra_coefnotoriete = res.getString("PRA_COEFNOTORIETE");
            String typ_code = res.getString("TYP_CODE");
            unPracticien = new MetierPracticiens(pra_num, pra_nom, pra_prenom, pra_adresse, pra_cp, pra_ville, pra_coefnotoriete, typ_code);
            lesPracticiens.add(unPracticien);
        }
        
        return lesPracticiens;
    }
    
    public static List<Medicaments> getAllMedic() throws SQLException,ClassNotFoundException{
        List<Medicaments> lesMedicaments = new ArrayList<Medicaments>(); 
        Medicaments unMedoc;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM MEDICAMENT");
        
        while (res.next()) {
            String depotLegal = res.getString("MED_DEPOTLEGAL");
            String nomCommercial = res.getString("MED_NOMCOMMERCIAL");
            String fam_code = res.getString("FAM_CODE");
            String composition = res.getString("MED_COMPOSITION");
            String effect = res.getString("MED_EFFETS");
            String contreIndic = res.getString("MED_CONTREINDIC");
            String prixEchantillon = res.getString("MED_PRIXECHANTILLON");
            unMedoc = new Medicaments(depotLegal,nomCommercial,fam_code,composition,effect,contreIndic,prixEchantillon);
            lesMedicaments.add(unMedoc);
        }
    
        return lesMedicaments;
    }
    
    
    public static List<Labo> getAllLab() throws SQLException, ClassNotFoundException {         
        List<Labo> lesLabos = new ArrayList<Labo>(); 
        Labo unLabo;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM LABO");    

        while (res.next()) {
            String labCode = res.getString("LAB_CODE");
            String labNom = res.getString("LAB_NOM");
            String labChefVente = res.getString("LAB_CHEFVENTE");
            
            unLabo = new Labo(labCode, labNom, labChefVente);
            lesLabos.add(unLabo);
           
        }
        return lesLabos;
    }
  
    public static List<Secteur> getAllSecteurs() throws SQLException, ClassNotFoundException {         
        List<Secteur> lesSecteurs = new ArrayList<Secteur>(); 
        Secteur unSecteur;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();
      
        ResultSet res = state.executeQuery("SELECT * FROM SECTEUR");    
      
        while (res.next()) {
            String codeSecteur = res.getString("SEC_CODE");
            String libelleSecteur = res.getString("SEC_LIBELLE");
            
            
            unSecteur = new Secteur(codeSecteur, libelleSecteur);
            lesSecteurs.add(unSecteur);
           
        }
        return lesSecteurs;
    }
    
    public static List<LieuExercice> getAllLieuExercice() throws SQLException, ClassNotFoundException {         
        List<LieuExercice> lesLieus = new ArrayList<LieuExercice>(); 
        LieuExercice unLieu;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM TYPE_PRATICIEN");    

        while (res.next()) {
            String typ_code = res.getString("TYP_CODE");
            String typ_libelle = res.getString("TYP_LIBELLE");
            String typ_lieu = res.getString("TYP_LIEU");
            
            unLieu = new LieuExercice(typ_code, typ_libelle, typ_lieu);
            lesLieus.add(unLieu);
           
        }
        return lesLieus;
    }
    
    public static String getMatricule(String login)throws SQLException, ClassNotFoundException {
        String matri=null;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT VIS_MATRICULE FROM VISITEUR where VIS_NOM = '" + login + "'");
        
        while(res.next()){
            matri=res.getString("VIS_MATRICULE");
        }
        return matri;
    }
    
    
    public static MetierVisiteur getConnection(String login, String mdp) throws SQLException, ClassNotFoundException {
        
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();
        
        MetierVisiteur unVisiteur = null;
        
        ResultSet res = state.executeQuery("SELECT * FROM VISITEUR WHERE VIS_NOM ='" + login + "' AND VIS_MATRICULE = '" + mdp + "'");
        
        if (res.next()) {
            String matricule = res.getString("VIS_MATRICULE");
            String nom = res.getString("VIS_NOM");
            String prenom = res.getString("Vis_PRENOM");
            String adresse = res.getString("VIS_ADRESSE");
            String cp = res.getString("VIS_CP");;
            String ville = res.getString("VIS_VILLE");
            Date date = res.getDate("VIS_DATEEMBAUCHE");
            String secCode = res.getString("SEC_CODE");
            String labCode = res.getString("LAB_CODE");
            unVisiteur = new MetierVisiteur(matricule, nom, prenom, adresse, cp, ville, date, secCode, labCode);
        } else {
            
        }
        return unVisiteur;
    }
    
    public static List<RapportVisite> getRapportVisiteWithVisiteur(MetierVisiteur myVisiteur) throws SQLException, ClassNotFoundException {
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();
        
        List<RapportVisite> myListRappVisite = null;
        
        ResultSet res = state.executeQuery("SELECT * FROM RAPPORT_VISITE WHERE VIS_MATRICULE ='" + myVisiteur.getMatricule() + "'");
        
        while(res.next()){
            String visMatricule = res.getString("VIS_MATRICULE");
            String rapNum = res.getString("RAP_NUM");
            String praNum = res.getString("PRA_NUM");
            Date rapDate = res.getDate("RAP_DATE");
            String rapBilan = res.getString("RAP_BILAN");
            String rapMotif = res.getString("RAP_MOTIF");
            RapportVisite myRapport= new RapportVisite(visMatricule, rapNum, praNum, rapDate, rapBilan, rapMotif);
            myListRappVisite.add(myRapport);
        }
        
    return myListRappVisite;
    }
  
    public static List<RapportVisite> getAllRapports() throws SQLException, ClassNotFoundException {         
        List<RapportVisite> lesRapports = new ArrayList<RapportVisite>(); 
        RapportVisite unRapport;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();

        ResultSet res = state.executeQuery("SELECT * FROM RAPPORT_VISITE order by RAP_NUM ");    

        while (res.next()) {
            String visMatricule = res.getString("VIS_MATRICULE");
            String rapNum = res.getString("RAP_NUM");
            String praNum = res.getString("PRA_NUM");
            Date rapDate = res.getDate("RAP_DATE");
            String rapBilan = res.getString("RAP_BILAN");
            String rapMotif = res.getString("RAP_MOTIF");
            
            unRapport= new RapportVisite(visMatricule, rapNum, praNum, rapDate, rapBilan, rapMotif);
            lesRapports.add(unRapport);
           
        }
        return lesRapports;
    }
    
    
    public static List<Echantillons> getAllEchantillon() throws SQLException, ClassNotFoundException {
        List<Echantillons> lesEchantillons = new ArrayList<Echantillons>(); 
        Echantillons unEchantillon;
        Connection con = models.Connect.Connection();      
        Statement state = con.createStatement();
        
        ResultSet res = state.executeQuery("Select * from OFFRIR");
        
        while(res.next()){
            String VIS_MATRICULE = res.getString("VIS_MATRICULE");
            int RAP_NUM = res.getInt("RAP_NUM");
            String MED_DEPOTLEGAL = res.getString("MED_DEPOTLEGAL");
            int OFF_QTE = res.getInt("OFF_QTE");
            unEchantillon = new Echantillons(VIS_MATRICULE, RAP_NUM, MED_DEPOTLEGAL, OFF_QTE);
            lesEchantillons.add(unEchantillon);
            
        }
        
        return lesEchantillons;
    }
    
    public static String getMedicamentRapport(String index) throws SQLException, ClassNotFoundException{
        String nom = null;
        Connection con = models.Connect.Connection();
        Statement state = con.createStatement();
        
        ResultSet res = state.executeQuery("select Offrir.RAP_NUM,Medicament.MED_DEPOTLEGAL,Medicament.MED_NOMCOMMERCIAL from Offrir inner join Medicament on Offrir.MED_DEPOTLEGAL=Medicament.MED_DEPOTLEGAL where RAP_NUM ="+index);
        while(res.next()){
            nom = res.getString("Medicament.MED_NOMCOMMERCIAL");
        }
        return nom;
    }
    
    public static int getLastRNb() throws SQLException,ClassNotFoundException{
        int number = 0 ;
        Connection con = models.Connect.Connection();
        Statement state = con.createStatement();
        
        ResultSet res = state.executeQuery("select MAX(RAP_NUM)  from rapport_visite");
        while(res.next()){
            number = res.getInt("MAX(RAP_NUM)");
        }
        
        return number;
    }
    
    
}
