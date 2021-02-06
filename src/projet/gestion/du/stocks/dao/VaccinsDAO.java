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
import projet.gestion.du.stocks.Classe.Vaccins;

/**
 * Classe pour la gestion des vaccins
 *
 * @author Kalictong
 */
public class VaccinsDAO {

    /**
     * Propriété privée
     */
    private static ArrayList<Vaccins> ListeVaccins = new ArrayList<Vaccins>();

    /**
     * Méthodes pour récupérer la liste des vaccins dans la base de données
     *
     * @throws SQLException Exception SQL
     */
    public static void ChargerListeVaccins() throws SQLException {
        //On initalise la liste des vaccins
        ListeVaccins = new ArrayList<>();

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT v.Id, v.Type_Vaccin, SUM(s.Total_Stocks) AS Total_Stocks\n"
                + "FROM VACCINS AS v\n"
                + "INNER JOIN STOCKS AS s ON v.Id = s.Id_Vaccin\n"
                + "GROUP BY v.Id, v.Type_Vaccin";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);

        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();

        //on parcourt les lignes du résultat
        while (rs.next()) {
            int nombreDisponible = rs.getInt("Total_Stocks");
            String typeVaccin = rs.getString("Type_Vaccin");
            int nombreCommander = 0;

            // Recherche des données
            nombreDisponible = CommandesDAO.getListeCommandes().stream().filter(x -> x.getVaccin().equals(typeVaccin)).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreDisponible, (accumulator, _item) -> accumulator - _item);
            nombreCommander = CommandesDAO.getListeCommandes().stream().filter(x -> x.getVaccin().equals(typeVaccin)).filter(x -> x.isCommandeValider() == false).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreCommander, (accumulator, _item) -> accumulator + _item);

            ListeVaccins.add(new Vaccins(rs.getInt("Id"), typeVaccin, nombreCommander, nombreDisponible, rs.getInt("Total_Stocks")));
        }
    }

    /**
     * Méthode permettant d'ajouter un vaccin
     *
     * @param TypeVaccin Type du vaccin
     * @return boolean - Vérifie l'ajour du vaccin
     * @throws SQLException Exception SQL
     */
    public static int AjouterUnVaccin(String TypeVaccin) throws SQLException, Exception {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO VACCINS (Type_Vaccin) VALUES (?)";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, TypeVaccin);
        ps.execute();

        //On récupère l'id générer
        ResultSet rs = ps.getGeneratedKeys();

        //Éxécution de la requete
        if (rs != null && rs.first()) {
            return rs.getInt(1);
        } else {
            throw new Exception("Vaccin non trouvée");
        }
    }

    /**
     * Méthode permettant de mettre à jour la liste des vaccins
     *
     * @param TypeVaccin Type du vaccin
     * @param NomFournisseur Nom du fournisseur
     * @param Quantiter Quantité demandé
     * @throws SQLException Exception SQL
     */
    public static void MiseAJourListeVaccins(String TypeVaccin, String NomFournisseur, int Quantiter) throws SQLException, Exception {
        int idVaccin = -1;
        int idFounisseur = -1;

        Vaccins v = ListeVaccins.stream().filter(x -> x.getTypeVaccin().equals(TypeVaccin)).findFirst().orElse(null);
        Fournisseurs f = FournisseursDAO.getListeFounisseurs().stream().filter(x -> x.getNomFournisseur().equals(NomFournisseur)).findFirst().orElse(null);

        //Si le vaccin n'est pas trouver
        if (v == null) {
            //On ajoute un vaccin
            idVaccin = AjouterUnVaccin(TypeVaccin);
        } else {
            idVaccin = v.getId();
        }

        if (f == null) {
            //On ajoute le founnisseur s'il n'existe pas
            idFounisseur = FournisseursDAO.AjouterUnFounisseur(NomFournisseur);
        } else {
            idFounisseur = f.getId();
        }

        //On fait la mise a jour du stocks
        StocksDAO.MiseAJourDuStock(idVaccin, idFounisseur, Quantiter);
    }

    /**
     * Méthode permettant de récupérer la liste des vaccins
     *
     * @return ArrayList - Liste des vaccins
     */
    public static ArrayList<Vaccins> getListeVaccins() {
        return new ArrayList<>(ListeVaccins);
    }
}
