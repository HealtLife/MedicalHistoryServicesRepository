package com.healthlifei.u20221b471.main.medicalhistoryservice.application.dto;

public class AllergyRequestDto {
    private String dni;
    private String alergia;
    private String reaccion;

    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public String getAlergia() {
        return alergia;
    }

    public void setAlergia(String alergia) {
        this.alergia = alergia;
    }

    public String getReaccion() {
        return reaccion;
    }

    public void setReaccion(String reaccion) {
        this.reaccion = reaccion;
    }
}
