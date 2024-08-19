package com.joaquingonzalez.webapp.biblioteca.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.joaquingonzalez.webapp.biblioteca.model.Empleado;

import com.joaquingonzalez.webapp.biblioteca.repository.EmpleadoRepository;


@Service
public class EmpleadoService implements IEmpleadoService {
    @Autowired
    EmpleadoRepository empleadoRepository;

    @Override
    public List<Empleado> listarEmpleado() {
        return empleadoRepository.findAll();
        
    }

    @Override
    public Empleado buscarEmpleadoPorId(Long id) {
        return empleadoRepository.findById(id).orElse(null);
    }

    @Override
    public Empleado guardarEmpleado(Empleado empleado) {
        return empleadoRepository.save(empleado);
        
    }

    @Override
    public void eliminarEmpleado(Empleado empleado) {
        empleadoRepository.delete(empleado);
        
    }

    @Override
    public Boolean verificacionDpiDuplicado(Empleado empleado) {
       Boolean x = Boolean.FALSE;
       List<Empleado>empleados = listarEmpleado();

    for (Empleado e : empleados) {
        if(e.getDpi().equals(empleado.getDpi()) && !e.getId().equals(empleado.getId())){
            x = Boolean.TRUE;
        }   
    }
       return x;
    }
}
