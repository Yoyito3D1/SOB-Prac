package deim.urv.cat.homework2.model;

import java.util.Date;
import java.util.List;

public class Rental {
    private Long id;
    private List<String> videogames;
    private Date dataAlquiler;
    private Date dataTornada;
    private double preuTotal;

    public Rental() {}
    
    public Rental(Long id, List<String> videogames,  Date dataAlquiler, Date dataTornada, double preuTotal) {
        this.id = id;
        this.videogames = videogames;
        this.dataAlquiler = dataAlquiler;
        this.dataTornada = dataTornada;
        this.preuTotal = preuTotal;
    }

    public double getPreuTotal() { return preuTotal; }

    public void setPreuTotal(double preuTotal) { this.preuTotal = preuTotal; }

    public Long getId() { return id; }

    public void setId(Long id) { this.id = id; }

    public List<String> getVideogames() {return videogames; }

    public void setVideogames(List<String> videogames) { this.videogames = videogames; }

    public Date getDataAlquiler() {return dataAlquiler; }

    public void setDataAlquiler(Date dataAlquiler) { this.dataAlquiler = dataAlquiler;  }

    public Date getDataTornada() { return dataTornada; }

    public void setDataTornada(Date dataTornada) { this.dataTornada = dataTornada; }
}
