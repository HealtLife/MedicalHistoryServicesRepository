package com.healthlifei.u20221b471.main.medicalhistoryservice.domain.model.aggregates;


import jakarta.persistence.*;

import java.time.LocalDateTime;

@Entity
@Table(name ="weight_height_history")
public class WeightHeightHistory {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String dni;

    private Double peso;

    private Double altura;

    @Column(name = "fecha_registro")
    private LocalDateTime fechaRegistro;


    public WeightHeightHistory() {
    }

    public WeightHeightHistory(Long id, String dni, Double peso, Double altura, LocalDateTime fechaRegistro) {
        this.id = id;
        this.dni = dni;
        this.peso = peso;
        this.altura = altura;
        this.fechaRegistro = fechaRegistro;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

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

    public LocalDateTime getFechaRegistro() {
        return fechaRegistro;
    }

    public void setFechaRegistro(LocalDateTime fechaRegistro) {
        this.fechaRegistro = fechaRegistro;
    }
}
