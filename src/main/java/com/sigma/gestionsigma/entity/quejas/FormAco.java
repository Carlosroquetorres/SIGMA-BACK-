package com.sigma.gestionsigma.entity.quejas;

import java.sql.Date;
import java.sql.Time;

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
@Table(name = "formsaceti")//nombre de la tabla donde se guardara los datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormAco {

    //datos del formulario de acoso -- persona que denuncia
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String folio;
    private Date date;
    private String normaInc;
    private String name;
    private String lastname;
    private String gendre1;
    private long phone;
    @NotBlank(message = "Pleas add the mail")
    private String mail;
    private String puesto1;

    //persona denunciada
    private String nameD;
    private String lastnameD;
    private String gendre2;
    private String puesto2;
    private String entityD;

    //descripcion de los hechos
    private Date dateH;
    private String hour;
    private String place;
    private String facts;
    @NotBlank(message = "Pleas add the facts")
    @Length(min = 50, max = 900)
    private String DescHechos;


    //datos del testigo
    private String nameT;
    private String lastnameT;
    private long phoneT;
    private String mailT;
    private String entityT;
    private String puesto3;

    @PrePersist
    public void beforeSave() {
        folio = "ITA-ADV-" + id;
    }
    
}
