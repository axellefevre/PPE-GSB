/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.Accueil;
import Vues.Menu;
import Vues.Visiteurs;
import Vues.practiciens;
import Vues.Medicament;
import Vues.rapportVisite;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ControllerMenu implements ActionListener {

    private Menu vue;
    private String login;

    public ControllerMenu(Menu vue, String login) {
        this.vue = vue;
        this.login = login;
        vue.getjButtonMenuVisiteur().addActionListener(this);
        vue.getjButtonRDV().addActionListener(this);
        vue.getjButtonDeconnexion().addActionListener(this);
        vue.getjButtonPracticiens().addActionListener(this);
        vue.getjButtonMedicaments().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getjButtonMenuVisiteur()) {
            Visiteurs vueVisiteur = new Visiteurs();
            ControllersVisiteurs controllers = new ControllersVisiteurs(vueVisiteur, login);
            vueVisiteur.setVisible(true);
            this.vue.hide();
        }

        if (source == vue.getjButtonRDV()) {
            rapportVisite vueRapportVisiteur = new rapportVisite();
            ControllersRapportVisite controllers = new ControllersRapportVisite(vueRapportVisiteur, login);
            vueRapportVisiteur.setVisible(true);
            this.vue.hide();
        }
        
        if (source == vue.getjButtonPracticiens()) {
            practiciens vueUnPracticien = new practiciens();
            ControllersPracticiens controllers = new ControllersPracticiens(vueUnPracticien, login);
            vueUnPracticien.setVisible(true);
            this.vue.hide();
        }
        
        if (source == vue.getjButtonMedicaments()){
            Medicament vueMedicament = new Medicament();
            ControllersMedicaments controllers = new ControllersMedicaments(vueMedicament, login);
            vueMedicament.setVisible(true);
            this.vue.hide();
        }

        if (source == vue.getjButtonDeconnexion()) {
            this.vue.hide();
            Accueil vue = new Accueil();
            ControllerAccueil controllers = new ControllerAccueil(vue);
            vue.setVisible(true);
        }
    }
}
