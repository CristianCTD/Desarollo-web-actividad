package com.unicartagena.tutoria;

import com.unicartagena.tutoria.model.Tutoria;
import com.unicartagena.tutoria.model.Usuario;
import com.unicartagena.tutoria.repository.TutoriaRepository;
import com.unicartagena.tutoria.repository.UsuarioRepository;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

@SpringBootApplication
public class TutoriaApplication {

    public static void main(String[] args) {
        SpringApplication.run(TutoriaApplication.class, args);
    }

    @Bean
    public CommandLineRunner initData(UsuarioRepository usuarioRepo, TutoriaRepository tutoriaRepo) {
        return args -> {
            if (usuarioRepo.count() == 0) {
                usuarioRepo.save(new Usuario("Administrador General", "admin@unicartagena.edu.co", "admin123", "Administrador"));
                usuarioRepo.save(new Usuario("Prof. John Carlos Arrieta", "jarrieta@unicartagena.edu.co", "docente123", "Docente"));
                usuarioRepo.save(new Usuario("Cristian Torres", "ctorres@unicartagena.edu.co", "estudiante123", "Estudiante"));
            }

            if (tutoriaRepo.count() == 0) {
                tutoriaRepo.save(new Tutoria(
                        "2026-09-18", "2026-09-20", "08:00", "10:00",
                        "Prof. John Carlos Arrieta", "Cristian Torres",
                        "Universidad de Cartagena", "Ingenieria de Software",
                        "Desarrollo Web", "Spring Boot 3 y Arquitectura MVC",
                        "Crear repositorios JPA, controladores y endpoints",
                        "Individual", "Laboratorio 3 / Virtual"
                ));
                tutoriaRepo.save(new Tutoria(
                        "2026-09-18", "2026-09-21", "14:00", "16:00",
                        "Prof. John Carlos Arrieta", "Carlos Gomez y Grupo",
                        "Universidad de Cartagena", "Ingenieria de Software",
                        "Desarrollo Web", "Persistencia con Spring Data JPA",
                        "Configurar base de datos y modelos relacionales",
                        "Grupal", "Sala Virtual Meet"
                ));
            }
        };
    }
}
