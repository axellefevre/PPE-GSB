/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.Menu;
import Vues.practiciens;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.Dao;
import models.metier.LieuExercice;
import models.metier.MetierPracticiens;

/**
 *
 * @author btssio
 */
public class ControllersPracticiens implements ActionListener {

    private practiciens vue;
    private List<MetierPracticiens> lesPracticiens;
    private List<LieuExercice> lesLieus;
    private String login;

    public ControllersPracticiens(practiciens vue, String login) {
        this.vue = vue;
        this.login = login;
        afficherLesPraticiens();
        afficherLesLieuExercice();
        vue.getjButtonOK().addActionListener(this);
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
    }

    public final void afficherLesPraticiens() {
        try {
            lesPracticiens = Dao.getAllPracticiens();
            for (MetierPracticiens unPracticien : lesPracticiens) {
                vue.getModeleListePracticiens().addElement(unPracticien);
            }

        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }
    }

    public final void afficherLesLieuExercice() {
        try {
            lesLieus = Dao.getAllLieuExercice();
            for (LieuExercice unLieu : lesLieus) {
                vue.getjComboBoxLieuExercice().addItem(unLieu);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }

    }
    
    
    @Override
   public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getjButtonOK()) {
            setVues();
        }
        if (source == vue.getjButtonPrecedent()) {
            int i = vue.getjComboBoxRecherche().getSelectedIndex();
            int z = i - 1;
            if (z > -1) {

                vue.getjComboBoxRecherche().setSelectedIndex(z);
                setVues();
            }
        }
        if (source == vue.getjButtonSuivant()) {
            int i = vue.getjComboBoxRecherche().getSelectedIndex();
            int z = i + 1;
            if (z < vue.getjComboBoxRecherche().getItemCount()) {
                vue.getjComboBoxRecherche().setSelectedIndex(z);
                setVues();
            }
        }
        if (source == vue.getjButtonFermer()) {
            vue.setVisible(false);
            Menu vue = new Menu();
            ControllerMenu controllers = new ControllerMenu(vue, login);
            vue.setVisible(true);
        }
    }

    void setVues() {
        MetierPracticiens monPracticien = (MetierPracticiens) vue.getModeleListePracticiens().getSelectedItem();
        vue.getjTextFieldNumero().setText(monPracticien.getPra_num());
        vue.getjTextFieldNom().setText(monPracticien.getPra_nom());
        vue.getjTextFieldPrenom().setText(monPracticien.getPra_prenom());
        vue.getjTextFieldAdresse().setText(monPracticien.getPra_adresse());
        vue.getjTextFieldCP().setText(monPracticien.getPra_cp());
        vue.getjTextFieldVille().setText(monPracticien.getPra_ville());
        vue.getjTextFieldCN().setText(monPracticien.getPra_coefnotoriete());
        vue.getjComboBoxLieuExercice().setSelectedIndex(getIntIndexLieu(lesLieus, monPracticien, -1));
    }


 int getIntIndexLieu(List<LieuExercice> myLieu, MetierPracticiens myPracticien, int index) {

        for (LieuExercice unLieu : myLieu) {
            if (unLieu.getTyp_code().equals(myPracticien.getTyp_code())) {
                index = myLieu.indexOf(unLieu);
            }
        }
        return index;
    }

}
