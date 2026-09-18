package com.unicartagena.tutoria.controller;

import com.unicartagena.tutoria.model.Usuario;
import com.unicartagena.tutoria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/usuarios")
public class UsuarioController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping
    public String listar(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        model.addAttribute("usuarios", usuarioRepo.findAll());
        return "usuarios/listar";
    }

    @GetMapping("/nuevo")
    public String nuevo(HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        model.addAttribute("usuario", new Usuario());
        model.addAttribute("titulo", "Registrar Nuevo Usuario");
        return "usuarios/formulario";
    }

    @PostMapping("/guardar")
    public String guardar(@ModelAttribute Usuario usuario, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        usuarioRepo.save(usuario);
        return "redirect:/usuarios?guardado=true";
    }

    @GetMapping("/editar/{id}")
    public String editar(@PathVariable Long id, HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        usuarioRepo.findById(id).ifPresent(u -> {
            model.addAttribute("usuario", u);
            model.addAttribute("titulo", "Modificar Usuario");
        });
        return "usuarios/formulario";
    }

    @GetMapping("/eliminar/{id}")
    public String eliminar(@PathVariable Long id, HttpSession session) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";
        usuarioRepo.deleteById(id);
        return "redirect:/usuarios?eliminado=true";
    }
}
