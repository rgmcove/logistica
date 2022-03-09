package com.example.logistica.controllers;

import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import com.example.logistica.services.LogisticaTerrestreService;
import io.swagger.v3.oas.annotations.Operation;
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

    @GetMapping(value = "/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllLogisticaTerrestre(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<LogisticaTerrestreDTO> logisticaTerrestreDTOS = logisticaTerrestreService.findAll(page, size);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTOS, "Logisticas terrestres encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.findById(id);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre encontrada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/guia/{guia}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByGuia(@PathVariable("guia") String guia) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaTerrestreDTO logisticaTerrestreDTO = logisticaTerrestreService.findByGuia(guia);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logistica terrestre encontrada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/cliente/{cliente}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByCliente(@PathVariable("cliente") Long cliente) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaTerrestreDTO> logisticaTerrestreDTO = logisticaTerrestreService.findByCliente(cliente);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logisticas terrestres encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/vehiculo/{vehiculo}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaTerrestreByVehiculo(@PathVariable("vehiculo") String vehiculo) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaTerrestreDTO> logisticaTerrestreDTO = logisticaTerrestreService.findByVehiculo(vehiculo);
            responseDTO = new ResponseDTO<>(200, logisticaTerrestreDTO, "Logisticas terrestres encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createLogisticaTerrestre(@RequestBody @Valid LogisticaTerrestreViews logisticaTerrestreViews,
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

    @PutMapping(value = "/update")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
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

    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
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
