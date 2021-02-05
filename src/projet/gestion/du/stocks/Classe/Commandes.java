/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

/**
 * Classe utilisé pour une commande
 *
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
     *
     * @param id Identifiant de la commande
     * @param client Nom du client de la commande
     * @param vaccin Type de vaccin de la commande
     * @param fournisseur Nom du fournisseur de la commande
     * @param nombreVaccinCommander Nombre de vaccin commandé
     * @param CommandeValider Vérifie si la commande est valider ou non
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
     *
     * @return int - L'identifiant
     */
    public int getId() {
        return id;
    }

    /**
     * Récupère le nom du client de la commande
     *
     * @return String - Le nom du client
     */
    public String getClient() {
        return client;
    }

    /**
     * Récupère le type de vaccin de la commande
     *
     * @return String - Le type de vaccin
     */
    public String getVaccin() {
        return vaccin;
    }

    /**
     * Récupère le nom du fournisseur de la commande
     *
     * @return String - Nom du fournisseur
     */
    public String getFournisseur() {
        return fournisseur;
    }

    /**
     * Récupère le nombre de vaccin de la commande
     *
     * @return int - Le nombre de vaccin
     */
    public int getNombreVaccinCommander() {
        return nombreVaccinCommander;
    }

    /**
     * Vérifie si une commande est valider
     *
     * @return boolean - Commande valider ?
     */
    public boolean isCommandeValider() {
        return CommandeValider;
    }
}
