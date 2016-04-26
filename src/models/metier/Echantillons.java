/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.metier;

/**
 *
 * @author btssio
 */
public class Echantillons {
    String VIS_MATRICULE;
    int RAP_NUM;
    String MED_DEPOTLEGAL;
    int OFF_QTE;

    
    
    //constructeur
    public Echantillons(String VIS_MATRICULE, int RAP_NUM, String MED_DEPOTLEGAL, int OFF_QTE) {
        this.VIS_MATRICULE = VIS_MATRICULE;
        this.RAP_NUM = RAP_NUM;
        this.MED_DEPOTLEGAL = MED_DEPOTLEGAL;
        this.OFF_QTE = OFF_QTE;
    }
    
    
    
    
    //getters
    public String getVIS_MATRICULE() {
        return VIS_MATRICULE;
    }

    public int getRAP_NUM() {
        return RAP_NUM;
    }

    public String getMED_DEPOTLEGAL() {
        return MED_DEPOTLEGAL;
    }

    public int getOFF_QTE() {
        return OFF_QTE;
    }

    
    
    //setters
    public void setVIS_MATRICULE(String VIS_MATRICULE) {
        this.VIS_MATRICULE = VIS_MATRICULE;
    }

    public void setRAP_NUM(int RAP_NUM) {
        this.RAP_NUM = RAP_NUM;
    }

    public void setMED_DEPOTLEGAL(String MED_DEPOTLEGAL) {
        this.MED_DEPOTLEGAL = MED_DEPOTLEGAL;
    }

    public void setOFF_QTE(int OFF_QTE) {
        this.OFF_QTE = OFF_QTE;
    }
    
    
    
    
    
}
