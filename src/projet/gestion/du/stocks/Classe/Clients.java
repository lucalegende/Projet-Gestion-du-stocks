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
public class Clients {
    //Propriété de la classe
    private int ID;
    private String nomClient;

    //Constructeur
    public Clients(int ID, String nomClient) {
        this.ID = ID;
        this.nomClient = nomClient;
    }

    //Méthodes
    public int getID() {
        return ID;
    }

    public void setID(int ID) {
        this.ID = ID;
    }

    public String getNomClient() {
        return nomClient;
    }

    public void setNomClient(String nomClient) {
        this.nomClient = nomClient;
    }
}
