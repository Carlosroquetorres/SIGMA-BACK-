package com.sigma.gestionsigma.service.quejas;

import java.util.List;

import com.sigma.gestionsigma.entity.quejas.FormOt;

public interface FormOtService {
     List<FormOt> findAllForme();
     FormOt saveForme(FormOt  formot);
     FormOt  updateForme(Long folio,FormOt department);
    void deleteForme(Long folio);
    FormOt getFormularioById(Long id);
}
