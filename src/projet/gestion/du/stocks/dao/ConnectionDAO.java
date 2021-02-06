/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

/**
 * Classe pour la connection a la base de données
 *
 * @version 1.1
 * @author Luca GRUNENWALD
 */
public class ConnectionDAO {

    /**
     * Paramètres de connexion à la base de données mysql URL, LOGIN et PASS
     * sont des constantes
     */
    final static String URL = "jdbc:mysql://localhost:3306/";
    final static String BDD = "gestion_stocks";
    final static String LOGIN = "root";
    final static String PASS = "";
    private static final ConnectionDAO instance = null;
    private static Connection connexion;

    /**
     * Constructeur de la classe
     *
     * @throws SQLException Exception SQL
     * @throws ClassNotFoundException Exception Non trouver
     */
    public ConnectionDAO() throws SQLException, ClassNotFoundException {
        // chargement du pilote de bases de données et connection a la base de donnée
        Class.forName("com.mysql.cj.jdbc.Driver");
        
        //Création de la DataBase
        Connection c = DriverManager.getConnection(URL, LOGIN, PASS);
        CreationDataBase(c);
        c.close();
        
        //Connection a la base de données
        connexion = DriverManager.getConnection(URL+BDD, LOGIN, PASS);
        //Création des tables
        CreationTables();
    }
    
    /**
     * Creation de la base de donnée gestion_stocks
     * @param c Connection a la base
     */
    private static void CreationDataBase(Connection c){
        try {
            //Requete sql
            String sql = "CREATE DATABASE IF NOT EXISTS `gestion_stocks` DEFAULT CHARACTER SET utf8 COLLATE utf8_bin;";
            
            //Préparation de la requete
            PreparedStatement ps = c.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            
        }
    }
    
    /**
     * Création des tables dans la base de donnée gestion_stocks
     */
    private static void CreationTables(){
        try {
            //Requete sql Table achat
            String sql = "CREATE TABLE IF NOT EXISTS `achat` (\n" +
                        "  `Id_Vaccins` int(11) NOT NULL,\n" +
                        "  `Id_Fournisseurs` int(11) NOT NULL,\n" +
                        "  `Id_Commandes` int(11) NOT NULL,\n" +
                        "  `Commande_Valider` tinyint(1) NOT NULL,\n" +
                        "  PRIMARY KEY (`Id_Vaccins`,`Id_Fournisseurs`,`Id_Commandes`),\n" +
                        "  KEY `Achat_Fournisseurs0_FK` (`Id_Fournisseurs`),\n" +
                        "  KEY `Achat_Commandes1_FK` (`Id_Commandes`)\n" +
                        ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
            
            //Préparation de la requete
            PreparedStatement ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Requete sql Table commandes
            sql = "CREATE TABLE IF NOT EXISTS `commandes` (\n" +
                "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Client` varchar(255) COLLATE utf8_bin NOT NULL,\n" +
                "  `Nombre_Commander` int(11) NOT NULL,\n" +
                "  PRIMARY KEY (`Id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=16 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
            
            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Requete sql Table fournisseur
            sql = "CREATE TABLE IF NOT EXISTS `fournisseurs` (\n" +
                "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Nom_fournisseur` varchar(255) COLLATE utf8_bin NOT NULL,\n" +
                "  PRIMARY KEY (`Id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=17 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
            
            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Requete sql Table stocks
            sql = "CREATE TABLE IF NOT EXISTS `stocks` (\n" +
                "  `Id_Fournisseur` int(11) NOT NULL,\n" +
                "  `Id_Vaccin` int(11) NOT NULL,\n" +
                "  `Total_Stocks` int(11) NOT NULL,\n" +
                "  PRIMARY KEY (`Id_Fournisseur`,`Id_Vaccin`),\n" +
                "  KEY `stocks_Vaccins0_FK` (`Id_Vaccin`)\n" +
                ") ENGINE=InnoDB DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
            
            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Requete sql Table vaccins
            sql = "CREATE TABLE IF NOT EXISTS `vaccins` (\n" +
                "  `Id` int(11) NOT NULL AUTO_INCREMENT,\n" +
                "  `Type_Vaccin` varchar(255) COLLATE utf8_bin NOT NULL,\n" +
                "  PRIMARY KEY (`Id`)\n" +
                ") ENGINE=InnoDB AUTO_INCREMENT=8 DEFAULT CHARSET=utf8 COLLATE=utf8_bin;";
            
            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Clef étrangère
            //Requete sql clef étrangère achat
            sql = "ALTER TABLE `achat`\n" +
                "  ADD CONSTRAINT `Achat_Commandes1_FK` FOREIGN KEY (`Id_Commandes`) REFERENCES `commandes` (`Id`),\n" +
                "  ADD CONSTRAINT `Achat_Fournisseurs0_FK` FOREIGN KEY (`Id_Fournisseurs`) REFERENCES `fournisseurs` (`Id`),\n" +
                "  ADD CONSTRAINT `Achat_Vaccins_FK` FOREIGN KEY (`Id_Vaccins`) REFERENCES `vaccins` (`Id`);";
            
            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
            
            //Requete sql clef étrangère stocks
            sql = "ALTER TABLE `stocks`\n" +
                "  ADD CONSTRAINT `stocks_Fournisseurs_FK` FOREIGN KEY (`Id_Fournisseur`) REFERENCES `fournisseurs` (`Id`),\n" +
                "  ADD CONSTRAINT `stocks_Vaccins0_FK` FOREIGN KEY (`Id_Vaccin`) REFERENCES `vaccins` (`Id`);\n" +
                "COMMIT;";

            //Préparation de la requete
            ps = connexion.prepareStatement(sql);
            ps.executeUpdate();
        } catch (SQLException e) {
            // Erreur lors de la creation des tables ou alter
        }
    }
    
    /**
     * Méthodes permettant d'appeler une instance de la classe
     *
     * @return ConnectionDAO
     * @throws SQLException Exception SQL
     * @throws ClassNotFoundException Exception Non trouver
     */
    public static ConnectionDAO getInstance() throws SQLException, ClassNotFoundException {
        if (instance == null) {
            return new ConnectionDAO();
        } else {
            return instance;
        }
    }

    /**
     * Méthode permettant de récupérer la connection
     *
     * @return Connection
     */
    public static Connection getConnexion() {
        return connexion;
    }
}
