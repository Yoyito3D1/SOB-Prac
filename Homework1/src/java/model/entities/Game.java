package model.entities;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import java.io.Serializable;

@Entity
@Table(name = "GAME")
public class Game implements Serializable {
    
    @Id
    @SequenceGenerator(name = "Game_Gen", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "Game_Gen")
    private long id;
    
    @NotNull
    @Column(unique = true)
    private String nom;
    private String consola;
    
    private String genere;
    private String imageURL;
   
    private String descripcio;
    private String disponibilitat;
    private float preuLloguerSetmanal;
    
    public Game() {
    
    }

    public Game(String nom, String consola, String genere, String imageURL, String descripcio, String disponibilitat, float preuLloguerSetmanal) {
        this.nom = nom;
        this.consola = consola;
        this.genere = genere;
        this.imageURL = imageURL;
        this.descripcio = descripcio;
        this.disponibilitat = disponibilitat;
        this.preuLloguerSetmanal = preuLloguerSetmanal;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getConsola() {
        return consola;
    }

    public void setConsola(String consola) {
        this.consola = consola;
    }

    public String getGenere() {
        return genere;
    }

    public void setGenere(String genere) {
        this.genere = genere;
    }
    
    public String getImageURL(){
        return imageURL;
    }
    
    public void setImageURL(String imageURL){
        this.imageURL = imageURL;
    }

    public String getDescripcio() {
        return descripcio;
    }

    public void setDescripcio(String descripcio) {
        this.descripcio = descripcio;
    }

    public String getDisponibilitat() {
        return disponibilitat;
    }

    public void setDisponibilitat(String disponibilitat) {
        this.disponibilitat = disponibilitat;
    }

    public float getPreuLloguerSetmanal() {
        return preuLloguerSetmanal;
    }

    public void setPreuLloguerSetmanal(float preuLloguerSetmanal) {
        this.preuLloguerSetmanal = preuLloguerSetmanal;
    }

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((nom == null) ? 0 : nom.hashCode());
        result = prime * result + ((consola == null) ? 0 : consola.hashCode());
        result = prime * result + ((genere == null) ? 0 : genere.hashCode());
        result = prime * result + ((descripcio == null) ? 0 : descripcio.hashCode());
        result = prime * result + ((disponibilitat == null) ? 0 : disponibilitat.hashCode());
        result = prime * result + Float.floatToIntBits(preuLloguerSetmanal);
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null || getClass() != obj.getClass())
            return false;

        Game other = (Game) obj;

        if (disponibilitat != other.disponibilitat)
            return false;
        if (Float.floatToIntBits(preuLloguerSetmanal) != Float.floatToIntBits(other.preuLloguerSetmanal))
            return false;
        if (nom == null) {
            if (other.nom != null)
                return false;
        } else if (!nom.equals(other.nom))
            return false;
        if (consola == null) {
            if (other.consola != null)
                return false;
        } else if (!consola.equals(other.consola))
            return false;
        if (genere == null) {
            if (other.genere != null)
                return false;
        } else if (!genere.equals(other.genere))
            return false;
        if (descripcio == null) {
            return other.descripcio == null;
        } else return descripcio.equals(other.descripcio);
    }
}

