package com.joaquingonzalez.webapp.biblioteca.service;

import java.util.List;

import com.joaquingonzalez.webapp.biblioteca.model.Empleado;


public interface IEmpleadoService {


    public List<Empleado>listarEmpleado();

    public Empleado buscarEmpleadoPorId(Long id);
    
    public Empleado guardarEmpleado (Empleado empleado);
    
    public void eliminarEmpleado (Empleado empleado);
    
}
