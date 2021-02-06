/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe pour gestion du stocks
 * @author GRUNENWALD Luca
 */
public class StocksDAO {

    /**
     * Propriété privée
     */
    private static int stocksInitial = -1;

    /**
     * Ajouter du stocks dans la base de donnée via un vaccin et un fournisseur
     * @param idVaccin Identifiant Vaccin
     * @param idFournisseur Identification fournisseur
     * @param Quantiter Quantité du stocks
     * @return boolean - Si le stocks a bien été ajouter
     * @throws SQLException Exception SQL
     */
    public static boolean AjouterDuStock(int idVaccin, int idFournisseur, int Quantiter) throws SQLException {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO STOCKS (Id_Fournisseur, Id_Vaccin, Total_Stocks) VALUES (?,?,?)";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, idFournisseur);
        ps.setInt(2, idVaccin);
        ps.setInt(3, Quantiter);

        //Éxécution de la requete
        return ps.executeUpdate() == 1;
    }

    /**
     * Mise à jour du stocks dans la base de donnée
     * @param idVaccin Identifiant Vaccin
     * @param idFournisseur Identification fournisseur
     * @param Quantiter Quantité du stocks
     * @return boolean - Si le stocks a bien été ajouter
     * @throws SQLException Exception SQL
     */
    public static boolean MiseAJourDuStock(int idVaccin, int idFournisseur, int Quantiter) throws SQLException {
        if (StocksExisteBDD(idVaccin, idFournisseur)) {
            //On essaye de se connecter
            Connection connexion = ConnectionDAO.getConnexion();

            //Requete SQL pour la liste des fournisseurs
            String sql = "UPDATE STOCKS SET Total_Stocks = ?\n"
                    + "WHERE Id_Fournisseur = ? AND Id_Vaccin = ?";

            //Préparation de la requete
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.setInt(1, stocksInitial + Quantiter);
            ps.setInt(2, idFournisseur);
            ps.setInt(3, idVaccin);

            //Éxécution de la requete
            return ps.executeUpdate() == 1;
        } else {
            return AjouterDuStock(idVaccin, idFournisseur, Quantiter);
        }
    }

    /**
     * Vérifie si le stocks du fournisseur via un vaccin existe
     * @param idVaccin Identifiant du vaccin
     * @param idFournisseur Identifiant du fournisseur
     * @return boolean - Vérifie si le sotcks existe
     * @throws SQLException Exception SQL
     */
    public static boolean StocksExisteBDD(int idVaccin, int idFournisseur) throws SQLException {
        stocksInitial = -1;

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT * FROM STOCKS\n"
                + "WHERE Id_Fournisseur = ? AND Id_Vaccin = ?";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, idFournisseur);
        ps.setInt(2, idVaccin);

        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();

        while (rs.next()) {
            stocksInitial = rs.getInt("Total_Stocks");
        }

        return stocksInitial > -1;
    }
}
