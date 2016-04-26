/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package models.metier;

/**
 * @author Anas
 */
public class Medicaments {
    
    private String depotLegal;
    private String nomCommercial;
    private String fam_code;
    private String composition;
    private String effect;
    private String contreIndic;
    private String prixEchantillon;

    
    //constructor
    public Medicaments(String depotLegal, String nomCommercial, String fam_code, String composition, String effect, String contreIndic, String prixEchantillon) {
        this.depotLegal = depotLegal;
        this.nomCommercial = nomCommercial;
        this.fam_code = fam_code;
        this.composition = composition;
        this.effect = effect;
        this.contreIndic = contreIndic;
        this.prixEchantillon = prixEchantillon;
    }

    //getters
    public String getDepotLegal() {
        return depotLegal;
    }

    public String getNomCommercial() {
        return nomCommercial;
    }

    public String getFam_code() {
        return fam_code;
    }

    public String getComposition() {
        return composition;
    }

    public String getEffect() {
        return effect;
    }

    public String getContreIndic() {
        return contreIndic;
    }

    public String getPrixEchantillon() {
        return prixEchantillon;
    }

    
    //setters
    public void setDepotLegal(String depotLegal) {
        this.depotLegal = depotLegal;
    }

    public void setNomCommercial(String nomCommercial) {
        this.nomCommercial = nomCommercial;
    }

    public void setFam_code(String fam_code) {
        this.fam_code = fam_code;
    }

    public void setComposition(String composition) {
        this.composition = composition;
    }

    public void setEffect(String effect) {
        this.effect = effect;
    }

    public void setContreIndic(String contreIndic) {
        this.contreIndic = contreIndic;
    }

    public void setPrixEchantillon(String prixEchantillon) {
        this.prixEchantillon = prixEchantillon;
    }

    @Override
    public String toString() {
        return nomCommercial;
    }
    
    
    
    
    
}
