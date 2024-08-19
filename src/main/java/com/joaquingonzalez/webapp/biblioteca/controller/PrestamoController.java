package com.joaquingonzalez.webapp.biblioteca.controller;

import java.util.HashMap;
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

import com.joaquingonzalez.webapp.biblioteca.model.Prestamo;
import com.joaquingonzalez.webapp.biblioteca.service.PrestamoService;



@Controller
@RestController
@RequestMapping("")
public class PrestamoController {


     @Autowired
     PrestamoService prestamoService;

     @GetMapping("/prestamos")
     public ResponseEntity<?> listarPrestamos() {
        Map<String,String> response = new HashMap<>();
        try {
            return ResponseEntity.ok(prestamoService.listarPrestamos());
        } catch (Exception e) {
            response.put("message", "error");
            response.put("err", "no se encontro una lista libros");
            return ResponseEntity.badRequest().body(response);
        }
     }

     @PostMapping("/prestamo")
     public ResponseEntity<Map<String,String>> agregarPrestamo(@RequestBody Prestamo prestamo){
         Map<String,String> response = new HashMap<>();

         try {
             prestamoService.guardarPrestamo(prestamo);
             response.put("message", "Prestamo encontrado con exito");
             return ResponseEntity.ok(response);
         } catch (Exception e) {
             response.put("message", "Error");
             response.put("message", "Hubo un error al crear el prestamo");
             return ResponseEntity.badRequest().body(response);
         }
     }
       @GetMapping("/prestamo")
    public ResponseEntity<Prestamo> buscarPrestamo(@RequestParam Long id){
        try{
            return ResponseEntity.ok(prestamoService.buscarPrestamoPorId(id));
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(null);
        }
    }
    @PutMapping("/prestamo")
    public ResponseEntity<Map<String, String>> editarPrestamo(@RequestParam Long id, @RequestBody Prestamo prestamoNuevo){
        Map<String, String> response = new HashMap<>();
        try {

            if(!prestamoService.verificacionDeLibro(prestamoNuevo)){
                Prestamo prestamoViejo = prestamoService.buscarPrestamoPorId(id);
                prestamoViejo.setFechaPrestamo(prestamoNuevo.getFechaPrestamo());
                prestamoViejo.setFechaDevolucion(prestamoNuevo.getFechaDevolucion());
                prestamoViejo.setVigencia(prestamoNuevo.getVigencia());
                prestamoViejo.setEmpleado(prestamoNuevo.getEmpleado());
                prestamoViejo.setCliente(prestamoNuevo.getCliente());
                prestamoService.guardarPrestamo(prestamoNuevo);
                response.put("message", "Modificado");
                return ResponseEntity.ok(response);
            }else{
                response.put("message" , "el libro no se encuetra en vigencia");
              return ResponseEntity.badRequest().body(response);
            }
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se pudo editar ");
            return ResponseEntity.badRequest().body(response);
        }
    }
    @DeleteMapping("/prestamo")
    public ResponseEntity<Map<String, String>> eliminarPrestamo(@RequestParam Long id){
        Map<String, String> response = new HashMap<>();
        try {
            Prestamo prestamoViejo = prestamoService.buscarPrestamoPorId(id);
            prestamoService.eliminarPrestamo(prestamoViejo);
            response.put("message", "borrado");
            return ResponseEntity.ok(response);
        } catch (Exception e) {
            response.put("message", "Error");
            response.put("err", "no se pudo borrar ");
            return ResponseEntity.badRequest().body(response);
        }
    }
 
}
