package com.marca.banco.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.MappingConstants;
import org.mapstruct.ReportingPolicy;

import com.marca.banco.dto.BancoDTO;
import com.marca.banco.modelo.Banco;

@Mapper(
    componentModel = MappingConstants.ComponentModel.SPRING,
    unmappedTargetPolicy = ReportingPolicy.IGNORE
)
public interface BancoMapper {
    
    BancoDTO toDTO(Banco modelo);
    Banco toModel(BancoDTO dto);
} 