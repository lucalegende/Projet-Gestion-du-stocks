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
import java.sql.Statement;
import java.util.ArrayList;
import projet.gestion.du.stocks.Classe.Fournisseurs;

/**
 * Classe pour la gestion des fournisseurs
 *
 * @author Luca GRUNENWALD
 */
public class FournisseursDAO {

    /**
     * Propriété privée
     */
    private static ArrayList<Fournisseurs> Listefournisseurs;

    /**
     * Méthodes pour récupérer la liste des fournisseurs dans la base de données
     *
     * @throws SQLException Exception SQL
     */
    public static void ChargerListeFournisseurs() throws SQLException {
        //On initalise la liste des commandes
        Listefournisseurs = new ArrayList<>();

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT f.Id, f.Nom_fournisseur, SUM(s.Total_Stocks) AS Total_Stocks\n"
                + "FROM FOURNISSEURS AS f\n"
                + "INNER JOIN STOCKS AS s ON f.Id = s.Id_Fournisseur\n"
                + "GROUP BY f.Id, f.Nom_fournisseur";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);

        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();

        //on parcourt les lignes du résultat
        while (rs.next()) {
            int nombreDisponible = rs.getInt("Total_Stocks");
            String nomFounrisseur = rs.getString("Nom_fournisseur");
            int nombreCommander = 0;

            // Recherche du nombre disponible de produit
            nombreDisponible = CommandesDAO.getListeCommandes().stream().filter(x -> x.getFournisseur().equals(nomFounrisseur)).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreDisponible, (accumulator, _item) -> accumulator - _item);
            nombreCommander = CommandesDAO.getListeCommandes().stream().filter(x -> x.getFournisseur().equals(nomFounrisseur)).filter(x -> x.isCommandeValider() == false).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreCommander, (accumulator, _item) -> accumulator + _item);

            Listefournisseurs.add(new Fournisseurs(rs.getInt("Id"), nomFounrisseur, nombreCommander, nombreDisponible, rs.getInt("Total_Stocks")));
        }
    }

    /**
     * Méthode permettant d'ajouter un fournisseur
     *
     * @param NomFournisseur Nom du fournisseur
     * @return boolean
     * @throws SQLException Exception SQL
     */
    public static int AjouterUnFounisseur(String NomFournisseur) throws SQLException, Exception {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO FOURNISSEURS (Nom_fournisseur) VALUES (?)";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, NomFournisseur);
        ps.execute();

        //On récupère l'id générer
        ResultSet rs = ps.getGeneratedKeys();

        //Éxécution de la requete
        if (rs != null && rs.first()) {
            return rs.getInt(1);
        } else {
            throw new Exception("Fournisseur non trouvée");
        }
    }

    /**
     * Récupère le stocks du vaccin lié au fournisseur
     * @param typeVaccin Type du vaccin
     * @param nomFournisseur Nom du fournisseur
     * @return int - Stocks du vaccin du fournisseur
     * @throws SQLException Exception SQL
     */
    public static int StocksVaccinFournisseur(String typeVaccin, String nomFournisseur) throws SQLException {
        //Propriété
        int idFournisseur = FournisseursDAO.getListeFounisseurs().stream().filter(x -> x.getNomFournisseur().equals(nomFournisseur)).findFirst().orElse(null).getId();
        int idVaccin = VaccinsDAO.getListeVaccins().stream().filter(x -> x.getTypeVaccin().equals(typeVaccin)).findFirst().orElse(null).getId();

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT f.Id, f.Nom_fournisseur, SUM(s.Total_Stocks) AS Total_Stocks\n"
                + "FROM STOCKS AS s\n"
                + "INNER JOIN FOURNISSEURS AS f ON s.Id_Fournisseur = f.Id\n"
                + "INNER JOIN VACCINS AS v on s.Id_Vaccin = v.Id\n"
                + "WHERE f.Id = ? AND v.Id = ?\n"
                + "GROUP BY f.Id, f.Nom_fournisseur";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, idFournisseur);
        ps.setInt(2, idVaccin);

        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();

        //on parcourt les lignes du résultat
        int stocksFV = 0;
        while (rs.next()) {
            stocksFV = rs.getInt("Total_Stocks");
        }

        return stocksFV;
    }

    /**
     * Méthode permettant de récupérer la liste des fournisseurs
     *
     * @return ArrayList - Liste des fournisseurs
     */
    public static ArrayList<Fournisseurs> getListeFounisseurs() {
        return new ArrayList<>(Listefournisseurs);
    }
}
