package controllers;

import Vues.Medicament;
import models.metier.Medicaments;
import Vues.Menu;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.Dao;

/**
 *
 * @author btssio
 */
public class ControllersMedicaments implements ActionListener {
    
    private Medicament vue;
    private List<Medicaments> lesMedicaments;
    int count = 0 ;
    private String login;
    
    public ControllersMedicaments(Medicament vue, String login){
        this.vue=vue;
        this.login = login;
        int count = 0 ;
        afficherLesPraticiens();
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
    }
    
    public final void afficherLesPraticiens() {
        try {
            lesMedicaments = Dao.getAllMedic();
            setVues(count);
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }
    }
    
   public void actionPerformed(ActionEvent e) {
        try {
            Object source = e.getSource();
            setVues(count);
        if (source == vue.getjButtonPrecedent()) {
            int z = count - 1;
            if (z > -1) {
                setVues(z);
                count = z ;
            }
        }
        if (source == vue.getjButtonSuivant()) {
            int z = count + 1;
            if(z<lesMedicaments.size()){
                setVues(z);
                count = z ;
            }
        }
        
            if (source == vue.getjButtonFermer()) {
                vue.setVisible(false);
                Menu vue = new Menu();
                ControllerMenu controllers = new ControllerMenu(vue, login);
                vue.setVisible(true);
            }
        } catch (SQLException ex) {
            Logger.getLogger(ControllersMedicaments.class.getName()).log(Level.SEVERE, null, ex);
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(ControllersMedicaments.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
   void setVues(int z) throws SQLException, ClassNotFoundException{
       Medicaments unMedoc = lesMedicaments.get(z);
       vue.getjTextFieldDepotLegal().setText(unMedoc.getDepotLegal());
       vue.getjTextFieldNomCommercial().setText(unMedoc.getNomCommercial());
       vue.getjTextFieldFamille().setText(unMedoc.getFam_code());
       vue.getjTextFieldComposition().setText(unMedoc.getComposition());
       vue.getjTextAreaEffet().setText(unMedoc.getEffect());
       vue.getjTextAreaContreIndication().setText(unMedoc.getContreIndic());
       vue.getjTextFieldPrixEchantillon().setText(unMedoc.getPrixEchantillon());
       
   }
}
