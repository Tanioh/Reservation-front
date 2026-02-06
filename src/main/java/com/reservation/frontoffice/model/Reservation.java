package com.reservation.frontoffice.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDate;
import java.time.LocalDateTime;

@Entity
@Table(name = "reservation")
public class Reservation {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_reservation")
    private Integer idReservation;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_client", nullable = false)
    @NotNull
    private Client client;

    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "id_voiture", nullable = false)
    @NotNull
    private Voiture voiture;

    @Column(name = "date_debut", nullable = false)
    @NotNull
    private LocalDate dateDebut;

    @Column(name = "date_fin", nullable = false)
    @NotNull
    private LocalDate dateFin;

    @Column(name = "montant_total", precision = 10, scale = 2)
    private Double montantTotal;

    @Column(length = 30)
    private String statut;

    @Column(name = "date_reservation")
    private LocalDateTime dateReservation;

    @Column(columnDefinition = "TEXT")
    private String commentaire;

    public Reservation() {}

    // Getters & Setters
    public Integer getIdReservation() { return idReservation; }
    public void setIdReservation(Integer idReservation) { this.idReservation = idReservation; }

    public Client getClient() { return client; }
    public void setClient(Client client) { this.client = client; }

    public Voiture getVoiture() { return voiture; }
    public void setVoiture(Voiture voiture) { this.voiture = voiture; }

    public LocalDate getDateDebut() { return dateDebut; }
    public void setDateDebut(LocalDate dateDebut) { this.dateDebut = dateDebut; }

    public LocalDate getDateFin() { return dateFin; }
    public void setDateFin(LocalDate dateFin) { this.dateFin = dateFin; }

    public Double getMontantTotal() { return montantTotal; }
    public void setMontantTotal(Double montantTotal) { this.montantTotal = montantTotal; }

    public String getStatut() { return statut; }
    public void setStatut(String statut) { this.statut = statut; }

    public LocalDateTime getDateReservation() { return dateReservation; }
    public void setDateReservation(LocalDateTime dateReservation) { this.dateReservation = dateReservation; }

    public String getCommentaire() { return commentaire; }
    public void setCommentaire(String commentaire) { this.commentaire = commentaire; }
}
