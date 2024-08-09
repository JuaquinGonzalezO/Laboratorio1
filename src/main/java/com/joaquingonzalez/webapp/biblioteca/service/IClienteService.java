package com.joaquingonzalez.webapp.biblioteca.service;

import java.util.List;

import com.joaquingonzalez.webapp.biblioteca.model.Cliente;





public interface IClienteService {


   public List<Cliente>listarCliente();

    public Cliente buscarClientePorId(Long id);
    
    public Cliente guardarCliente (Cliente cliente);
    
    public void eliminarCliente (Cliente cliente);
}
