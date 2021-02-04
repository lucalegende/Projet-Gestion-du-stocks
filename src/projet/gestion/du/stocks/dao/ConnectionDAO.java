/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

/**
 * Classe d'acc�s aux donn�es contenues dans la table article
 * @version 1.1
 * @author Kalictong
 */
public class ConnectionDAO {
    /**
	 * Param�tres de connexion � la base de donn�es oracle
	 * URL, LOGIN et PASS sont des constantes
	 */
	final static String URL = "jdbc:mysql://localhost:3306/database";
	final static String LOGIN = "root";
	final static String PASS = "";
        private static ConnectionDAO instance = null;
        private static Connection connexion;
        
	/**
	 * Constructeur de la classe
	 * 
	 */
	public ConnectionDAO() throws SQLException
	{
            // chargement du pilote de bases de données et connection a la base de donnée
            try {
                Class.forName("com.mysql.cj.jdbc.Driver");
                //Connection a la base de données
                connexion = DriverManager.getConnection(URL,LOGIN,PASS);
            } catch (ClassNotFoundException e2) {
                System.err.println("Impossible de charger le pilote de BDD, ne pas oublier d'importer le fichier .jar dans le projet");
            }
	}
        
        public static ConnectionDAO getInstance() throws SQLException {
            if(instance == null)
                return new ConnectionDAO();
            else
                return instance;
        }

        public static Connection getConnexion() {
            return connexion;
        }
}
