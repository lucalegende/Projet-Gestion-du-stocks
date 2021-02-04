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
import projet.gestion.du.stocks.Classe.Commandes;
/**
 *
 * @author Kalictong
 */
public class CommandesDAO {
    
    //Propriété privée
    private static ArrayList<Commandes> ListeCommandes;
    
    //Méthodes : Récupération des données de la base
    public static void ChargerListeCommandes() throws SQLException{         
        //On initalise la liste des commandes
        ListeCommandes = new ArrayList<>();
        
        //On essaye de se connecter
        Connection connexion = ConnectionDAO.getInstance().getConnexion();
        
        //Requete SQL pour la liste des fournisseurs
        String sql = "SELECT C.Id, C.Client, F.Nom_fournisseur, V.Type_Vaccin, C.Nombre_Commander, A.Commande_Valider\n" +
                     "FROM achat AS A\n" +
                     "INNER JOIN commandes AS C ON C.Id = A.Id_Commandes\n" +
                     "INNER JOIN fournisseurs AS F ON F.Id = A.Id_Fournisseurs\n" +
                     "INNER JOIN vaccins AS V ON V.Id = A.Id_Vaccins";
        
        //Préparation de la requete
        PreparedStatement ps = connexion.prepareStatement(sql);
        
        //Éxécution de la requete
        ResultSet rs = ps.executeQuery();
        
        //on parcourt les lignes du résultat
        while(rs.next())
                ListeCommandes.add(new Commandes(rs.getInt("Id"), rs.getString("Client"), rs.getString("Type_Vaccin"), rs.getString("Nom_fournisseur"), rs.getInt("Nombre_Commander"), rs.getBoolean("Commande_Valider")));
    }
    
    //Méthodes : Mise à jour des données dans la base
    public static void AjouterUneCommande(Commandes commande){
        
    }
    
    public static void ValiderUneCommande(Commandes commande){
        
    }
    
    public static void AnnulerUneCommande(Commandes commande){
        
    }
    
    //Méthodes général
    public static ArrayList<Commandes> getListeCommandes(){
        return new ArrayList<>(ListeCommandes);
    }
}
