
package controllers;

import Vues.Menu;
import Vues.practiciens;
import Vues.rapportVisite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.Dao;
import models.dao.DaoRapport;
import models.metier.Echantillons;
import models.metier.LieuExercice;
import models.metier.Medicaments;
import models.metier.MetierPracticiens;
import models.metier.MetierVisiteur;
import models.metier.RapportVisite;

/**
 *
 * @author btssio
 */
public class ControllersRapportVisite implements ActionListener {
    private rapportVisite vue;
    private List<MetierPracticiens> lesPracticiens;
    private List<Medicaments> lesMedicaments;
    private List<RapportVisite> lesRapports;
    private List<Echantillons> lesEchantillons;
    private List<String> lesMeds;
    private List<Integer> nbOffre;
    private Echantillons unEchantillon;
    private RapportVisite unPracticien;
    private MetierVisiteur unVisiteur;
    private List<LieuExercice> lesLieus;
    private practiciens vuePracticiens = new practiciens();
    private String login;
    private int num;
    private int qte = 0;
    private int count = 0;
    private int countMed = 0;
    private int countOffre = 0;
    private boolean nouveau = false;
    private DateFormat dateFormat = new SimpleDateFormat("dd/MM/yyyy");
    
    public ControllersRapportVisite(rapportVisite vue, String login) {
        this.vue=vue;
        this.login = login;
        afficherLesPraticiens();
        vue.getjButtonNouveau().addActionListener(this);
        vue.getjButtonDetails().addActionListener(this);
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
        vuePracticiens.getjButtonFermer().addActionListener(this);
    }
    
