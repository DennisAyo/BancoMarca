package com.marca.banco.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class BancoDTO {
    
    @NotBlank(message = "El SWIFT del banco es requerido")
    @Size(min = 8, max = 11, message = "El SWIFT debe tener entre 8 y 11 caracteres")
    private String swiftBanco;

    @NotBlank(message = "El nombre del banco es requerido")
    @Size(max = 100, message = "El nombre no debe exceder 100 caracteres")
    private String nombre;

    @NotBlank(message = "El país es requerido")
    @Size(min = 2, max = 2, message = "El código de país debe tener 2 caracteres")
    private String pais;

    @NotBlank(message = "El BIN es requerido")
    @Pattern(regexp = "^[0-9]{6}$", message = "El BIN debe tener exactamente 6 dígitos numéricos")
    private String bin;
} 