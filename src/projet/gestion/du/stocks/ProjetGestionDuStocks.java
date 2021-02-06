/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks;

import java.sql.SQLException;
import javax.swing.*;
import projet.gestion.du.stocks.dao.ConnectionDAO;
import projet.gestion.du.stocks.design.stockFenetre;

/**
 * Classe principal pour le lancement de l'application
 *
 * @author Luca GRUNENWALD
 */
public class ProjetGestionDuStocks {

    /**
     * Méthode principal pour le lancement de l'application
     *
     * @param args the command line arguments
     * @throws java.sql.SQLException Exception SQL
     */
    public static void main(String[] args) throws SQLException {
        try {
            //Création d'une instance de connexion
            ConnectionDAO.getInstance();
            
            //Affichage de la fenêtre principal
            stockFenetre stockF = new stockFenetre();
            stockF.setVisible(true);
        } catch (SQLException e) {
            JOptionPane.showConfirmDialog(null,"Impossible de ce connection a la base de données.\n"
            + "Vérifier que vous avez bien installer :\n"
            + " - Wamp avec mysql\n"
            + " - La base de données\n"
            + "Sinon veillez suivre le manuel d'installation", "Vérification", JOptionPane.OK_CANCEL_OPTION);
        } catch (ClassNotFoundException e2) {
            JOptionPane.showConfirmDialog(null,"Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet","Vérification", JOptionPane.OK_CANCEL_OPTION);
        }
    }
}
