package com.sigma.gestionsigma.service.quejas;

import java.util.List;

import com.sigma.gestionsigma.entity.quejas.FormInc;

public interface FormIncEticService {
     List<FormInc> findAllForminc();
     FormInc saveForminc(FormInc  forminc);
    //FormInc updateForme(Long id,FormInc  department);
    void deleteForminc(Long id);
    FormInc getFormularioById(Long id);

    
}
