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
import projet.gestion.du.stocks.Classe.Vaccins;

/**
 * Classe VaccinsDAO
 * @author Kalictong
 */
public class VaccinsDAO {
    
    /**
     * Propriété privée
     */
    private static ArrayList<Vaccins> ListeVaccins = new ArrayList<Vaccins>();
    
    /**
     *  Méthodes pour récupérer la liste des vaccins dans la base de données
     * @throws SQLException  Exception SQL
     */
    public static void ChargerListeVaccins() throws SQLException{
        //On initalise la liste des vaccins
        ListeVaccins = new ArrayList<>();
        
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT * FROM VACCINS";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        
        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();
        
        //on parcourt les lignes du résultat
        while(rs.next()){
            int nombreDisponible = rs.getInt("Total_stocks");
            String typeVaccin = rs.getString("Type_Vaccin");
            int nombreCommander = 0;
            
            // Recherche des données
            nombreDisponible = CommandesDAO.getListeCommandes().stream().filter(x -> x.getVaccin().equals(typeVaccin)).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreDisponible, (accumulator, _item) -> accumulator - _item);
            nombreCommander =  CommandesDAO.getListeCommandes().stream().filter(x -> x.getVaccin().equals(typeVaccin)).filter(x -> x.isCommandeValider() == false).map(listeCommande -> listeCommande.getNombreVaccinCommander()).reduce(nombreCommander, (accumulator, _item) -> accumulator + _item);
           
            ListeVaccins.add(new Vaccins(rs.getInt("Id"), typeVaccin, nombreCommander, nombreDisponible, rs.getInt("Total_stocks")));
        }
    }
    
    /**
     * Méthode permettant d'ajouter un vaccin
     * @param TypeVaccin Type du vaccin
     * @param TotalStocks Total de stocks du vaccin
     * @return boolean
     * @throws SQLException Exception SQL
     */
    public static boolean AjouterUnVaccin(String TypeVaccin, int TotalStocks) throws SQLException{
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "INSERT INTO VACCINS (Type_Vaccin, Total_stocks) VALUES (?, ?)";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setString(1, TypeVaccin);
        ps.setInt(2, TotalStocks);
        
        //Éxécution de la requete
        return ps.executeUpdate() == 1;  
    }
    
    /**
     * Méthode permettant d'ajouter du stocks a un vaccin
     * @param TypeVaccin Type du vaccin
     * @param Quantiter Quantité demandé
     * @return boolean
     * @throws SQLException Exception SQL 
     */
    public static boolean AjouterDuStock(String TypeVaccin, int Quantiter) throws SQLException{
        //On incrémente la donnée et on la récupère dans une variable
        ListeVaccins.stream().filter(Listefournisseur -> (Listefournisseur.getTypeVaccin().equals(TypeVaccin))).forEachOrdered(Listefournisseur -> {
            Listefournisseur.setTotalStocks(Listefournisseur.getTotalStocks()+Quantiter);
        });
        
        Vaccins v = ListeVaccins.stream().filter(x -> x.getTypeVaccin().equals(TypeVaccin)).findFirst().orElse(null);

        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "UPDATE VACCINS SET Total_stocks = ?  WHERE Type_Vaccin = ? ";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        ps.setInt(1,v.getTotalStocks());
        ps.setString(2, TypeVaccin);
        
        //Éxécution de la requete
        return ps.executeUpdate() == 1; 
    }
    
    /**
     * Méthode permettant de mettre à jour la liste des vaccins
     * @param TypeVaccin Type du vaccin
     * @param NomFournisseur Nom du fournisseur
     * @param Quantiter Quantité demandé
     * @throws SQLException Exception SQL
     */
    public static void MiseAJourListeVaccins(String TypeVaccin, String NomFournisseur, int Quantiter) throws SQLException{
        
        Vaccins vaccin = ListeVaccins.stream().filter(x -> x.getTypeVaccin().equals(TypeVaccin)).findFirst().orElse(null);
        
        //Si le vaccin n'est pas trouver
        if (vaccin == null) {
            //On ajoute un vaccin
            AjouterUnVaccin(TypeVaccin, Quantiter);
        } else {
            //Sinon on ajoute la quantité
            AjouterDuStock(TypeVaccin, Quantiter);
        }
        
        FournisseursDAO.MiseAJourListeFournisseur(NomFournisseur, Quantiter);
        
        //On recharge la liste
        ChargerListeVaccins();
    }

    /**
     * Méthode permettant de récupérer la liste des vaccins
     * @return ArrayList - Liste des vaccins
     */
    public static ArrayList<Vaccins> getListeVaccins(){
        return new ArrayList<>(ListeVaccins);
    }
}
