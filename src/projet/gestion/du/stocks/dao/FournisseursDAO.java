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
 *
 * @author Kalictong
 */
public class FournisseursDAO {
    
    //Propriété privée
    private static ArrayList<Fournisseurs> Listefournisseurs;
    
    //Méthodes : Récupération des données de la base
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
    
    /*public static ArrayList<Fournisseurs> TousLesFournisseursDuVaccin(Vaccins vaccin){
        return Listefournisseurs;
    }*/
    
    //Méthodes : Mise à jour des données dans la base
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
    
    public static boolean AjouterDuStocks(String NomFournisseurs, int Quantité) throws SQLException{

        //On incrémente la donnée et on la récupère dans une variable
        Listefournisseurs.stream().filter(Listefournisseur -> (Listefournisseur.getNomFournisseur().equals(NomFournisseurs))).forEachOrdered(Listefournisseur -> {
            Listefournisseur.setTotalStocks(Listefournisseur.getTotalStocks()+Quantité);
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
    
    public static void MiseAJourListeFournisseur(String NomFournisseur, int Quantité) throws SQLException{
        Fournisseurs founisseur = Listefournisseurs.stream().filter(x -> x.getNomFournisseur().equals(NomFournisseur)).findFirst().orElse(null);
        
        //Si le vaccin n'est pas trouver
        if (founisseur == null) {
            //On ajoute un vaccin
            AjouterUnFounisseur(NomFournisseur, Quantité);
        } else {
            //Sinon on ajoute la quantité
            AjouterDuStocks(NomFournisseur, Quantité);
        }
        
        //On recharge la liste
        ChargerListeFournisseurs();
    }
    
    //Méthodes général
    public static ArrayList<Fournisseurs> getListeFounisseurs(){
        return new ArrayList<>(Listefournisseurs);
    }
}
