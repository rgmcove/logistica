package com.example.logistica.controllers;

import com.example.logistica.entities.dto.BodegasDTO;
import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.BodegasViews;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.services.BodegasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bodegas")
public class BodegasController {

    private BodegasService bodegasService;

    @GetMapping(value = "/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllBodegas(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<BodegasDTO> bodegas = bodegasService.findAll(page, size);
            responseDTO = new ResponseDTO<>(200, bodegas, "Bodegas encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getBodegaById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            BodegasDTO bodegas = bodegasService.findById(id);
            responseDTO = new ResponseDTO<>(200, bodegas, "Bodega encontrada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createBodega(@RequestBody @Valid BodegasViews bodegasViews, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            BodegasDTO bodegasDTO = bodegasService.save(bodegasViews);
            responseDTO = new ResponseDTO<>(200, bodegasDTO, "Bodega creada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PutMapping(value = "/update")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateBodega(@RequestBody @Valid BodegasViews bodegasViews,
                                                        @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            BodegasDTO bodegasDTO = bodegasService.update(bodegasViews, id);
            responseDTO = new ResponseDTO<>(200, bodegasDTO, "Bodega actualizada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deleteBodega(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            bodegasService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Bodega eliminada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }
}
