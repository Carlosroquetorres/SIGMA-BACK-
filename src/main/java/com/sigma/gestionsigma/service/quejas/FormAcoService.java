package com.sigma.gestionsigma.service.quejas;

import java.util.List;

import com.sigma.gestionsigma.entity.quejas.FormAco;

public interface FormAcoService {
     List<FormAco> findAllForme();
    FormAco saveForme(FormAco  formad);
    //FormAco  updateForme(Long id,FormAco  department);
    void deleteForme(Long id);
    FormAco getFormularioById(Long id);

    
}
