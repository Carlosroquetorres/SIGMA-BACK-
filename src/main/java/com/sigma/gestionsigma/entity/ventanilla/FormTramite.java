package com.sigma.gestionsigma.entity.ventanilla;

import java.sql.Date;

import org.hibernate.validator.constraints.Length;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.PrePersist;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;


@Entity
@Table(name = "formtram")
@Data
@NoArgsConstructor

@AllArgsConstructor
// formulario de tramites
public class FormTramite {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String folio;
    private String name;
    @NotBlank(message = "Pleas add a description")
    @Length(min = 25, max = 900)
    private String description;
    private String user;
    private String modalidad;
    private String subdireccion;
    private String area;
    private String quienrealiza;
    private String donderealiza;
    private String timeresp;
    private long cost;
    private String dia1;
    private String dia2;
    private String hour1;
    private String hour2;
    private String queseobtiene;
    private String vigencia;
    private String requizitos;
    private String criterios;
    private String instruccion;
    private String enlace;

@PrePersist
    public void beforeSave() {
        folio = "ITA-"+name+"-" + id;
    }
    
}
