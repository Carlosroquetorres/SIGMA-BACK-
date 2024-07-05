package com.sigma.gestionsigma.service.quejas;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.gestionsigma.entity.quejas.FormAd;
import com.sigma.gestionsigma.repository.quejas.FormAdRepository;

@Service
public class FormAdServiceimp implements FormAdService{

    @Autowired
    FormAdRepository formAdRepository;

    @Override
    public List<FormAd> findAllForme() {
        return formAdRepository.findAll();
    }

    @Override
    public FormAd saveForme(FormAd formad) {
       return formAdRepository.save(formad);
    }

    @Override
    public FormAd updateForme(Long id, FormAd formad) {
        FormAd formeup = formAdRepository.findById(id).get();
        //solo se actualizara si el dato departmen no es nulo ni esta vacio
        if(Objects.nonNull(formad.getDepartment()) && !"".equalsIgnoreCase(formad.getDepartment())){
            formeup.setDepartment(formad.getDepartment());
        }
        //solo se actualizara si el dato status no es nulo ni esta vacio
        if(Objects.nonNull(formad.getStatus()) && !"".equalsIgnoreCase(formad.getStatus())){
            formeup.setStatus(formad.getStatus());
        }

        return formAdRepository.save(formeup);
    }


    @Override
    public void deleteForme(Long id) {
        formAdRepository.deleteById(id);
       
    }

    @Override
    public FormAd getFormularioById(Long id) {
        return formAdRepository.findById(id).orElse(null);
    }

    

}
