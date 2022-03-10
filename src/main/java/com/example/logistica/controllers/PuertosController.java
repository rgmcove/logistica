package com.example.logistica.controllers;

import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.dto.PuertoDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.entities.views.PuertoViews;
import com.example.logistica.services.PuertoService;
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
@RequestMapping("/puertos")
public class PuertosController {

    @Autowired
    private PuertoService puertoService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PuertoDTO.class))}),
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
            description = "Consultar todos los registros de puertos con paginacion")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllPuertos(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<PuertoDTO> puertoDTOS = puertoService.findAll(page, size);
            if (puertoDTOS != null) {
                responseDTO = new ResponseDTO<>(200, puertoDTOS, "Puertos encontrados");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron puertos"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PuertoDTO.class))}),
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
            description = "Consultar registro de puerto por id")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getPuertoById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            PuertoDTO puertoDTO = puertoService.findById(id);
            if (puertoDTO != null) {
                responseDTO = new ResponseDTO<>(200, puertoDTO, "Puerto encontrado");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro el puerto"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PuertoViews.class))}),
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
            description = "Crear registro de puerto")
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

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = PuertoViews.class))}),
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
            description = "Actualizar registro de puerto")
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
            description = "Eliminar un registro de puertos")
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
