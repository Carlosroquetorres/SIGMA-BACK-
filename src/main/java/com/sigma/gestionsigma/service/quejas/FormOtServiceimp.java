package com.sigma.gestionsigma.service.quejas;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;


import com.sigma.gestionsigma.entity.quejas.FormOt;
import com.sigma.gestionsigma.repository.quejas.FormOtRepository;

@Service
public class FormOtServiceimp implements FormOtService{

    @Autowired
    FormOtRepository formOtRepository;

    @Override
    public List<FormOt> findAllForme() {
        return formOtRepository.findAll();
    }

    @Override
    public FormOt saveForme(FormOt formot) {
       return formOtRepository.save(formot);
    }

    @Override
    public FormOt updateForme(Long folio, FormOt formot) {
        FormOt formeup = formOtRepository.findById(folio).get();
        //solo se actualizara si el dato departmen no es nulo ni esta vacio
        if(Objects.nonNull(formot.getDepartment()) && !"".equalsIgnoreCase(formot.getDepartment())){
            formeup.setDepartment(formot.getDepartment());
        }
        //solo se actualizara si el dato status no es nulo ni esta vacio
        if(Objects.nonNull(formot.getStatus()) && !"".equalsIgnoreCase(formot.getStatus())){
            formeup.setStatus(formot.getStatus());
        }

        return formOtRepository.save(formeup);
    }


    @Override
    public void deleteForme(Long folio) {
        formOtRepository.deleteById(folio);
       
    }
    
  
     @Override
    public FormOt getFormularioById(Long id) {
        return formOtRepository.findById(id).orElse(null);
    }

   

    

}
