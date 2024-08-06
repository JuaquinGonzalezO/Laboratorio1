package com.joaquingonzalez.webapp.biblioteca.service;

import java.util.List;

import com.joaquingonzalez.webapp.biblioteca.model.Categoria;

public interface ICategoriaService {

    public List<Categoria>listarCategorias();

    public Categoria guardarCategoria(Categoria categoria);

    public Categoria buscarCategoriaPorId(Long id);

    public void eliminarCategoria(Categoria categoria);
}
