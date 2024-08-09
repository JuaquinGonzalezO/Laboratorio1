package com.joaquingonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.joaquingonzalez.webapp.biblioteca.model.Cliente;
import com.joaquingonzalez.webapp.biblioteca.service.ClienteService;


@Controller
@RestController
@RequestMapping("")
public class ClienteController {
    @Autowired
    ClienteService clienteService;

     @GetMapping("/clientes")
    public ResponseEntity<List<Cliente>> listarCliente( Long id){
       try {
        return ResponseEntity.ok(clienteService.listarCliente());
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
       } 
    }
       @GetMapping("/cliente")
       public ResponseEntity<Cliente>buscarClientePorId(@RequestParam Long id){
       try {
        Cliente cliente = clienteService.buscarClientePorId(id);
        return ResponseEntity.ok(cliente);
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
        
       
       }
    
    } 

    @PostMapping("/cliente")
    public ResponseEntity<Map<String, String>>agregarCliente(@RequestBody Cliente cliente){
        Map<String,String>response = new HashMap<>();
        try {
            clienteService.guardarCliente(cliente);
            response.put("message", "cliente creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
           response.put("message", "error");
           response.put("error", "Hubo un error al crear el cliente");
           return ResponseEntity.badRequest().body(response);
        }
    }
    @PutMapping("cliente")
    public ResponseEntity<Map<String,String>> editarCliente(@PathVariable Long id, @RequestBody Cliente clienteNuevo){
        Map<String, String>response = new HashMap<>();
        try {
           
            Cliente cliente = clienteService.buscarClientePorId(id);
            cliente.setDpi(clienteNuevo.getDpi());
            cliente.setNombre(clienteNuevo.getNombre());
            cliente.setApellido(clienteNuevo.getApellido());
            cliente.setTelefono(clienteNuevo.getTelefono());
            response.put("message", "Cliente modificado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un errro al modificar el cliente");
            return ResponseEntity.badRequest().body(response);
            
        }        

    }

     @DeleteMapping("/cliente")
    public ResponseEntity<Map<String, String>>eliminarCliente(@RequestParam Long id){
        Map<String,String>response = new HashMap<>();
        try {
            Cliente cliente = clienteService.buscarClientePorId(id);
            clienteService.eliminarCliente(cliente);
            response.put("message", "cliente eliminado con Exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "Hubo un error al eliminar el cliente ");
            return ResponseEntity.badRequest().body(response);
        }
    }
}
