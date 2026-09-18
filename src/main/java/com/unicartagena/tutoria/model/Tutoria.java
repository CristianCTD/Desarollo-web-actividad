package com.unicartagena.tutoria.model;

import jakarta.persistence.*;

@Entity
@Table(name = "tutorias")
public class Tutoria {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String fecha;

    @Column(nullable = false)
    private String fechaProgramada;

    @Column(nullable = false)
    private String horaInicio;

    @Column(nullable = false)
    private String horaFin;

    @Column(nullable = false)
    private String docente;

    @Column(nullable = false)
    private String estudiante;

    @Column(nullable = false)
    private String universidad;

    @Column(nullable = false)
    private String carrera;

    @Column(nullable = false)
    private String asignatura;

    @Column(nullable = false)
    private String tematica;

    @Column(nullable = false, columnDefinition = "TEXT")
    private String compromisos;

    @Column(nullable = false)
    private String esGrupalOIndividual;

    @Column(nullable = false)
    private String lugar;

    public Tutoria() {}

    public Tutoria(String fecha, String fechaProgramada, String horaInicio, String horaFin,
                   String docente, String estudiante, String universidad, String carrera,
                   String asignatura, String tematica, String compromisos,
                   String esGrupalOIndividual, String lugar) {
        this.fecha = fecha;
        this.fechaProgramada = fechaProgramada;
        this.horaInicio = horaInicio;
        this.horaFin = horaFin;
        this.docente = docente;
        this.estudiante = estudiante;
        this.universidad = universidad;
        this.carrera = carrera;
        this.asignatura = asignatura;
        this.tematica = tematica;
        this.compromisos = compromisos;
        this.esGrupalOIndividual = esGrupalOIndividual;
        this.lugar = lugar;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getFecha() { return fecha; }
    public void setFecha(String fecha) { this.fecha = fecha; }

    public String getFechaProgramada() { return fechaProgramada; }
    public void setFechaProgramada(String fechaProgramada) { this.fechaProgramada = fechaProgramada; }

    public String getHoraInicio() { return horaInicio; }
    public void setHoraInicio(String horaInicio) { this.horaInicio = horaInicio; }

    public String getHoraFin() { return horaFin; }
    public void setHoraFin(String horaFin) { this.horaFin = horaFin; }

    public String getDocente() { return docente; }
    public void setDocente(String docente) { this.docente = docente; }

    public String getEstudiante() { return estudiante; }
    public void setEstudiante(String estudiante) { this.estudiante = estudiante; }

    public String getUniversidad() { return universidad; }
    public void setUniversidad(String universidad) { this.universidad = universidad; }

    public String getCarrera() { return carrera; }
    public void setCarrera(String carrera) { this.carrera = carrera; }

    public String getAsignatura() { return asignatura; }
    public void setAsignatura(String asignatura) { this.asignatura = asignatura; }

    public String getTematica() { return tematica; }
    public void setTematica(String tematica) { this.tematica = tematica; }

    public String getCompromisos() { return compromisos; }
    public void setCompromisos(String compromisos) { this.compromisos = compromisos; }

    public String getEsGrupalOIndividual() { return esGrupalOIndividual; }
    public void setEsGrupalOIndividual(String esGrupalOIndividual) { this.esGrupalOIndividual = esGrupalOIndividual; }

    public String getLugar() { return lugar; }
    public void setLugar(String lugar) { this.lugar = lugar; }
}
