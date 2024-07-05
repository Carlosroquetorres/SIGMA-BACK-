package com.sigma.gestionsigma.service.ventanilla;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sigma.gestionsigma.entity.ventanilla.FormTramite;
import com.sigma.gestionsigma.repository.ventanilla.FormUpTramRepository;

// formulario de subir documento de tramite
@Service
public class FormUpTramServiceimp implements FormUpTramService{


    @Autowired
    FormUpTramRepository FormUpTramRepository;

    @Override
    public List<FormTramite> findAllFormuptram() {
        return FormUpTramRepository.findAll();
    }

    @Override
    public FormTramite saveFormuptram(FormTramite formedit) {
       return FormUpTramRepository.save(formedit);
    }

    @Override
    public FormTramite updateFormuptram(Long id) {
        return null;
    }


    @Override
    public void deleteFormuptram(Long id) {
        FormUpTramRepository.deleteById(id);
       
    }

     @Override
    public FormTramite getFormularioById(Long id) {
        return FormUpTramRepository.findById(id).orElse(null);
    }

   

   
    
}
