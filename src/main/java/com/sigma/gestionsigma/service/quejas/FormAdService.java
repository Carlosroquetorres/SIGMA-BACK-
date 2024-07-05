package com.sigma.gestionsigma.service.quejas;

import java.util.List;

import com.sigma.gestionsigma.entity.quejas.FormAd;

public interface FormAdService {
     List<FormAd> findAllForme();
    FormAd saveForme(FormAd  formad);
    FormAd  updateForme(Long id,FormAd  department);
    void deleteForme(Long id);
    FormAd getFormularioById(Long id);

    
}
