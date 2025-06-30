package com.project.bicicoletivo.entities;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "tb_bike")
public class Bike {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private String marca;

    @Column(nullable = false)
    private String modelo;

    @Column(nullable = false)
    private String color;


    @ManyToOne
    @JoinColumn(name = "proprietary_id")
    private User proprietary;

    @OneToMany(mappedBy = "bike")
    private List<Registration> registros = new ArrayList<>();

    public Bike() {
    }

    public Bike(String marca, String modelo, String color, User proprietary) {
        this.marca = marca;
        this.modelo = modelo;
        this.color = color;
        this.proprietary = proprietary;
    }

    public String getMarca() {
        return marca;
    }

    public void setMarca(String marca) {
        this.marca = marca;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getModelo() {
        return modelo;
    }

    public void setModelo(String model) {
        this.modelo = modelo;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public User getProprietary() {
        return proprietary;
    }

    public void setProprietary(User proprietary) {
        this.proprietary = proprietary;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Bike other = (Bike) o;
        return id != null && id.equals(other.id);
    }

    @Override
    public int hashCode() {
        return getClass().hashCode();
    }

}
