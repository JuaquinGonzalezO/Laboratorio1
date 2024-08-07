package com.joaquingonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquingonzalez.webapp.biblioteca.model.Libro;

public interface LibroRepository extends JpaRepository<Libro, Long>{

}
