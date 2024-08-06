package com.joaquingonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquingonzalez.webapp.biblioteca.model.Categoria;

public interface CategoriaRepository extends JpaRepository<Categoria,Long>{

}


