package com.example.logistica.controllers;

import com.example.logistica.entities.dto.LogisticaMaritimaDTO;
import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.LogisticaMaritimaViews;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import com.example.logistica.services.LogisticaMaritimaService;
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
@RequestMapping("/logisticaMaritima")
public class LogisticaMaritimaController {

    @Autowired
    private LogisticaMaritimaService logisticaMaritimaService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaDTO.class))}),
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
            description = "Consultar todos los registros de logistica maritima con paginacion")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllLogisticaMaritima(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<LogisticaMaritimaDTO> logisticaMaritimaDTOS = logisticaMaritimaService.findAll(page, size);
            if (logisticaMaritimaDTOS != null) {
                responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTOS, "Logisticas Maritimas encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas maritimas"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaDTO.class))}),
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
            description = "Consultar registro de logistica maritima por id")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.findById(id);
            if (logisticaMaritimaDTO != null) {
                responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima encontrada");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro la logistica maritima"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaDTO.class))}),
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
            description = "Consultar registro de logistica maritima por guia")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByGuia(@PathVariable("guia") String guia) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.findByGuia(guia);
            if (logisticaMaritimaDTO != null) {
                responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima encontrada");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro la logistica maritima"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaDTO.class))}),
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
            description = "Consultar registro de logistica maritima por ID cliente")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByCliente(@PathVariable("cliente") Long cliente) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaMaritimaDTO> logisticaMaritimaDTO = logisticaMaritimaService.findByCliente(cliente);
            if (logisticaMaritimaDTO.size() > 0) {
                responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logisticas Maritimas encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas maritimas"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaDTO.class))}),
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
    @GetMapping(value = "/flota/{flota}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") },
            description = "Consultar registro de logistica maritima por numero de flota")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByVehiculo(@PathVariable("flota") String flota) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaMaritimaDTO> logisticaMaritimaDTO = logisticaMaritimaService.findByFlota(flota);
            if (logisticaMaritimaDTO.size() > 0) {
                responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logisticas Maritimas encontradas");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontraron logisticas maritimas"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaViews.class))}),
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
            description = "Crear registro de logistica maritima")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createLogisticaMaritima(@Valid @RequestBody LogisticaMaritimaViews logisticaMaritimaViews,
                                                                   BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.save(logisticaMaritimaViews);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima creado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = LogisticaMaritimaViews.class))}),
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
            description = "Actualizar registro de logistica maritima")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateLogisticaMaritima(@Valid @RequestBody LogisticaMaritimaViews logisticaMaritimaViews,
                                                                   @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.update(logisticaMaritimaViews, id);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima actualizado");
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
            description = "Eliminar registro de logistica maritima")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deleteLogisticaMaritima(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            logisticaMaritimaService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Logistica Maritima eliminado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }
}
