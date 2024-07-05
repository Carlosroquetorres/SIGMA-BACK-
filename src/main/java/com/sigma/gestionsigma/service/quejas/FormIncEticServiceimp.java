package com.sigma.gestionsigma.service.quejas;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.gestionsigma.entity.quejas.FormInc;
import com.sigma.gestionsigma.repository.quejas.FormIncRepository;

@Service
public class FormIncEticServiceimp implements FormIncEticService{

    @Autowired
    FormIncRepository FormIncRepository;

    @Override
    public List<FormInc> findAllForminc() {
        return FormIncRepository.findAll();
    }

    @Override
    public FormInc saveForminc(FormInc forminc) {
       return FormIncRepository.save(forminc);
    }

    // no creo que se util pero se usara para actualizar el documento (ya que es acoso no se debe editar)
    // @Override
    // public FormInc updateForme(Long id, FormInc forminc) {
    //     FormInc formeup = FormIncRepository.findById(id).get();
    //     //solo se actualizara si el dato departmen no es nulo ni esta vacio
    //     if(Objects.nonNull(formad.getDepartment()) && !"".equalsIgnoreCase(formad.getDepartment())){
    //         formeup.setDepartment(formad.getDepartment());
    //     }
    //     //solo se actualizara si el dato status no es nulo ni esta vacio
    //     if(Objects.nonNull(formad.getStatus()) && !"".equalsIgnoreCase(formad.getStatus())){
    //         formeup.setStatus(formad.getStatus());
    //     }

    //     return FormIncRepository.save(formeup);
    // }


    @Override
    public void deleteForminc(Long id) {
        FormIncRepository.deleteById(id);
       
    }

    @Override
    public FormInc getFormularioById(Long id) {
        return FormIncRepository.findById(id).orElse(null);
    }

    

}
