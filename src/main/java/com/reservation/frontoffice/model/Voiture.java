package com.reservation.frontoffice.model;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import jakarta.persistence.*;
import java.time.LocalDateTime;

@Entity
@Table(name = "voiture")
public class Voiture {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_voiture")
    private Integer idVoiture;

    @Column(nullable = false, length = 80)
    private String marque;

    @Column(nullable = false, length = 80)
    private String modele;

    @Column(nullable = false, unique = true, length = 20)
    private String immatriculation;

    @Column(length = 50)
    private String couleur;

    @Column(name = "nb_places")
    private Integer nbPlaces;

    @Column(name = "prix_journalier", nullable = false)
    private Double prixJournalier;

    @Column
    private Boolean disponible;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_hotel")
    @JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
    private Hotel hotel;

    @Column(name = "date_ajout")
    private LocalDateTime dateAjout;

    public Voiture() {}

    // Getters & Setters
    public Integer getIdVoiture() { return idVoiture; }
    public void setIdVoiture(Integer idVoiture) { this.idVoiture = idVoiture; }

    public String getMarque() { return marque; }
    public void setMarque(String marque) { this.marque = marque; }

    public String getModele() { return modele; }
    public void setModele(String modele) { this.modele = modele; }

    public String getImmatriculation() { return immatriculation; }
    public void setImmatriculation(String immatriculation) { this.immatriculation = immatriculation; }

    public String getCouleur() { return couleur; }
    public void setCouleur(String couleur) { this.couleur = couleur; }

    public Integer getNbPlaces() { return nbPlaces; }
    public void setNbPlaces(Integer nbPlaces) { this.nbPlaces = nbPlaces; }

    public Double getPrixJournalier() { return prixJournalier; }
    public void setPrixJournalier(Double prixJournalier) { this.prixJournalier = prixJournalier; }

    public Boolean getDisponible() { return disponible; }
    public void setDisponible(Boolean disponible) { this.disponible = disponible; }

    public Hotel getHotel() { return hotel; }
    public void setHotel(Hotel hotel) { this.hotel = hotel; }

    public LocalDateTime getDateAjout() { return dateAjout; }
    public void setDateAjout(LocalDateTime dateAjout) { this.dateAjout = dateAjout; }

    public String getNomComplet() {
        return marque + " " + modele;
    }
}
