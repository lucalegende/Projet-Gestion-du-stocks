/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package projet.gestion.du.stocks.Classe;

import java.util.Date;

/**
 *
 * @author Kalic
 */
public class Commandes {
    //Propriété de la classe Commandes
    private int id;
    private String client;
    private String vaccin;
    private String fournisseur;
    private int nombreVaccinCommander;
    private boolean CommandeValider;
    
    //Constructeur
    public Commandes(int id, String client, String vaccin, String fournisseur, int nombreVaccinCommander, boolean CommandeValider) {
        this.id = id;
        this.client = client;
        this.vaccin = vaccin;
        this.fournisseur = fournisseur;
        this.nombreVaccinCommander = nombreVaccinCommander;
        this.CommandeValider = CommandeValider;
    }
    
    //Méthodes
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }
    
    public String getClient() {
        return client;
    }

    public void setClient(String client) {
        this.client = client;
    }

    public String getVaccin() {
        return vaccin;
    }

    public void setVaccin(String vaccin) {
        this.vaccin = vaccin;
    }

    public String getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(String fournisseur) {
        this.fournisseur = fournisseur;
    }

    public int getNombreVaccinCommander() {
        return nombreVaccinCommander;
    }

    public void setNombreVaccinCommander(int nombreVaccinCommander) {
        this.nombreVaccinCommander = nombreVaccinCommander;
    }

    public boolean isCommandeValider() {
        return CommandeValider;
    }

    public void setCommandeValider(boolean CommandeValider) {
        this.CommandeValider = CommandeValider;
    }
}
