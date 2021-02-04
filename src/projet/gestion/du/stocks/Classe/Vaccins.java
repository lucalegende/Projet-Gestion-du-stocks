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
public class Vaccins {
    //Propriété de la classe vaccins
    private int id;
    private String typeVaccin;
    private int nombreDisponible;
    private int totalStocks;

    //Constructeur
    public Vaccins(int id, String typeVaccin, int nombreDisponible, int totalStocks) {
        this.id = id;
        this.typeVaccin = typeVaccin;
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

    public String getTypeVaccin() {
        return typeVaccin;
    }

    public void setTypeVaccin(String typeVaccin) {
        this.typeVaccin = typeVaccin;
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
