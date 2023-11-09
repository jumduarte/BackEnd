package com.API.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.API.entities.Cursos;
import com.API.services.CursosService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.validation.Valid;

@Tag(name = "Cursos", description = "API REST DE GERENCIAMENTO DE CURSOS")
@RestController
@RequestMapping("/cursos")
@CrossOrigin(origins = "*")
public class CursosController {
    
    private final CursosService cursosService;
    
    @Autowired
    public CursosController(CursosService cursosService) {
        this.cursosService = cursosService;
    }

    @GetMapping("/{id}")
    @Operation(summary = "Localiza cursos por ID")
    public ResponseEntity<Cursos> getProductById(@PathVariable Long id) {
    	Cursos cursos = cursosService.getCursosById(id);
        if (cursos != null) {
            return ResponseEntity.ok(cursos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping
    @Operation(summary = "Apresenta todos os Cursos")
    public ResponseEntity<List<Cursos>> getAllCursoss() {
        List<Cursos> cursos = cursosService.getAllCursoss();
        return ResponseEntity.ok(cursos);
    }
    @PostMapping
    @Operation(summary = "Cadastra um Cursos")
    public ResponseEntity<Cursos> criarCursos(@RequestBody @Valid Cursos cursos) {
    	Cursos criarCursos = cursosService.salvarCursos(cursos);
        return ResponseEntity.status(HttpStatus.CREATED).body(criarCursos);
    }
   

    @PutMapping("/{id}")
    @Operation(summary = "Altera o Cursos")
    public ResponseEntity<Cursos> updateCursos(@PathVariable Long id, @RequestBody @Valid Cursos cursos) {
    	Cursos updatedCursos = cursosService.updateCursos(id, cursos);
        if (updatedCursos != null) {
            return ResponseEntity.ok(updatedCursos);
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Deleta o Cursos")
    public ResponseEntity<String> deleteCursos(@PathVariable Long id) {
        boolean deleted = cursosService.deleteCursos(id);
        if (deleted) {
        	 return ResponseEntity.status(HttpStatus.NO_CONTENT).build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}