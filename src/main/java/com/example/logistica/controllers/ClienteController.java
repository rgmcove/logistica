package com.example.logistica.controllers;

import com.example.logistica.entities.dto.ClienteDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.services.ClienteService;
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
@RequestMapping("/clientes")
public class ClienteController {

    @Autowired
    private ClienteService clienteService;

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))}),
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
            description = "Consultar todos los registros de clientes con paginacion")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllClientes(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<ClienteDTO> clientes = clienteService.findAll(page, size);
            if (clientes != null) {
                responseDTO = new ResponseDTO<>(200, clientes, "Clientes encontrados");
                return ResponseEntity.ok(responseDTO);
            } else {
                responseDTO = new ResponseDTO<>(404, "No se encontraron clientes");
                return ResponseEntity.badRequest().body(responseDTO);
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteDTO.class))}),
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
            description = "Consultar registro de cliente por id")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getClienteById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            ClienteDTO cliente = clienteService.findById(id);
            if (cliente != null) {
                responseDTO = new ResponseDTO<>(200, cliente, "Cliente encontrado");
                return ResponseEntity.ok(responseDTO);
            } else {
                return ResponseEntity.badRequest().body(new ResponseDTO<>(404, "No se encontro el cliente"));
            }
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteViews.class))}),
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
            description = "Crear registro de cliente")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createCliente(@RequestBody @Valid ClienteViews clienteViews, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            ClienteDTO clienteDTO = clienteService.save(clienteViews);
            responseDTO = new ResponseDTO<>(200, clienteDTO, "Cliente creado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Solicitud procesada correctamente",
                    content = {@Content(mediaType = "application/json", schema = @Schema(implementation = ClienteViews.class))}),
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
            description = "Actualizar registro de cliente")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateCliente(@RequestBody @Valid ClienteViews clienteViews,
                                                        @PathVariable("id") Long id, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            ClienteDTO clienteDTO = clienteService.update(clienteViews, id);
            responseDTO = new ResponseDTO<>(200, clienteDTO, "Cliente actualizado");
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
            description = "Eliminar registro de cliente")
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deleteCliente(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            clienteService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Cliente eliminado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }
}



