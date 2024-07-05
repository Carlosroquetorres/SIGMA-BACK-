package com.sigma.gestionsigma.entity.quejas;

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
@Table(name = "formse")//nombre de la tabla donde se guardara los datos
@Data
@NoArgsConstructor
@AllArgsConstructor
public class FormOt {

    //datos del formulario de externos de quejas
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String folio;
    private String name;
    private String lastname;
    @NotBlank(message = "Pleas add the mail")
    private String mail;
    private long phone;
    private String ncontrol;
    private String career;
    private int semester;
    @NotBlank(message = "Pleas add the complaint")
    @Length(min = 50, max = 900)
    private String complaint;
    //datos administracion
    private String type;
    private Date date;
    private String department;
    private String status;
    private String nameAd;
    private String answerAd;
    
    @PrePersist
    public void beforeSave() {
        folio = "ITA-QSOT-" + id;
    }
   
}
