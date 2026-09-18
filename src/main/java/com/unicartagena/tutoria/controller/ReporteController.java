package com.unicartagena.tutoria.controller;

import com.unicartagena.tutoria.repository.TutoriaRepository;
import com.unicartagena.tutoria.repository.UsuarioRepository;
import jakarta.servlet.http.HttpSession;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ReporteController {

    @Autowired
    private TutoriaRepository tutoriaRepo;

    @Autowired
    private UsuarioRepository usuarioRepo;

    @GetMapping("/reportes")
    public String reportes(@RequestParam(required = false) String tipo,
                           @RequestParam(required = false) String docente,
                           @RequestParam(required = false) String asignatura,
                           @RequestParam(required = false) String modalidad,
                           @RequestParam(required = false) String carrera,
                           @RequestParam(required = false) String rol,
                           @RequestParam(required = false) String texto,
                           HttpSession session, Model model) {
        if (session.getAttribute("usuarioLogueado") == null) return "redirect:/login";

        if ("tutoria_docente_materia".equals(tipo)) {
            model.addAttribute("resultadoTutorias", tutoriaRepo.findByDocenteContainingIgnoreCaseAndAsignaturaContainingIgnoreCase(docente != null ? docente : "", asignatura != null ? asignatura : ""));
            model.addAttribute("filtroActivo", "Tutorias por Docente y Asignatura");
        } else if ("tutoria_modalidad_carrera".equals(tipo)) {
            model.addAttribute("resultadoTutorias", tutoriaRepo.findByEsGrupalOIndividualIgnoreCaseOrCarreraContainingIgnoreCase(modalidad != null ? modalidad : "", carrera != null ? carrera : ""));
            model.addAttribute("filtroActivo", "Tutorias por Modalidad o Carrera");
        } else if ("usuario_rol".equals(tipo)) {
            model.addAttribute("resultadoUsuarios", usuarioRepo.findByRol(rol != null ? rol : ""));
            model.addAttribute("filtroActivo", "Usuarios con Rol: " + rol);
        } else if ("usuario_busqueda".equals(tipo)) {
            model.addAttribute("resultadoUsuarios", usuarioRepo.findByNombreContainingIgnoreCaseOrEmailContainingIgnoreCase(texto != null ? texto : "", texto != null ? texto : ""));
            model.addAttribute("filtroActivo", "Busqueda de Usuario: " + texto);
        }

        return "reportes";
    }
}
