package com.example.logistica.controllers;

import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.dto.PuertoDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.entities.views.PuertoViews;
import com.example.logistica.services.PuertoService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/puertos")
public class PuertosController {

    @Autowired
    private PuertoService puertoService;

    @GetMapping(value = "/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllPuertos(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<PuertoDTO> puertoDTOS = puertoService.findAll(page, size);
            responseDTO = new ResponseDTO<>(200, puertoDTOS, "Puertos encontrados");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getPuertoById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            PuertoDTO puertoDTO = puertoService.findById(id);
            responseDTO = new ResponseDTO<>(200, puertoDTO, "Puerto encontrado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createPuerto(@RequestBody @Valid PuertoViews puertoViews, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            PuertoDTO puertoDTO = puertoService.save(puertoViews);
            responseDTO = new ResponseDTO<>(200, puertoDTO, "Puerto creado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PutMapping(value = "/update")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updatePuerto(@RequestBody @Valid PuertoViews puertoViews,
                                                        @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            PuertoDTO puertoDTO = puertoService.update(puertoViews, id);
            responseDTO = new ResponseDTO<>(200, puertoDTO, "Puerto actualizado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deletePuerto(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            puertoService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Puerto eliminado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }
}
