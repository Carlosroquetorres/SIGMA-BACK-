package com.sigma.gestionsigma.service.ventanilla;
import java.util.List;

import com.sigma.gestionsigma.entity.ventanilla.FormTramite;

// formulario de subir docuemento de tramite
public interface FormUpTramService {
    
    List<FormTramite> findAllFormuptram();
    FormTramite saveFormuptram(FormTramite formuptram);
    FormTramite updateFormuptram(Long id);
    void deleteFormuptram(Long id);
    FormTramite getFormularioById(Long id);

}
