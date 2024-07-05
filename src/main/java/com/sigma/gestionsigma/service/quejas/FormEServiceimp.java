package com.sigma.gestionsigma.service.quejas;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sigma.gestionsigma.entity.quejas.FormE;
import com.sigma.gestionsigma.repository.quejas.FormERepository;

// formulario de estudiantes
@Service
public class FormEServiceimp implements FormEService{


    @Autowired
    FormERepository FormERepository;

    @Override
    public List<FormE> findAllForme() {
        return FormERepository.findAll();
    }

    @Override
    public FormE saveForme(FormE forme) {
       return FormERepository.save(forme);
    }

    @Override
    public FormE updateForme(Long id, FormE forme) {
        FormE formeup = FormERepository.findById(id).get();
        //solo se actualizara si el dato departmen no es nulo ni esta vacio
        if(Objects.nonNull(forme.getDepartment()) && !"".equalsIgnoreCase(forme.getDepartment())){
            formeup.setDepartment(forme.getDepartment());
        }
        //solo se actualizara si el dato status no es nulo ni esta vacio
        if(Objects.nonNull(forme.getStatus()) && !"".equalsIgnoreCase(forme.getStatus())){
            formeup.setStatus(forme.getStatus());
        }

        return FormERepository.save(formeup);
    }


    @Override
    public void deleteForme(Long id) {
        FormERepository.deleteById(id);
       
    }

     @Override
    public FormE getFormularioById(Long id) {
        return FormERepository.findById(id).orElse(null);
    }

   

   
    
}
