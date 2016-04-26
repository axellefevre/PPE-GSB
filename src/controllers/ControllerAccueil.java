/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controllers;

import Vues.Accueil;
import Vues.Menu;
import Vues.Visiteurs;
import java.awt.Component;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;
import models.dao.Dao;
import models.metier.MetierVisiteur;

/**
 *
 * @author btssio
 */
public class ControllerAccueil implements ActionListener {

    private Accueil vue;
    private MetierVisiteur myVisiteur;

    public ControllerAccueil(Accueil vue) {
        this.vue = vue;
        vue.getjButtonConnection().addActionListener(this);
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        Object source = e.getSource();
        if (source == vue.getjButtonConnection()) {

            try {
                String login = vue.getjTextFieldLogin().getText();
                String pass =  vue.getjPasswordField().getText();
                myVisiteur = Dao.getConnection(login, pass);  
                if (myVisiteur != null) {
                    Menu vue = new Menu();
                    ControllerMenu controllers = new ControllerMenu(vue, login);
                    vue.setVisible(true);
                    this.vue.hide();
                } else {
                    Component frame=null;
                    JOptionPane.showMessageDialog(frame, "you must enter your username and password","Erreur",JOptionPane.ERROR_MESSAGE);
                }
            } catch (SQLException ex) {
                Logger.getLogger(ControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(ControllerAccueil.class.getName()).log(Level.SEVERE, null, ex);
            }

        }
        
    }
    
    public MetierVisiteur getVisiteur(MetierVisiteur unVisiteur) {
        unVisiteur = myVisiteur;
        return unVisiteur;
        
    }

}
