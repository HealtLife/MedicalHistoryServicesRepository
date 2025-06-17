package com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto;

import jakarta.persistence.Column;

import java.time.LocalDate;

public class VaccineRequestDto {

    private String dni;
    private String vacuna;
    private LocalDate fechaAplicacion;
    private String dosis;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getVacuna() {
        return vacuna;
    }

    public void setVacuna(String vacuna) {
        this.vacuna = vacuna;
    }

    public LocalDate getFechaAplicacion() {
        return fechaAplicacion;
    }

    public void setFechaAplicacion(LocalDate fechaAplicacion) {
        this.fechaAplicacion = fechaAplicacion;
    }

    public String getDosis() {
        return dosis;
    }

    public void setDosis(String dosis) {
        this.dosis = dosis;
    }
}
