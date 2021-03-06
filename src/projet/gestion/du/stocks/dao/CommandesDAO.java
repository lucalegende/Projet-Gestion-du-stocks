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
import projet.gestion.du.stocks.Classe.Commandes;

/**
 * Classe pour la gestion des commandes
 *
 * @author Luca GRUNENWALD
 */
public class CommandesDAO {

    /**
     * Propriété privée
     */
    private static ArrayList<Commandes> ListeCommandes;

    /**
     * Méthodes pour récupérer la liste des commandes dans la base de données
     *
     * @throws SQLException Exception SQL
     */
    public static void ChargerListeCommandes() throws SQLException {
        //On initalise la liste des commandes
        ListeCommandes = new ArrayList<>();

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT C.Id, C.Client, F.Nom_fournisseur, V.Type_Vaccin, C.Nombre_Commander, A.Commande_Valider\n"
                + "FROM achat AS A\n"
                + "INNER JOIN commandes AS C ON C.Id = A.Id_Commandes\n"
                + "INNER JOIN fournisseurs AS F ON F.Id = A.Id_Fournisseurs\n"
                + "INNER JOIN vaccins AS V ON V.Id = A.Id_Vaccins";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);

        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();

        //on parcourt les lignes du résultat
        while (rs.next()) {
            ListeCommandes.add(new Commandes(rs.getInt("Id"), rs.getString("Client"), rs.getString("Type_Vaccin"), rs.getString("Nom_fournisseur"), rs.getInt("Nombre_Commander"), rs.getBoolean("Commande_Valider")));
        }
    }

    /**
     * Méthode permettant d'ajouter une commande
     *
     * @param typeVaccin Type de vaccin
     * @param nomFournisseur Nom du fournisseur
     * @param client Nom du client
     * @param quantiter Quantité demandé
     * @return boolean - Vérifie si une commandes est bien ajouter
     * @throws SQLException Exception SQL
     */
    public static boolean AjouterUneCommande(String typeVaccin, String nomFournisseur, String client, int quantiter) throws SQLException {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO COMMANDES (Client, Nombre_Commander) VALUES (?, ?)";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
        ps.setString(1, client);
        ps.setInt(2, quantiter);
        ps.execute();

        //On récupère l'id générer
        ResultSet rs = ps.getGeneratedKeys();

        //Éxécution de la requete
        if (rs != null && rs.first()) {
            //Recherche des 3 id
            int idVaccin = VaccinsDAO.getListeVaccins().stream().filter(x -> x.getTypeVaccin().equals(typeVaccin)).findFirst().orElse(null).getId();
            int idFournisseur = FournisseursDAO.getListeFounisseurs().stream().filter(x -> x.getNomFournisseur().equals(nomFournisseur)).findFirst().orElse(null).getId();
            int idCommande = rs.getInt(1);

            //Requete SQL pour la liste des fournisseurs
            sql = "INSERT INTO ACHAT (Id_Vaccins, Id_Fournisseurs, Id_Commandes, Commande_Valider) VALUES (?, ?, ?, ?)";

            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.setInt(1, idVaccin);
            ps.setInt(2, idFournisseur);
            ps.setInt(3, idCommande);
            ps.setBoolean(4, false);

            if (ps.executeUpdate() == 1) {
                return true;
            }
        }
        return false;
    }

    /**
     * Méthode permettant la validation d'une commande
     *
     * @param idCommandes Identifiant de la commandes
     * @return boolean - Vérifie si une commande est bien valider
     * @throws SQLException Exception SQL
     */
    public static boolean ValiderUneCommande(int idCommandes) throws SQLException {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "UPDATE ACHAT SET Commande_Valider = ? WHERE Id_Commandes = ?";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setBoolean(1, true);
        ps.setInt(2, idCommandes);

        //Éxécution de la requete
        return ps.executeUpdate() == 1;
    }

    /**
     * Méthode permettant l'annulation d'une commande
     *
     * @param idCommandes Identifiant de la commandes
     * @return boolean - Vérifie si une commande est bien annuler
     * @throws SQLException Exception SQL
     */
    public static boolean AnnulerUneCommande(int idCommandes) throws SQLException {
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getConnexion();

        //Requete SQL pour la liste des fournisseurs
        String sql = "DELETE FROM ACHAT WHERE Id_Commandes = ?";

        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1, idCommandes);

        //Éxécution de la requete
        if (ps.executeUpdate() == 1) {

            //Requete SQL pour la liste des fournisseurs
            sql = "DELETE FROM COMMANDES WHERE Id = ?";

            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.setInt(1, idCommandes);

            if (ps.executeUpdate() == 1) {
                return true;
            }
        }

        return false;
    }

    /**
     * Méthode permettant de récupérer la liste des commandes
     *
     * @return ArrayList - Liste des commandes
     */
    public static ArrayList<Commandes> getListeCommandes() {
        return new ArrayList<>(ListeCommandes);
    }

    /**
     * Récupère le nombre de commandes d'un founisseur et d'un vaccin
     * @param typeVaccin Type de vaccin
     * @param nomFournisseur Nom du fournisseur
     * @return int - Nombre commander de vaccin via un fournisseur
     */
    public static int CommandesFournisseurVaccin(String typeVaccin, String nomFournisseur) {
        int nombreCommanderVaccin = 0;
        nombreCommanderVaccin = ListeCommandes.stream().filter(ListeCommande -> (ListeCommande.getVaccin().equals(typeVaccin) && ListeCommande.getFournisseur().equals(nomFournisseur))).map(ListeCommande -> ListeCommande.getNombreVaccinCommander()).reduce(nombreCommanderVaccin, Integer::sum);
        return nombreCommanderVaccin;
    }
}
