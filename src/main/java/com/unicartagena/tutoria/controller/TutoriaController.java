package com.unicartagena.tutoria.controller;

import com.unicartagena.tutoria.model.Tutoria;
import com.unicartagena.tutoria.repository.TutoriaRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/tutorias")
public class TutoriaController {

    @Autowired
    private TutoriaRepository tutoriaRepo;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        model.addAttribute("tutorias", tutoriaRepo.findAll());
        return "tutorias/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        model.addAttribute("tutoria", new Tutoria());
        model.addAttribute("titulo", "Registrar Nueva Tutoria");
        return "tutorias/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Tutoria tutoria, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        tutoriaRepo.save(tutoria);
        return "redirect:/tutorias?guardado=true";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        tutoriaRepo.findById(id).ifPresent(t -> {
            model.addAttribute("tutoria", t);
            model.addAttribute("titulo", "Modificar Tutoria");
        });
        return "tutorias/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        tutoriaRepo.deleteById(id);
        return "redirect:/tutorias?eliminado=true";
    }
}
