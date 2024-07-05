package com.sigma.gestionsigma.service.documentos;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sigma.gestionsigma.entity.documentos.FormUpDoc;
import com.sigma.gestionsigma.repository.documentos.FormUpDocRepository;

// formulario de subir documento
@Service
public class FormUpDocServiceimp implements FormUpDocService{


    @Autowired
    FormUpDocRepository FormUpDocRepository;

    @Override
    public List<FormUpDoc> findAllFormupdoc() {
        return FormUpDocRepository.findAll();
    }

    @Override
    public FormUpDoc saveFormupdoc(FormUpDoc formup) {
       return FormUpDocRepository.save(formup);
    }

    @Override
    public FormUpDoc updateFormupdoc(Long id) {
        return null;
    }


    @Override
    public void deleteFormupdoc(Long id) {
        FormUpDocRepository.deleteById(id);
       
    }

     @Override
    public FormUpDoc getFormularioById(Long id) {
        return FormUpDocRepository.findById(id).orElse(null);
    }

   

   
    
}
