/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 * Classe utilisé pour un vaccin
 * @author Luca GRUNENWALD
 */
public class Vaccins {
    /**
     * Propriété de la classe vaccins
     */
    private final int id;
    private final String typeVaccin;
    private final int nombreCommander;
    private final int nombreDisponible;
    private int totalStocks;

    /**
     * Constructeur de la classe vaccins
     * @param id Identifiant du vaccin
     * @param typeVaccin Type du vaccin
     * @param nombreCommander Nombre de commande du vaccin
     * @param nombreDisponible Nombre disponible du vaccin
     * @param totalStocks total de stocks du vaccin
     */
    public Vaccins(int id, String typeVaccin, int nombreCommander, int nombreDisponible, int totalStocks) {
        this.id = id;
        this.typeVaccin = typeVaccin;
        this.nombreCommander = nombreCommander;
        this.nombreDisponible = nombreDisponible;
        this.totalStocks = totalStocks;
    }
    
    //Méthodes public
    /**
     * Récupére l'identifiant du vaccins
     * @return int - L'identifiant 
     */
    public int getId() {
        return id;
    }

    /**
     * Récupére le type de vaccin
     * @return String - Type du vaccin
     */
    public String getTypeVaccin() {
        return typeVaccin;
    }
    
    /**
     * Récupére le nombre de commande du vaccin
     * @return int - Nombre de vaccin commander
     */
    public int getNombreCommander() {
        return nombreCommander;
    }
    
    /**
     * Récupére le nombre disponible du vaccin
     * @return int - Nombre de vaccin disponible
     */
    public int getNombreDisponible() {
        return nombreDisponible;
    }

    /**
     * Récupére le total des sotcks du vaccin
     * @return int - Total du stocks du vaccin
     */
    public int getTotalStocks() {
        return totalStocks;
    }

    /**
     * Modifie le total des stocks du vaccin
     * @param totalStocks Total de stocks pour le vaccin
     */
    public void setTotalStocks(int totalStocks) {
        this.totalStocks = totalStocks;
    }
}
