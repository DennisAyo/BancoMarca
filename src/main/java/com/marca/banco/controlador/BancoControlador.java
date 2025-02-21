package com.marca.banco.controlador;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.marca.banco.dto.BancoDTO;
import com.marca.banco.excepcion.NotFoundException;
import com.marca.banco.mapper.BancoMapper;
import com.marca.banco.modelo.Banco;
import com.marca.banco.servicio.BancoServicio;

import jakarta.validation.Valid;
import lombok.extern.slf4j.Slf4j;

@RestController
@RequestMapping("/api/v1/bancos")
@Slf4j
public class BancoControlador {

    private final BancoServicio servicio;
    private final BancoMapper mapper;

    public BancoControlador(BancoServicio servicio, BancoMapper mapper) {
        this.servicio = servicio;
        this.mapper = mapper;
    }

    @GetMapping
    public ResponseEntity<List<BancoDTO>> obtenerTodos() {
        List<Banco> bancos = this.servicio.buscarTodos();
        List<BancoDTO> dtos = new ArrayList<>(bancos.size());
        log.info("Se encontraron {} bancos", bancos.size());
        for(Banco banco : bancos) {
            dtos.add(mapper.toDTO(banco));
        }
        return ResponseEntity.ok(dtos);
    }

    @GetMapping("/{swiftBanco}")
    public ResponseEntity<BancoDTO> obtenerPorSwift(@PathVariable("swiftBanco") String swiftBanco) {
        try {
            Banco banco = this.servicio.buscarPorSwift(swiftBanco);
            log.info("Se encontró el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.ok(this.mapper.toDTO(banco));
        } catch (NotFoundException e) {
            log.error("No se encontró el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.notFound().build();
        }
    }

    @GetMapping("/bin/{bin}")
    public ResponseEntity<BancoDTO> obtenerPorBin(@PathVariable("bin") String bin) {
        try {
            Banco banco = this.servicio.buscarPorBin(bin);
            log.info("Se encontró el banco con BIN: {}", bin);
            return ResponseEntity.ok(this.mapper.toDTO(banco));
        } catch (NotFoundException e) {
            log.error("No se encontró el banco con BIN: {}", bin);
            return ResponseEntity.notFound().build();
        }
    }

    @PostMapping
    public ResponseEntity<BancoDTO> crear(@Valid @RequestBody BancoDTO dto) {
        try {
            Banco banco = this.servicio.crear(this.mapper.toModel(dto));
            log.info("Se creó el banco con SWIFT: {}", banco.getSwiftBanco());
            return ResponseEntity.ok(this.mapper.toDTO(banco));
        } catch (Exception e) {
            log.error("Error al crear el banco", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @PutMapping("/{swiftBanco}")
    public ResponseEntity<BancoDTO> actualizar(
            @PathVariable("swiftBanco") String swiftBanco,
            @Valid @RequestBody BancoDTO dto) {
        try {
            if (!swiftBanco.equals(dto.getSwiftBanco())) {
                log.error("El SWIFT en la URL ({}) no coincide con el del cuerpo ({})", 
                    swiftBanco, dto.getSwiftBanco());
                return ResponseEntity.badRequest().build();
            }
            Banco banco = this.servicio.actualizar(this.mapper.toModel(dto));
            log.info("Se actualizó el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.ok(this.mapper.toDTO(banco));
        } catch (NotFoundException e) {
            log.error("No se encontró el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.notFound().build();
        } catch (Exception e) {
            log.error("Error al actualizar el banco", e);
            return ResponseEntity.badRequest().build();
        }
    }

    @DeleteMapping("/{swiftBanco}")
    public ResponseEntity<Void> eliminar(@PathVariable("swiftBanco") String swiftBanco) {
        try {
            this.servicio.eliminar(swiftBanco);
            log.info("Se eliminó el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.ok().build();
        } catch (NotFoundException e) {
            log.error("No se encontró el banco con SWIFT: {}", swiftBanco);
            return ResponseEntity.notFound().build();
        }
    }

    @ExceptionHandler(NotFoundException.class)
    public ResponseEntity<String> manejarNotFoundException(NotFoundException e) {
        log.error(e.getMessage(), e);
        return ResponseEntity.notFound().build();
    }
} 