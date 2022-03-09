package com.example.logistica.controllers;

import com.example.logistica.entities.dto.LogisticaMaritimaDTO;
import com.example.logistica.entities.dto.LogisticaTerrestreDTO;
import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.views.LogisticaMaritimaViews;
import com.example.logistica.entities.views.LogisticaTerrestreViews;
import com.example.logistica.services.LogisticaMaritimaService;
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
@RequestMapping("/logisticaMaritima")
public class LogisticaMaritimaController {

    @Autowired
    private LogisticaMaritimaService logisticaMaritimaService;

    @GetMapping(value = "/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllLogisticaMaritima(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<LogisticaMaritimaDTO> logisticaMaritimaDTOS = logisticaMaritimaService.findAll(page, size);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTOS, "Logisticas Maritimas encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaById(@PathVariable("id") Long id) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.findById(id);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima encontrada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/guia/{guia}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByGuia(@PathVariable("guia") String guia) {
        ResponseDTO<?> responseDTO;
        try {
            LogisticaMaritimaDTO logisticaMaritimaDTO = logisticaMaritimaService.findByGuia(guia);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logistica Maritima encontrada");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/cliente/{cliente}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByCliente(@PathVariable("cliente") Long cliente) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaMaritimaDTO> logisticaMaritimaDTO = logisticaMaritimaService.findByCliente(cliente);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logisticas Maritimas encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/flota/{flota}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getLogisticaMaritimaByVehiculo(@PathVariable("flota") String flota) {
        ResponseDTO<?> responseDTO;
        try {
            List<LogisticaMaritimaDTO> logisticaMaritimaDTO = logisticaMaritimaService.findByFlota(flota);
            responseDTO = new ResponseDTO<>(200, logisticaMaritimaDTO, "Logisticas Maritimas encontradas");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createLogisticaMaritima(@RequestBody @Valid LogisticaMaritimaViews logisticaMaritimaViews,
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

    @PutMapping(value = "/update")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateLogisticaMaritima(@RequestBody @Valid LogisticaMaritimaViews logisticaMaritimaViews,
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

    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
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