    public final void afficherLesPraticiens() {
        try {
            lesPracticiens = Dao.getAllPracticiens();
            for (MetierPracticiens unPracticien : lesPracticiens) {
                vue.getModeleListePracticien().addElement(unPracticien);
            }
            lesMedicaments = Dao.getAllMedic();
            for (Medicaments unmedoc : lesMedicaments){
                vue.getModelListeMedoc().addElement(unmedoc.toString());
            }
            lesLieus = Dao.getAllLieuExercice();
            for (LieuExercice unLieu : lesLieus) {
                vuePracticiens.getjComboBoxLieuExercice().addItem(unLieu);
            }
            lesRapports = Dao.getAllRapports();
            lesEchantillons = Dao.getAllEchantillon();
            num = Dao.getLastRNb();
            setVue(count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }
    }
    
    @Override
    public void actionPerformed(ActionEvent e) {
        try{
            Object source =e.getSource();
            if (source == vue.getjButtonDetails()){
                vue.setVisible(false);
                vuePracticiens.setVisible(true);
                SetVuesPrac(vue.getjComboBoxPracticien().getSelectedIndex());
            }

            if (source == vue.getjButtonFermer()){
                vue.setVisible(false);
                Menu vue = new Menu();
                ControllerMenu controllers = new ControllerMenu(vue, login);
                vue.setVisible(true);
            }

            if (source == vue.getjButtonSuivant()){
                if(count < lesRapports.size()-1){
                    lesMeds.clear();
                    count += 1;
                    setVue(count);
                }
            }
            
            if (source==vue.getjButtonPrecedent()){
                if(count > 0){
                    lesMeds.clear();
                    count -= 1;
                    setVue(count);
                }
            }

            if (source == vuePracticiens.getjButtonFermer()){
                vuePracticiens.setVisible(false);
                vue.setVisible(true);
            }
            
            if (source == vue.getjButtonNouveau()) {
                if (nouveau){
                    String matri=Dao.getMatricule(login);
                    int numPraticient = (vue.getjComboBoxPracticien().getSelectedIndex())+1;
                    int numRapport = num + 1;
                    String modifVisite = vue.getjTextFieldModifVisite().getText();
                    String date = dateFormat.format(vue.getjDateChooser1().getDate()).toString();
                    String bilan = vue.getjTextAreaBilan().getText();
                    DaoRapport.insertRapport(matri, numRapport,numPraticient, date, bilan,modifVisite);
                    for(int i=0;i<5;i++){
                        String depotLegal = getDepot(vue.getjTable1().getValueAt(i, 0).toString());
                        String quantity = vue.getjTable1().getValueAt(i, 1).toString().trim();
                        if(quantity != ""){
                            qte = Integer.parseInt(quantity);
                        }
                        if(depotLegal != null){
                            DaoRapport.insertOffre(matri, numRapport, depotLegal, qte);
                        }
                    }
                    vue.getjButtonNouveau().setLabel("Nouveau");
                    lesRapports = Dao.getAllRapports();
                    nouveau=false;
                }else{
                    vue.getjButtonNouveau().setLabel("Valider");
                    ClearVue();
                    vue.getjTextFieldNumRapport().setText(Integer.toString(num+1));
                    nouveau=true;
                }
               
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllersMedicaments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllersMedicaments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    
    void ClearVue(){
        vue.getjTextFieldNumRapport().setText("");
        vue.getjTextFieldModifVisite().setText("");
        vue.getjTextAreaBilan().setText("");
        vue.getjDateChooser1().setDate(null);
        for(int i = 0 ; i < 5 ; i++){
            vue.getjTable1().setValueAt("", i, 0);
            vue.getjTable1().setValueAt("", i, 1);
        }
    }
    
    
    public MetierVisiteur setVisiteurWithFromAccueil(MetierVisiteur myVisiteur){
        unVisiteur=myVisiteur;
        return unVisiteur;
    }        
    
    void setVue(int index) throws SQLException, ClassNotFoundException{
        RapportVisite unRapport = lesRapports.get(index);
        vue.getjTextFieldNumRapport().setText(unRapport.getNumeroRapport());
        vue.getjDateChooser1().setDate(unRapport.getDateRapport());
        vue.getjTextFieldModifVisite().setText(unRapport.getMotifRapport());
        vue.getjTextAreaBilan().setText(unRapport.getBilanRapport());
        vue.getjComboBoxPracticien().setSelectedIndex(Integer.parseInt(unRapport.getNumeroPracticien())-1);
        int num = Integer.parseInt(unRapport.getNumeroRapport());
        countMed = 0 ;
        countOffre = 0 ;
        lesMeds = getMedic(num);
        
        for(int i = 0 ; i < 5 ; i++){
            vue.getjTable1().setValueAt("", i, 0);
            vue.getjTable1().setValueAt("", i, 1);
        }
        
        for(String med : lesMeds){
            vue.getjTable1().setValueAt(med, countMed, 0);
            countMed += 1;
        }
        
        nbOffre = getnbMedic(num);

        for(int nb : nbOffre){
            vue.getjTable1().setValueAt(nb, countOffre, 1);
            countOffre += 1;
        }
        
        
    }
    
    
    void SetVuesPrac(int z) {
        MetierPracticiens monPracticien = lesPracticiens.get(z);
        vuePracticiens.getjTextFieldNumero().setText(monPracticien.getPra_num());
        vuePracticiens.getjTextFieldNom().setText(monPracticien.getPra_nom());
        vuePracticiens.getjTextFieldPrenom().setText(monPracticien.getPra_prenom());
        vuePracticiens.getjTextFieldAdresse().setText(monPracticien.getPra_adresse());
        vuePracticiens.getjTextFieldCP().setText(monPracticien.getPra_cp());
        vuePracticiens.getjTextFieldVille().setText(monPracticien.getPra_ville());
        vuePracticiens.getjTextFieldCN().setText(monPracticien.getPra_coefnotoriete());
        vuePracticiens.getjComboBoxLieuExercice().setSelectedIndex(getIntIndexLieu(lesLieus, monPracticien, -1));
    }
        
    int getIntIndexLieu(List<LieuExercice> myLieu, MetierPracticiens myPracticien, int index) {

        for (LieuExercice unLieu : myLieu) {
            if (unLieu.getTyp_code().equals(myPracticien.getTyp_code())) {
                index = myLieu.indexOf(unLieu);
            }
        }
        return index;
    }

    List<String> getMedic(int z){
        List<String> medics=new ArrayList();
        String medic = null;
        
        for(Echantillons unEchant : lesEchantillons){
            if(unEchant.getRAP_NUM() == z){
                for(Medicaments unMedoc : lesMedicaments){
                    if(unMedoc.getDepotLegal().equals(unEchant.getMED_DEPOTLEGAL())){
                        medic = unMedoc.getNomCommercial();
                        medics.add(medic);
                    }
                }
            }
                
        }
        
        return medics;
    }
    
    String getDepot(String nom){
        String dep = null;
        for(Medicaments medoc : lesMedicaments){
            if(nom.equals(medoc.getNomCommercial())){
                dep = medoc.getDepotLegal();
            }
        }
        return dep;
    }
   
    List<Integer> getnbMedic(int z){
        List<Integer>  nbs = new ArrayList();
        int nb ;
        for(Echantillons unEchant : lesEchantillons){
            if(unEchant.getRAP_NUM() == z){
                nb = unEchant.getOFF_QTE();
                nbs.add(nb);
            }
        }

        return nbs;
    }
    
    
}
