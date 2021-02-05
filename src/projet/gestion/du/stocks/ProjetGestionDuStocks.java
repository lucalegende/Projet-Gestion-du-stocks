/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks;

import java.sql.SQLException;
import projet.gestion.du.stocks.design.stockFenetre;

/**
 * Classe principal pour le lancement de l'application
 * @author Luca GRUNENWALD
 */
public class ProjetGestionDuStocks {

    /**
     * Méthode principal pour le lancement de l'application
     * @param args the command line arguments
     * @throws java.sql.SQLException Exception SQL
     */
    public static void main(String[] args) throws SQLException{
        stockFenetre stockF = new stockFenetre();
        stockF.setVisible(true);
    } 
}