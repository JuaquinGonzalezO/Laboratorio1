package com.joaquingonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.joaquingonzalez.webapp.biblioteca.model.Empleado;
import com.joaquingonzalez.webapp.biblioteca.service.EmpleadoService;

@Controller
@RestController
@RequestMapping("")
public class EmpleadoController {

     @Autowired
     EmpleadoService empleadoService;

     @GetMapping("/empleados")
    public ResponseEntity<List<Empleado>> listarEmpleado( Long id){
       try {
        return ResponseEntity.ok(empleadoService.listarEmpleado());
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);
       } 
    }

    @GetMapping("/empleado")
       public ResponseEntity<Empleado>buscarEmpleadoPorId(@RequestParam Long id){
       try {
        Empleado empleado = empleadoService.buscarEmpleadoPorId(id);
        return ResponseEntity.ok(empleado);
       } catch (Exception e) {
        return ResponseEntity.badRequest().body(null);   
       
       }
    
    } 
   @PostMapping("/empleado")
    public ResponseEntity<Map<String, String>>agregarEmpleado(@RequestBody Empleado empleado){
        Map<String,String>response = new HashMap<>();
        try {
            empleadoService.guardarEmpleado(empleado);
            response.put("message", "empleado creado con exito");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
           response.put("message", "error");
           response.put("error", "Hubo un error al crear el empleado");
           return ResponseEntity.badRequest().body(response);
        }
    }

}
