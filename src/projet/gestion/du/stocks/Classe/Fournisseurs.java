/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 * Classe Fournisseur
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
     * @param id
     * @param nomFournisseur
     * @param nombreCommander
     * @param nombreDisponible
     * @param totalStocks 
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
     * @return id
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom du fournisseur
     * @return nomFournisseur
     */
    public String getNomFournisseur() {
        return nomFournisseur;
    }

    /**
     * Récupère le nombre de commander passer chez le fournisseur
     * @return nombreCommander
     */
    public int getNombreCommander() {
        return nombreCommander;
    }

    /**
     * Récupère le nombre disponible de vaccin du fournisseur
     * @return nombreDisponible
     */
    public int getNombreDisponible() {
        return nombreDisponible;
    }

    /**
     * Récupère le nombre total de stocks de vaccin du fournissuer
     * @return totalStocks
     */
    public int getTotalStocks() {
        return totalStocks;
    }

    /**
     * Modifie le nombre total de stocks du fournisseur
     * @param totalStocks 
     */
    public void setTotalStocks(int totalStocks) {
        this.totalStocks = totalStocks;
    }
    
}
