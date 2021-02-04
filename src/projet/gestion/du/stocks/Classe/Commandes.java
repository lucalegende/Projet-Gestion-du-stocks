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
    private Clients client;
    private Vaccins vaccin;
    private Fournisseurs fournisseur;
    private Date dateHeureCommande;
    private int nombreVaccinCommander;
    
    //Constructeur
    public Commandes(Clients client, Vaccins vaccin, Fournisseurs fournisseur, Date dateHeureCommande, int nombreVaccinCommander) {
        this.client = client;
        this.vaccin = vaccin;
        this.fournisseur = fournisseur;
        this.dateHeureCommande = dateHeureCommande;
        this.nombreVaccinCommander = nombreVaccinCommander;
    }
    
    //Méthodes
    public Clients getClient() {
        return client;
    }

    public void setClient(Clients client) {
        this.client = client;
    }

    public Vaccins getVaccin() {
        return vaccin;
    }

    public void setVaccin(Vaccins vaccin) {
        this.vaccin = vaccin;
    }

    public Fournisseurs getFournisseur() {
        return fournisseur;
    }

    public void setFournisseur(Fournisseurs fournisseur) {
        this.fournisseur = fournisseur;
    }

    public Date getDateHeureCommande() {
        return dateHeureCommande;
    }

    public void setDateHeureCommande(Date dateHeureCommande) {
        this.dateHeureCommande = dateHeureCommande;
    }

    public int getNombreVaccinCommander() {
        return nombreVaccinCommander;
    }

    public void setNombreVaccinCommander(int nombreVaccinCommander) {
        this.nombreVaccinCommander = nombreVaccinCommander;
    }
}
