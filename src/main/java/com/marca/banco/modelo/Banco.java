package com.marca.banco.modelo;

import java.io.Serializable;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@Entity
@Table(name = "BANCO")
public class Banco implements Serializable {

    @Id
    @Column(name = "SWIFT_BANCO", length = 11)
    private String swiftBanco;

    @NotNull
    @Column(name = "NOMBRE", length = 100, nullable = false)
    private String nombre;

    @NotNull
    @Column(name = "PAIS", length = 2, nullable = false)
    private String pais;

    @NotNull
    @Column(name = "BIN", length = 6, nullable = false)
    private String bin;

    @Override
    public int hashCode() {
        final int prime = 31;
        int result = 1;
        result = prime * result + ((swiftBanco == null) ? 0 : swiftBanco.hashCode());
        return result;
    }

    @Override
    public boolean equals(Object obj) {
        if (this == obj)
            return true;
        if (obj == null)
            return false;
        if (getClass() != obj.getClass())
            return false;
        Banco other = (Banco) obj;
        if (swiftBanco == null) {
            if (other.swiftBanco != null)
                return false;
        } else if (!swiftBanco.equals(other.swiftBanco))
            return false;
        return true;
    }

    @Override
    public String toString() {
        return "Banco [swiftBanco=" + swiftBanco + ", nombre=" + nombre + ", pais=" + pais + ", bin=" + bin + "]";
    }
} 