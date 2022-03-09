package com.example.logistica.controllers;

import com.example.logistica.entities.dto.ResponseDTO;
import com.example.logistica.entities.dto.TipoProductoDTO;
import com.example.logistica.entities.views.ClienteViews;
import com.example.logistica.entities.views.TipoProductoViews;
import com.example.logistica.services.TipoProductoService;
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
@RequestMapping("/tipoProducto")
public class TipoProductoController {

    @Autowired
    private TipoProductoService tipoProductoService;

    @GetMapping("/all/{page}/{size}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getAllTipoProducto(@PathVariable("page") int page, @PathVariable("size") int size) {
        ResponseDTO<?> responseDTO;
        try {
            Page<TipoProductoDTO> tipoProductoDTOPage = tipoProductoService.getAllTipoProducto(page, size);
            responseDTO = new ResponseDTO<>(200, tipoProductoDTOPage, "Productos encontrados");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/id/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getTipoProductoById(@PathVariable("id") Integer id) {
        ResponseDTO<?> responseDTO;
        try {
            TipoProductoDTO tipoProductoDTO = tipoProductoService.findById(id);
            responseDTO = new ResponseDTO<>(200, tipoProductoDTO, "Producto encontrado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PostMapping(value = "/create")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> createTipoProducto(@RequestBody @Valid TipoProductoViews tipoProductoViews, BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            tipoProductoService.save(tipoProductoViews);
            responseDTO = new ResponseDTO<>(200, "Producto creado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @PutMapping(value = "/update")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> updateTipoProducto(@RequestBody @Valid TipoProductoViews tipoProductoViews, @PathVariable("id") Integer id,
                                                             BindingResult bindingResult) {
        if (bindingResult.hasErrors()) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, bindingResult.getAllErrors().get(0).getDefaultMessage()));
        }
        ResponseDTO<?> responseDTO;
        try {
            TipoProductoDTO tipoProductoDTO = tipoProductoService.update(tipoProductoViews, id);
            responseDTO = new ResponseDTO<>(200, tipoProductoDTO, "Producto actualizado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @DeleteMapping(value = "/delete/{id}")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> deleteTipoProducto(@PathVariable("id") Integer id) {
        ResponseDTO<?> responseDTO;
        try {
            tipoProductoService.delete(id);
            responseDTO = new ResponseDTO<>(200, "Producto eliminado");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/productosTerrestres")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getTipoProductoByTerrestre() {
        ResponseDTO<?> responseDTO;
        try {
            List<TipoProductoDTO> tipoProductoDTOList = tipoProductoService.getTipoProductosByTerrestre();
            responseDTO = new ResponseDTO<>(200, tipoProductoDTOList, "Productos encontrados");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

    @GetMapping(value = "/productosMaritimos")
    @Operation(security = { @SecurityRequirement(name = "bearer-key") })
    @PreAuthorize("hasRole('ROLE_USER')")
    public ResponseEntity<ResponseDTO<?>> getTipoProductoByMaritimo() {
        ResponseDTO<?> responseDTO;
        try {
            List<TipoProductoDTO> tipoProductoDTOList = tipoProductoService.getTipoProductosByMaritimo();
            responseDTO = new ResponseDTO<>(200, tipoProductoDTOList, "Productos encontrados");
            return ResponseEntity.ok(responseDTO);
        } catch (Exception e) {
            return ResponseEntity.badRequest().body(new ResponseDTO<>(400, e.getMessage()));
        }
    }

}
