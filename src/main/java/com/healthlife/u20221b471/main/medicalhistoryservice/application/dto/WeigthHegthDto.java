package com.healthlife.u20221b471.main.medicalhistoryservice.application.dto;


import jakarta.persistence.Column;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class WeigthHegthDto {

    private String dni;
    private Double peso;
    private Double altura;
    private LocalDate fechaRegistro;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public Double getPeso() {
        return peso;
    }

    public void setPeso(Double peso) {
        this.peso = peso;
    }

    public Double getAltura() {
        return altura;
    }

    public void setAltura(Double altura) {
        this.altura = altura;
    }

    public LocalDate getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDate fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
