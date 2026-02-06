package com.reservation.frontoffice.repository;

import com.reservation.frontoffice.model.Reservation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.time.LocalDate;
import java.util.List;

@Repository
public interface ReservationRepository extends JpaRepository<Reservation, Integer> {

    /**
     * Rechercher les réservations avec filtres optionnels par date et statut.
     */
    @Query("SELECT r FROM Reservation r " +
           "JOIN FETCH r.client " +
           "JOIN FETCH r.voiture " +
           "WHERE (:dateDebut IS NULL OR r.dateDebut >= :dateDebut) " +
           "AND (:dateFin IS NULL OR r.dateFin <= :dateFin) " +
           "AND (:statut IS NULL OR :statut = '' OR r.statut = :statut) " +
           "ORDER BY r.dateReservation DESC")
    List<Reservation> findByFilters(
            @Param("dateDebut") LocalDate dateDebut,
            @Param("dateFin") LocalDate dateFin,
            @Param("statut") String statut
    );

    /**
     * Toutes les réservations avec les relations chargées.
     */
    @Query("SELECT r FROM Reservation r JOIN FETCH r.client JOIN FETCH r.voiture ORDER BY r.dateReservation DESC")
    List<Reservation> findAllWithRelations();

    /**
     * Réservations d'un client.
     */
    List<Reservation> findByClientIdClientOrderByDateReservationDesc(Integer idClient);
}
