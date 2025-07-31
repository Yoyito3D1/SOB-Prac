package model.entities;

import jakarta.json.bind.annotation.JsonbTransient;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import java.util.Objects;
import jakarta.persistence.CascadeType;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.JoinTable;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;

@Entity
@Table(name = "RENTALS")
public class Rental implements Serializable {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @ManyToOne
    @JoinColumn(name = "CUSTOMER_ID")
    private Customer customer;

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(name = "RENTAL_VIDEOGAME", joinColumns = @JoinColumn(name = "RENTAL_ID"), inverseJoinColumns = @JoinColumn(name = "VIDEOGAME_ID"))
    private List<Game> videojocs;

    private Date dataAlquiler;
    private Date dataTornada;
    private double preuTotal;

    public Rental() {
       
    }
    
    public Rental(List<Game> videojocs, Customer customer, Date rentalDate, Date returnDate, double preuTotal) {
        this.videojocs = videojocs;
        this.customer = customer;
        this.dataAlquiler = rentalDate;
        this.dataTornada = returnDate;
        this.preuTotal = preuTotal;

    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public Customer getCustomer() {
        return customer;
    }

    public void setCustomer(Customer customer) {
        this.customer = customer;
    }

    public List<Game> getVideojocs() {
        return videojocs;
    }

    public void setVideojocs(List<Game> videojocs) {
        this.videojocs = videojocs;
    }

    public Date getDataAlquiler() {
        return dataAlquiler;
    }

    public void setDataAlquiler(Date dataAlquiler) {
        this.dataAlquiler = dataAlquiler;
    }

    public Date getDataTornada() {
        return dataTornada;
    }

    public void setDataTornada(Date dataTornada) {
        this.dataTornada = dataTornada;
    }

    public double getPreuTotal() {
        return preuTotal;
    }

    public void setPreuTotal(double preuTotal) {
        this.preuTotal = preuTotal;
    }
    
    @Override
    public int hashCode() {
        return Objects.hash(id, dataAlquiler, dataTornada);
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj) {
            return true;
        }
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Rental other = (Rental) obj;
        return this.id == other.id;
    }

    @Override
    public String toString() {
        return "Rental{" +
                "id=" + id +
                ", rentalDate=" + dataAlquiler +
                ", returnDate=" + dataTornada +
                ", customer=" + customer +
                '}';
    }
}
