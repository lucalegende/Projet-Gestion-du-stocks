/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 * Classe utilisé pour un fournisseur
 * @author Luca GRUNENWALD
 */
public class Fournisseurs {
    /**
     * Propriété de la classe fournisseurs
     */
    private final int id;
    private final String nomFournisseur;
    private final int nombreCommander;
    private final int nombreDisponible;
    private int totalStocks;
    
    /**
     * Constructeur de la classe fournisseurs
     * @param id Identifiant du fournisseur
     * @param nomFournisseur Nom du fournisseur
     * @param nombreCommander Nombre de commande lié au fournisseur
     * @param nombreDisponible Nombre de vaccins disponible du fournisseur
     * @param totalStocks Total de stocks de vaccin du fournisseur
     */
    public Fournisseurs(int id, String nomFournisseur, int nombreCommander, int nombreDisponible, int totalStocks) {
        this.id = id;
        this.nomFournisseur = nomFournisseur;
        this.nombreCommander = nombreCommander;
        this.nombreDisponible = nombreDisponible;
        this.totalStocks = totalStocks;
    }
    
    //Méthodes public
    /**
     * Récupère l'identifiant du fournisseur
     * @return int - L'identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom du fournisseur
     * @return String - Nom du fournisseur
     */
    public String getNomFournisseur() {
        return nomFournisseur;
    }

    /**
     * Récupère le nombre de commander passer chez le fournisseur
     * @return int - Nombre de commande
     */
    public int getNombreCommander() {
        return nombreCommander;
    }

    /**
     * Récupère le nombre disponible de vaccin du fournisseur
     * @return int - Nombre de vaccin disponible
     */
    public int getNombreDisponible() {
        return nombreDisponible;
    }

    /**
     * Récupère le nombre total de stocks de vaccin du fournissuer
     * @return int - Total de stocks du vaccin
     */
    public int getTotalStocks() {
        return totalStocks;
    }

    /**
     * Modifie le nombre total de stocks du fournisseur
     * @param totalStocks Total de stocks pour le fournisseur
     */
    public void setTotalStocks(int totalStocks) {
        this.totalStocks = totalStocks;
    }
    
}
