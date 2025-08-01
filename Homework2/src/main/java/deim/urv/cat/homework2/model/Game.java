package deim.urv.cat.homework2.model;

import java.io.Serializable;

public class Game implements Serializable {
    private String nom;
    private String consola;
    private String genere; 
    private String disponibilitat;
    private double preuLloguerSetmanal;
    private String imageURL;
    private String descripcio;

    public String getDescripcio() {  return descripcio; }

    public void setDescripcio(String descripcio) { this.descripcio = descripcio; }

    public String getGenere() { return genere; }

    public void setGenere(String genere) { this.genere = genere; }

    public String getImageURL() { return imageURL; }

    public void setImageURL(String imageURL) { this.imageURL = imageURL; }

    public String getNom() {  return nom; }

    public void setNom(String nom) {  this.nom = nom; }

    public String getConsola() { return consola; }

    public void setConsola(String consola) { this.consola = consola; }

    public String getDisponibilitat() { return disponibilitat; }

    public void setDisponibilitat(String disponibilitat) { this.disponibilitat = disponibilitat; }

    public double getPreuLloguerSetmanal() {  return preuLloguerSetmanal; }

    public void setPreuLloguerSetmanal(double preuLloguerSetmanal) { this.preuLloguerSetmanal = preuLloguerSetmanal; }
    
    
    
}
