/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 *
 * @author Kalic
 */
public class Fournisseurs {
    //Propriété de la classe fournisseurs
    private int id;
    private String nomFounrisseur;
    private int nombreDisponible;
    private int totalStocks;
    
    //Constructeur
    public Fournisseurs(int id, String nomFounrisseur, int nombreDisponible, int totalStocks) {
        this.id = id;
        this.nomFounrisseur = nomFounrisseur;
        this.nombreDisponible = nombreDisponible;
        this.totalStocks = totalStocks;
    }
    
    //Méthodes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNomFounrisseur() {
        return nomFounrisseur;
    }

    public void setNomFounrisseur(String nomFounrisseur) {
        this.nomFounrisseur = nomFounrisseur;
    }

    public int getNombreDisponible() {
        return nombreDisponible;
    }

    public void setNombreDisponible(int nombreDisponible) {
        this.nombreDisponible = nombreDisponible;
    }

    public int getTotalStocks() {
        return totalStocks;
    }

    public void setTotalStocks(int totalStocks) {
        this.totalStocks = totalStocks;
    }
    
}
