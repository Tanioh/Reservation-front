package com.reservation.frontoffice.service;

import com.reservation.frontoffice.model.Reservation;
import com.reservation.frontoffice.repository.ReservationRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.temporal.ChronoUnit;
import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class ReservationService {

    private final ReservationRepository reservationRepository;

    public ReservationService(ReservationRepository reservationRepository) {
        this.reservationRepository = reservationRepository;
    }

    /**
     * Lister toutes les réservations.
     */
    public List<Reservation> findAll() {
        return reservationRepository.findAllWithRelations();
    }

    /**
     * Rechercher avec filtres.
     */
    public List<Reservation> findByFilters(LocalDate dateDebut, LocalDate dateFin, String statut) {
        return reservationRepository.findByFilters(dateDebut, dateFin, statut);
    }

    /**
     * Trouver par ID.
     */
    public Optional<Reservation> findById(Integer id) {
        return reservationRepository.findById(id);
    }

    /**
     * Créer une nouvelle réservation.
     * Calcule automatiquement le montant total.
     */
    public Reservation creerReservation(Reservation reservation) {
        // Date de réservation
        reservation.setDateReservation(LocalDateTime.now());

        // Calcul du montant si non défini
        if (reservation.getMontantTotal() == null || reservation.getMontantTotal() <= 0) {
            long jours = ChronoUnit.DAYS.between(reservation.getDateDebut(), reservation.getDateFin());
            if (jours > 0 && reservation.getVoiture() != null) {
                reservation.setMontantTotal(jours * reservation.getVoiture().getPrixJournalier());
            }
        }

        // Statut par défaut
        if (reservation.getStatut() == null || reservation.getStatut().isEmpty()) {
            reservation.setStatut("EN_ATTENTE");
        }

        return reservationRepository.save(reservation);
    }

    /**
     * Réservations d'un client.
     */
    public List<Reservation> findByClient(Integer idClient) {
        return reservationRepository.findByClientIdClientOrderByDateReservationDesc(idClient);
    }
}
