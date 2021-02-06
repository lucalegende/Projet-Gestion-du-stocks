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
    final static String URL = "jdbc:mysql://localhost:3306/database";
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
        //Connection a la base de données
        connexion = DriverManager.getConnection(URL, LOGIN, PASS);
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
