package com.sigma.gestionsigma.service.quejas;
import java.util.List;

import com.sigma.gestionsigma.entity.quejas.FormE;

// formulario de estudiantes
public interface FormEService {
    
    List<FormE> findAllForme();
    FormE saveForme(FormE forme);
    FormE updateForme(Long id,FormE department);
    void deleteForme(Long id);
    FormE getFormularioById(Long id);

}
