/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 * Classe Commandes
 * @author Luca GRUNENWALD
 */
public class Commandes {
    /**
     * Propriété de la classe Commandes
     */
    private final int id;
    private final String client;
    private final String vaccin;
    private final String fournisseur;
    private final int nombreVaccinCommander;
    private final boolean CommandeValider;
    
    /**
     * Constructeur de la classe commandes
     * @param id
     * @param client
     * @param vaccin
     * @param fournisseur
     * @param nombreVaccinCommander
     * @param CommandeValider 
     */
    public Commandes(int id, String client, String vaccin, String fournisseur, int nombreVaccinCommander, boolean CommandeValider) {
        this.id = id;
        this.client = client;
        this.vaccin = vaccin;
        this.fournisseur = fournisseur;
        this.nombreVaccinCommander = nombreVaccinCommander;
        this.CommandeValider = CommandeValider;
    }
    
    //Méthodes public
    /**
     * Récupère l'identifiant de la commande
     * @return id
     */
    public int getId() {
        return id;
    }
    
    /**
     * Récupère le nom du client de la commande
     * @return client
     */
    public String getClient() {
        return client;
    }

    /**
     * Récupère le type de vaccin de la commande
     * @return vaccin
     */
    public String getVaccin() {
        return vaccin;
    }

    /**
     * Récupère le nom du fournisseur de la commande
     * @return fournisseur
     */
    public String getFournisseur() {
        return fournisseur;
    }

    /**
     * Récupère le nombre de vaccin de la commande
     * @return nombreVaccinCommander
     */
    public int getNombreVaccinCommander() {
        return nombreVaccinCommander;
    }

    /**
     * 
     * @return CommandeValider
     */
    public boolean isCommandeValider() {
        return CommandeValider;
    }
}
