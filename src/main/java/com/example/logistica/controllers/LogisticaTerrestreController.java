package com.example.logistica.controllers;

import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import com.example.logistica.services.LogisticaTerrestreService;
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
import java.util.List;

@RestController
@RequestMapping("/logisticaTerrestre")
public class LogisticaTerrestreController {

    @Autowired
    private LogisticaTerrestreService logisticaTerrestreService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreDTO.class))}),
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
            description = "Consultar todos los registros de logistica terrestre con paginacion")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllLogisticaTerrestre(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<LogisticaTerrestreDTO> logisticaTerrestreDTOS = logisticaTerrestreService.findAll(page, size);
            if (logisticaTerrestreDTOS != null) {
                responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTOS, "Logisticas terrestres encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas terrestres"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreDTO.class))}),
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
            description = "Consultar registro de logistica terrestre por ID")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.findById(id);
            if (logisticaTerrestreDTO != null) {
                responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre encontrada");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro la logistica terrestre"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreDTO.class))}),
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
    @GetMapping(value = "/guia/{guia}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar registro de logistica terrestre por numero de guia")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByGuia(@PathVariable("guia") String guia) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.findByGuia(guia);
            if (logisticaTerrestreDTO != null) {
                responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre encontrada");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro la logistica terrestre"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreDTO.class))}),
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
    @GetMapping(value = "/cliente/{cliente}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar registro de logistica terrestre por ID de cliente")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByCliente(@PathVariable("cliente") Long cliente) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaTerrestreDTO> logisticaTerrestreDTO = logisticaTerrestreService.findByCliente(cliente);
            if (logisticaTerrestreDTO.size() > 0) {
                responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logisticas terrestres encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas terrestres"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreDTO.class))}),
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
    @GetMapping(value = "/vehiculo/{vehiculo}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar registro de logistica terrestre por placa de vehiculo")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByVehiculo(@PathVariable("vehiculo") String vehiculo) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaTerrestreDTO> logisticaTerrestreDTO = logisticaTerrestreService.findByVehiculo(vehiculo);
            if (logisticaTerrestreDTO.size() > 0) {
                responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logisticas terrestres encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas terrestres"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreViews.class))}),
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
            description = "Crear registro de logistica terrestre")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createLogisticaTerrestre(@Valid @RequestBody LogisticaTerrestreViews logisticaTerrestreViews,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.save(logisticaTerrestreViews);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre creado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaTerrestreViews.class))}),
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
            description = "Actualizar registro de logistica terrestre")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateLogisticaTerrestre(@RequestBody @Valid LogisticaTerrestreViews logisticaTerrestreViews,
                                                        @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.update(logisticaTerrestreViews, id);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre actualizado");
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
            description = "Eliminar registro de logistica terrestre")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deleteLogisticaTerrestre(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            logisticaTerrestreService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Logistica Terrestre eliminado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

}
