package com.unicartagena.tutoria.controller;

import com.unicartagena.tutoria.model.Usuario;
import com.unicartagena.tutoria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.Optional;

@Controller
public class LoginController {

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/")
    public String index(HttpSession session) {
        if (session.getAttribute("usuarioLogueado") != null) {
            return "redirect:/tutorias";
        }
        return "redirect:/login";
    }

    @GetMapping("/login")
    public String loginForm() {
        return "login";
    }

    @PostMapping("/login")
    public String loginSubmit(@RequestParam String email, @RequestParam String clave,
                              HttpSession session, Model model) {
        Optional<Usuario> userOpt = usuarioRepo.findByEmailAndClave(email.trim(), clave.trim());
        if (userOpt.isPresent()) {
            Usuario u = userOpt.get();
            session.setAttribute("usuarioLogueado", u);
            session.setAttribute("nombre", u.getNombre());
            session.setAttribute("rol", u.getRol());
            return "redirect:/tutorias";
        } else {
            model.addAttribute("error", "Credenciales incorrectas. Verifique correo o clave.");
            return "login";
        }
    }

    @GetMapping("/logout")
    public String logout(HttpSession session) {
        session.invalidate();
        return "redirect:/login?logout=true";
    }

    @GetMapping("/recuperar")
    public String recuperarForm() {
        return "recuperar";
    }

    @PostMapping("/recuperar")
    public String recuperarSubmit(@RequestParam String email, Model model) {
        Optional<Usuario> uOpt = usuarioRepo.findByEmail(email.trim());
        if (uOpt.isPresent()) {
            Usuario u = uOpt.get();
            model.addAttribute("mensajeExito", "Recordatorio enviado a " + email + ". [DEMO: Clave: '" + u.getClave() + "']");
        } else {
            model.addAttribute("error", "No existe ningun usuario con ese correo registrado.");
        }
        return "recuperar";
    }
}
