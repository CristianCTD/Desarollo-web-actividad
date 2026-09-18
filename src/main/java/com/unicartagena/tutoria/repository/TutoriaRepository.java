package com.unicartagena.tutoria.repository;

import com.unicartagena.tutoria.model.Tutoria;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TutoriaRepository extends JpaRepository<Tutoria, Long> {
    List<Tutoria> findByDocenteContainingIgnoreCaseAndAsignaturaContainingIgnoreCase(String docente, String asignatura);
    List<Tutoria> findByEsGrupalOIndividualIgnoreCaseOrCarreraContainingIgnoreCase(String modalidad, String carrera);
}
