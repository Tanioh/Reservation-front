package com.reservation.frontoffice.controller;

import com.reservation.frontoffice.model.Voiture;
import com.reservation.frontoffice.repository.VoitureRepository;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.List;

/**
 * Contrôleur MVC pour la page d'accueil (Spring MVC + Thymeleaf).
 */
@Controller
public class HomeController {

    private final VoitureRepository voitureRepository;

    public HomeController(VoitureRepository voitureRepository) {
        this.voitureRepository = voitureRepository;
    }

    @GetMapping("/")
    public String home(Model model) {
        List<Voiture> voituresDisponibles = voitureRepository.findByDisponibleTrue();
        model.addAttribute("voitures", voituresDisponibles);
        return "index";
    }

    @GetMapping("/reservations")
    public String reservationsPage() {
        return "reservations";
    }

    @GetMapping("/nouvelle-reservation")
    public String nouvelleReservationPage() {
        return "nouvelle-reservation";
    }
}
