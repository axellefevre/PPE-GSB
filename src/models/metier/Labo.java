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
public class Labo {
    private String labCode;
    private String labName;
    private String labChefVente;

    public Labo(String labCode, String labName, String labChefVente) {
        this.labCode = labCode;
        this.labName = labName;
        this.labChefVente = labChefVente;
    }

    public String getLabCode() {
        return labCode;
    }

    public void setLabCode(String labCode) {
        this.labCode = labCode;
    }

    public String getLabName() {
        return labName;
    }

    public void setLabName(String labName) {
        this.labName = labName;
    }

    public String getLabChefVente() {
        return labChefVente;
    }

    public void setLabChefVente(String labChefVente) {
        this.labChefVente = labChefVente;
    }

    @Override
    public String toString() {
        return  labName;
    }
    
    
}
