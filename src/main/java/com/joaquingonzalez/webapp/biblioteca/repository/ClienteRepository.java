package com.joaquingonzalez.webapp.biblioteca.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joaquingonzalez.webapp.biblioteca.model.Cliente;

public interface ClienteRepository extends JpaRepository <Cliente,Long>{

}
