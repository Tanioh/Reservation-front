package com.reservation.frontoffice.controller;

import com.reservation.frontoffice.model.Client;
import com.reservation.frontoffice.model.Reservation;
import com.reservation.frontoffice.model.Voiture;
import com.reservation.frontoffice.repository.ClientRepository;
import com.reservation.frontoffice.repository.VoitureRepository;
import com.reservation.frontoffice.service.ReservationService;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * API REST pour les réservations (consommée par Vue.js côté frontend).
 */
@RestController
@RequestMapping("/api/reservations")
public class ReservationApiController {

    private final ReservationService reservationService;
    private final ClientRepository clientRepository;
    private final VoitureRepository voitureRepository;

    public ReservationApiController(ReservationService reservationService,
                                     ClientRepository clientRepository,
                                     VoitureRepository voitureRepository) {
        this.reservationService = reservationService;
        this.clientRepository = clientRepository;
        this.voitureRepository = voitureRepository;
    }

    /**
     * GET /api/reservations
     * Liste toutes les réservations avec filtres optionnels.
     */
    @GetMapping
    public ResponseEntity<List<Reservation>> list(
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateDebut,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate dateFin,
            @RequestParam(required = false) String statut) {

        List<Reservation> reservations;
        if (dateDebut != null || dateFin != null || (statut != null && !statut.isEmpty())) {
            reservations = reservationService.findByFilters(dateDebut, dateFin, statut);
        } else {
            reservations = reservationService.findAll();
        }
        return ResponseEntity.ok(reservations);
    }

    /**
     * GET /api/reservations/{id}
     */
    @GetMapping("/{id}")
    public ResponseEntity<Reservation> getById(@PathVariable Integer id) {
        return reservationService.findById(id)
                .map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    /**
     * POST /api/reservations
     * Créer une nouvelle réservation.
     */
    @PostMapping
    public ResponseEntity<?> create(@RequestBody Map<String, Object> payload) {
        try {
            Integer idClient = ((Number) payload.get("idClient")).intValue();
            Integer idVoiture = ((Number) payload.get("idVoiture")).intValue();
            String dateDebutStr = (String) payload.get("dateDebut");
            String dateFinStr = (String) payload.get("dateFin");
            String commentaire = (String) payload.get("commentaire");

            Optional<Client> client = clientRepository.findById(idClient);
            Optional<Voiture> voiture = voitureRepository.findById(idVoiture);

            if (client.isEmpty() || voiture.isEmpty()) {
                return ResponseEntity.badRequest().body(Map.of("error", "Client ou voiture introuvable"));
            }

            Reservation reservation = new Reservation();
            reservation.setClient(client.get());
            reservation.setVoiture(voiture.get());
            reservation.setDateDebut(LocalDate.parse(dateDebutStr));
            reservation.setDateFin(LocalDate.parse(dateFinStr));
            reservation.setCommentaire(commentaire);

            Reservation saved = reservationService.creerReservation(reservation);
            return ResponseEntity.ok(saved);

        } catch (Exception e) {
            return ResponseEntity.badRequest().body(Map.of("error", e.getMessage()));
        }
    }

    /**
     * GET /api/clients
     */
    @GetMapping("/clients")
    public ResponseEntity<List<Client>> listClients() {
        return ResponseEntity.ok(clientRepository.findAll());
    }

    /**
     * GET /api/voitures
     */
    @GetMapping("/voitures")
    public ResponseEntity<List<Voiture>> listVoitures() {
        return ResponseEntity.ok(voitureRepository.findByDisponibleTrue());
    }
}
