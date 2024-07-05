package com.sigma.gestionsigma.service.quejas;

import java.util.List;
import java.util.Objects;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.sigma.gestionsigma.entity.quejas.FormAco;
import com.sigma.gestionsigma.repository.quejas.FormAcoRepository;

@Service
public class FormAcoServiceimp implements FormAcoService{

    @Autowired
    FormAcoRepository formAcoRepository;

    @Override
    public List<FormAco> findAllForme() {
        return formAcoRepository.findAll();
    }

    @Override
    public FormAco saveForme(FormAco formad) {
       return formAcoRepository.save(formad);
    }

    // no creo que se util pero se usara para actualizar el documento (ya que es acoso no se debe editar)
    // @Override
    // public FormAco updateForme(Long id, FormAco formad) {
    //     FormAco formeup = formAcoRepository.findById(id).get();
    //     //solo se actualizara si el dato departmen no es nulo ni esta vacio
    //     if(Objects.nonNull(formad.getDepartment()) && !"".equalsIgnoreCase(formad.getDepartment())){
    //         formeup.setDepartment(formad.getDepartment());
    //     }
    //     //solo se actualizara si el dato status no es nulo ni esta vacio
    //     if(Objects.nonNull(formad.getStatus()) && !"".equalsIgnoreCase(formad.getStatus())){
    //         formeup.setStatus(formad.getStatus());
    //     }

    //     return formAcoRepository.save(formeup);
    // }


    @Override
    public void deleteForme(Long id) {
        formAcoRepository.deleteById(id);
       
    }

    @Override
    public FormAco getFormularioById(Long id) {
        return formAcoRepository.findById(id).orElse(null);
    }

    

}
