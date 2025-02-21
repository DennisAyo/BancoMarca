package com.marca.banco.repositorio;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.marca.banco.modelo.Banco;

@Repository
public interface BancoRepositorio extends JpaRepository<Banco, String> {
    
    Optional<Banco> findByBin(String bin);
    
} 