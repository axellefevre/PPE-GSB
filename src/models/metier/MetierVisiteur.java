
package models.metier;

import java.sql.Date;


public class MetierVisiteur {
    
    private String matricule;
    private String nom;
    private String prenom;
    private String adresse; 
    private String cp;
    private String ville;
    private Date date;   
    private String secCode;
    private String labCode;
    
    public String getMatricule() {
        return matricule;
    }

    public String getNom() {
        return nom;
    }

    public String getPrenom() {
        return prenom;
    }

    public String getAdresse() {
        return adresse;
    }

    public String getCp() {
        return cp;
    }

    public String getVille() {
        return ville;
    }

    public Date getDate() {
        return date;
    }
    
    public String getSecCode() {
        return secCode;
    }
   

    public MetierVisiteur(String matricule, String nom, String prenom, String adresse, String cp, String ville, Date date, String secCode, String labCode) {
        this.matricule = matricule;
        this.nom = nom;
        this.prenom = prenom;
        this.adresse = adresse;
        this.cp = cp;
        this.ville = ville;
        this.date = date;
        this.secCode= secCode;
        this.labCode=labCode;
    }

    public void setMatricule(String matricule) {
        this.matricule = matricule;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public void setPrenom(String prenom) {
        this.prenom = prenom;
    }

    public void setAdresse(String adresse) {
        this.adresse = adresse;
    }

    public void setCp(String cp) {
        this.cp = cp;
    }

    public void setVille(String ville) {
        this.ville = ville;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    public void setSecCode(String secCode) {
        this.secCode = secCode;
    }

    public String getLabCode() {
        return labCode;
    }

    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }
    
    @Override
    public String toString() {
        return  nom+" "+ prenom ;
    }
    
}
