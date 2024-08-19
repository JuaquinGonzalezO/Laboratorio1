package com.joaquingonzalez.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.joaquingonzalez.webapp.biblioteca.model.Prestamo;
import com.joaquingonzalez.webapp.biblioteca.repository.PrestamoRepository;

@Service
public class PrestamoService implements IPrestamoService {
    
    @Autowired
    
    PrestamoRepository prestamoRepository;

    @Override
    public List<Prestamo> listarPrestamos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Prestamo buscarPrestamoPorId(Long id) {
        return prestamoRepository.findById(id).orElse(null);
    }

    @Override
    public Prestamo guardarPrestamo(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPrestamo(Prestamo prestamo) {
        prestamoRepository.delete(prestamo);
    }

    @Override
    public Boolean verificacionDeLibro(Prestamo prestamo) {
          Boolean x = Boolean.FALSE;
       List<Prestamo>prestamos = listarPrestamos();

    for (Prestamo e : prestamos) {
        if(e.getVigencia().equals(prestamo.getVigencia())){
            x = Boolean.TRUE;
        }   
    }
       return x;
    }

    
  
  }
