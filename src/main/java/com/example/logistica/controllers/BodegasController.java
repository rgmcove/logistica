package com.example.logistica.controllers;

import com.example.logistica.entities.dto.BodegasDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.BodegasViews;
import com.example.logistica.services.BodegasService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;

import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
@RequestMapping("/bodegas")
public class BodegasController {

    @Autowired
    private BodegasService bodegasService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodegasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No se proceso la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
                    content = @Content)
    })
    @GetMapping(value = "/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar todos los registros de bodegas con paginacion")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllBodegas(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<BodegasDTO> bodegas = bodegasService.findAll(page, size);
            if (bodegas != null) {
                responseDTO = new ResponseDTO<>(200, bodegas, "Bodegas encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron bodegas"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodegasDTO.class))}),
            @ApiResponse(responseCode = "400", description = "No se proceso la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
                    content = @Content)
    })
    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar registro de bodega por id")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getBodegaById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            BodegasDTO bodegas = bodegasService.findById(id);
            if (bodegas != null) {
                responseDTO = new ResponseDTO<>(200, bodegas, "Bodega encontrada");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro la bodega"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodegasViews.class))}),
            @ApiResponse(responseCode = "400", description = "No se proceso la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
                    content = @Content)
    })
    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Crear registro de bodega")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createBodega(@Valid @RequestBody BodegasViews bodegasViews, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            BodegasDTO bodegasDTO = bodegasService.save(bodegasViews);
            responseDTO = new ResponseDTO<>(200, bodegasDTO, "Bodega creada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getCause().getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = BodegasViews.class))}),
            @ApiResponse(responseCode = "400", description = "No se proceso la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
                    content = @Content)
    })
    @PutMapping(value = "/update/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Actualizar registro de bodega")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = @Content),
            @ApiResponse(responseCode = "400", description = "No se proceso la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "401", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "403", description = "No esta autorizado para procesar la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "404", description = "No se encontro la solicitud",
                    content = @Content),
            @ApiResponse(responseCode = "500", description = "Error interno en el servidor",
                    content = @Content)
    })
    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Eliminar registro de bodega")
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
