package com.marca.banco.servicio;

import java.util.List;

import org.springframework.stereotype.Service;

import com.marca.banco.excepcion.NotFoundException;
import com.marca.banco.modelo.Banco;
import com.marca.banco.repositorio.BancoRepositorio;

@Service
public class BancoServicio {

    public static final String ENTITY_NAME = "Banco";
    
    private final BancoRepositorio repositorio;

    public BancoServicio(BancoRepositorio repositorio) {
        this.repositorio = repositorio;
    }

    public List<Banco> buscarTodos() {
        return this.repositorio.findAll();
    }

    public Banco buscarPorSwift(String swiftBanco) {
        return this.repositorio.findById(swiftBanco)
            .orElseThrow(() -> new NotFoundException(swiftBanco, ENTITY_NAME));
    }

    public Banco buscarPorBin(String bin) {
        return this.repositorio.findByBin(bin)
            .orElseThrow(() -> new NotFoundException(bin, ENTITY_NAME));
    }

    public Banco crear(Banco banco) {
        return this.repositorio.save(banco);
    }

    public Banco actualizar(Banco banco) {
        Banco bancoDB = this.buscarPorSwift(banco.getSwiftBanco());
        bancoDB.setNombre(banco.getNombre());
        bancoDB.setPais(banco.getPais());
        bancoDB.setBin(banco.getBin());
        return this.repositorio.save(bancoDB);
    }

    public void eliminar(String swiftBanco) {
        Banco banco = this.buscarPorSwift(swiftBanco);
        this.repositorio.delete(banco);
    }
} 