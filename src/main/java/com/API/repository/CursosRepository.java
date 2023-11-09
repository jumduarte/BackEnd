package com.API.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.API.entities.Cursos;

public interface CursosRepository extends JpaRepository<Cursos, Long> {

}