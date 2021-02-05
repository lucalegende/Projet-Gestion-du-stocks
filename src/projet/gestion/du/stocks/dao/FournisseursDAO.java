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
import java.util.ArrayList;
import projet.gestion.du.stocks.Classe.Fournisseurs;

/**
 * Classe FournisseurDAO
 * @author Luca GRUNENWALD
 */
public class FournisseursDAO {
    
    /**
     * Propriété privée
     */
    private static ArrayList<Fournisseurs> Listefournisseurs;
   
    /**
     * Méthodes pour récupérer la liste des fournisseurs dans la base de données
     * @throws SQLException  Exception SQL
     */
    public static void ChargerListeFournisseurs() throws SQLException{
        //On initalise la liste des commandes
        Listefournisseurs = new ArrayList<>();
        
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT * FROM FOURNISSEURS";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        
        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();
        
        //on parcourt les lignes du résultat
        while(rs.next()){
            int nombreDisponible = rs.getInt("Total_de_stocks");
            String nomFounrisseur = rs.getString("Nom_fournisseur");
            int nombreCommander = 0;
            
            // Recherche du nombre disponible de produit
            nombreDisponible = CommandesDAO.getListeCommandes().stream().filter(x -> x.getFournisseur().equals(nomFounrisseur)).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreDisponible, (accumulator, _item) -> accumulator - _item);
            nombreCommander =  CommandesDAO.getListeCommandes().stream().filter(x -> x.getFournisseur().equals(nomFounrisseur)).filter(x -> x.isCommandeValider() == false).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreCommander, (accumulator, _item) -> accumulator + _item);
            
            Listefournisseurs.add(new Fournisseurs(rs.getInt("Id"), nomFounrisseur, nombreCommander, nombreDisponible, rs.getInt("Total_de_stocks")));
        }
    }
    
    /**
     * Méthode permettant d'ajouter un fournisseur
     * @param NomFournisseur Nom du fournisseur
     * @param TotalStocks Total de stocks du vaccin chez le fournisseur
     * @return boolean
     * @throws SQLException  Exception SQL
     */
    public static boolean AjouterUnFounisseur(String NomFournisseur, int TotalStocks) throws SQLException{
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO FOURNISSEURS (Nom_fournisseur, Total_de_stocks) VALUES (?, ?)";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setString(1, NomFournisseur);
        ps.setInt(2, TotalStocks);
        
        //Éxécution de la requete
        return ps.executeUpdate() == 1;  
    }
    
    /**
     * Méthode permettant d'ajouter du stocks au fournisseur
     * @param NomFournisseurs Nom du fournisseur
     * @param Quantiter Quantité demandé
     * @return boolean
     * @throws SQLException  Exception SQL
     */
    public static boolean AjouterDuStocks(String NomFournisseurs, int Quantiter) throws SQLException{

        //On incrémente la donnée et on la récupère dans une variable
        Listefournisseurs.stream().filter(Listefournisseur -> (Listefournisseur.getNomFournisseur().equals(NomFournisseurs))).forEachOrdered((Fournisseurs Listefournisseur) -> {
            Listefournisseur.setTotalStocks(Listefournisseur.getTotalStocks()+Quantiter);
        });
        
        Fournisseurs f = Listefournisseurs.stream().filter(x -> x.getNomFournisseur().equals(NomFournisseurs)).findFirst().orElse(null);

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "UPDATE FOURNISSEURS SET Total_de_stocks = ?  WHERE Nom_fournisseur = ? ";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1,f.getTotalStocks());
        ps.setString(2, NomFournisseurs);
        
        //Éxécution de la requete
        return ps.executeUpdate() == 1; 
    }
    
    /**
     * Méthode permettant de mettre a jour la liste des fournisseurs
     * @param NomFournisseur Nom du fournisseur
     * @param Quantiter Quantiter demandé
     * @throws SQLException  Exception SQL
     */
    public static void MiseAJourListeFournisseur(String NomFournisseur, int Quantiter) throws SQLException{
        Fournisseurs founisseur = Listefournisseurs.stream().filter(x -> x.getNomFournisseur().equals(NomFournisseur)).findFirst().orElse(null);
        
        //Si le vaccin n'est pas trouver
        if (founisseur == null) {
            //On ajoute un vaccin
            AjouterUnFounisseur(NomFournisseur, Quantiter);
        } else {
            //Sinon on ajoute la quantité
            AjouterDuStocks(NomFournisseur, Quantiter);
        }
        
        //On recharge la liste
        ChargerListeFournisseurs();
    }
    
    /**
     * Méthode permettant de récupérer la liste des fournisseurs
     * @return ArrayList
     */
    public static ArrayList<Fournisseurs> getListeFounisseurs(){
        return new ArrayList<>(Listefournisseurs);
    }
}
