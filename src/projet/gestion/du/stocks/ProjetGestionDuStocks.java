/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks;

import java.sql.SQLException;
import projet.gestion.du.stocks.dao.CommandesDAO;
import projet.gestion.du.stocks.dao.FournisseursDAO;
import projet.gestion.du.stocks.dao.VaccinsDAO;
import projet.gestion.du.stocks.design.stockFenetre;

/**
 *
 * @author Kalic
 */
public class ProjetGestionDuStocks {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) throws SQLException {
        //Charge la liste des commandes
        CommandesDAO.ChargerListeCommandes();

        //Charge la liste des fournisseurs
        FournisseursDAO.ChargerListeFournisseurs();
        
        //Charge la liste des vaccins
        VaccinsDAO.ChargerListeVaccins();
        
        stockFenetre stockF = new stockFenetre();
        stockF.setVisible(true);
    } 
}