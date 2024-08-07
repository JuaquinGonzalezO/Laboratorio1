package com.joaquingonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaquingonzalez.webapp.biblioteca.model.Categoria;
import com.joaquingonzalez.webapp.biblioteca.service.CategoriaService;

@Controller
@RestController
@RequestMapping("")
public class CategoriaController {

    @Autowired
    CategoriaService categoriaService;

    @GetMapping("/categorias")
    public List<Categoria>listarCategorias(){
        return categoriaService.listarCategorias();
    }
     
  @GetMapping("/categoria")
    public ResponseEntity<Categoria> buscarCategoriaPorId(@RequestParam Long id){
        try {
           Categoria categoria = categoriaService.buscarCategoriaPorId(id);
           return ResponseEntity.ok(categoria);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
 


    @PostMapping("categoria")
    public ResponseEntity<Map<String,String>>agregarCategoria(@RequestBody Categoria categoria){
       Map<String, String> response = new HashMap<>(); 
        try {
            categoriaService.guardarCategoria(categoria);
            response.put("message", "Categoria Creada con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "error");
            response.put("error", "hubi un error al crear la categoria");
            return ResponseEntity.badRequest().body(response);
        }
    }

    @PutMapping("/categoria")
    public ResponseEntity<Map<String, String>> editarCategoria(@RequestParam Long id, @RequestBody Categoria categoriaNueva){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoriaVieja = categoriaService.buscarCategoriaPorId(id);
            categoriaVieja.setNombreCategoria(categoriaNueva.getNombreCategoria());
            categoriaService.guardarCategoria(categoriaVieja);
            response.put("message", "Modificado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se pudo editar :v");
            return ResponseEntity.badRequest().body(response);
        }
    }
 
    @DeleteMapping("/categoria")
    public ResponseEntity<Map<String, String>> eliminarCategoria(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Categoria categoria = categoriaService.buscarCategoriaPorId(id);
            categoriaService.eliminarCategoria(categoria);
            response.put("message", "borrao");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se pudo borrar :c");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
 
