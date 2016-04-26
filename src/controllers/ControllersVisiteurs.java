/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.Accueil;
import Vues.Menu;
import Vues.Visiteurs;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import models.metier.MetierVisiteur;
import java.util.List;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.Dao;
import models.metier.Labo;
import models.metier.Secteur;

/**
 *
 * @author btssio
 */
public class ControllersVisiteurs implements ActionListener {

    private Visiteurs vue;
    private List<MetierVisiteur> lesVisiteurs;
    private List<Labo> lesLabos;
    private List<Secteur> lesSecteurs;
    private String login;

    public ControllersVisiteurs(Visiteurs vue, String login) {
        this.vue = vue;
        this.login = login;
        afficherLesVisiteurs();
        afficherLesLabos();
        afficherLesSecteurs();
        vue.getjButtonOK().addActionListener(this);
        vue.getjButtonFermer().addActionListener(this);
        vue.getjButtonPrecedent().addActionListener(this);
        vue.getjButtonSuivant().addActionListener(this);
    }

    public final void afficherLesLabos() {
        try {
            lesLabos = Dao.getAllLab();
            for (Labo labo : lesLabos) {
                vue.getjComboBoxLaboVisiteur().addItem(labo);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }

    }

    public final void afficherLesSecteurs() {
        try {
            lesSecteurs = Dao.getAllSecteurs();
            for (Secteur secteur : lesSecteurs) {
                vue.getjComboBoxSecteurVisiteur().addItem(secteur);
            }
        } catch (SQLException ex) {
            ex.printStackTrace();
        } catch (ClassNotFoundException ex) {
            JOptionPane.showMessageDialog(vue, "Ctrl - erreur SQL");
        }

    }

    public final void afficherLesVisiteurs() {
        try {
            lesVisiteurs = Dao.getAll();
            for (MetierVisiteur visiteur : lesVisiteurs) {
                vue.getModeleListeVisiteurs().addElement(visiteur);
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
            int i = vue.getjComboBoxListeVisiteurs().getSelectedIndex();
            int z = i - 1;
            if (z > -1) {

                vue.getjComboBoxListeVisiteurs().setSelectedIndex(z);
                setVues();
            }
        }
        if (source == vue.getjButtonSuivant()) {
            int i = vue.getjComboBoxListeVisiteurs().getSelectedIndex();
            int z = i + 1;
            if (z < vue.getjComboBoxListeVisiteurs().getItemCount()) {
                vue.getjComboBoxListeVisiteurs().setSelectedIndex(z);
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
        MetierVisiteur monVisiteur = (MetierVisiteur) vue.getModeleListeVisiteurs().getSelectedItem();
        vue.getjTextFieldNomVisiteur().setText(monVisiteur.getNom());
        vue.getjTextFieldPrenomVisiteur().setText(monVisiteur.getPrenom());
        vue.getjTextFieldAdresseVisiteur().setText(monVisiteur.getAdresse());
        vue.getjTextFieldCPVisiteur().setText(monVisiteur.getCp());
        vue.getjTextFieldVilleVisiteur().setText(monVisiteur.getVille());
        vue.getjComboBoxLaboVisiteur().setSelectedIndex(getIntIndexLabo(lesLabos, monVisiteur, -1));
        vue.getjComboBoxSecteurVisiteur().setSelectedIndex(getIntIndexSect(lesSecteurs, monVisiteur, -1));
    }

    int getIntIndexSect(List<Secteur> mySecteurs, MetierVisiteur myVisiteur, int index) {
        for (Secteur secteur : mySecteurs) {
            if (secteur.getCodeSecteur().equals(myVisiteur.getSecCode())) {
                index = mySecteurs.indexOf(secteur);
            } else {

            }
        }
        return index;
    }

    int getIntIndexLabo(List<Labo> myLabs, MetierVisiteur myVisiteur, int index) {

        for (Labo labo : myLabs) {
            if (labo.getLabCode().equals(myVisiteur.getLabCode())) {
                index = myLabs.indexOf(labo);
            }
        }
        return index;
    }

}

