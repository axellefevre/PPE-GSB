import Vues.Accueil;
import Vues.Menu;
import Vues.Visiteurs;
import controllers.ControllerAccueil;
import controllers.ControllerMenu;
import java.sql.SQLException;
import javax.swing.JOptionPane;
import models.Connect;
import controllers.ControllersVisiteurs;
import javax.swing.UIManager;
import javax.swing.UIManager.*;



/**
 *
 * @author btssio
 */
public class Main {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {        
        try {
            for (LookAndFeelInfo info : UIManager.getInstalledLookAndFeels()) {
                if ("Nimbus".equals(info.getName())) {
                    UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (Exception e) {
            // If Nimbus is not available, you can set the GUI to another look and feel.
        }
        Accueil vue = new Accueil();
        ControllerAccueil controllers = new ControllerAccueil(vue);
        vue.setVisible(true);       
    }
    
}
