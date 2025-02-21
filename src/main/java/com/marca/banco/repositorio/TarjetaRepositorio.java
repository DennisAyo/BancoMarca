package com.marca.banco.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marca.banco.modelo.Tarjeta;

@Repository
public interface TarjetaRepositorio extends JpaRepository<Tarjeta, String> {
    
    Optional<Tarjeta> findByNumeroTarjeta(String numeroTarjeta);
    
}
