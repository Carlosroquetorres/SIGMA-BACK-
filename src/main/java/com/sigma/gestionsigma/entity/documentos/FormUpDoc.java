package com.sigma.gestionsigma.entity.documentos;

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
@Table(name = "formdoc")
@Data
@NoArgsConstructor

@AllArgsConstructor
// formulario de subir documentos
public class FormUpDoc {

    
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;
    private String folio;
    private Date date1;
    private Date date2;
    private String name;
    private String code;
    private String typedoc;
    private long revision;
    private String depdoc;
    private String userupdoc;
    private String usereditdoc;
    @NotBlank(message = "Pleas add a description")
    @Length(min = 25, max = 900)
    private String desceditdoc;
    @NotBlank(message = "Pleas add a description")
    @Length(min = 25, max = 900)
    private String description;

@PrePersist
    public void beforeSave() {
        folio = "ITA-"+code+"-" + id;
    }
    
}
