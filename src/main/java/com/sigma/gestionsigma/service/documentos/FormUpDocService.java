package com.sigma.gestionsigma.service.documentos;
import java.util.List;

import com.sigma.gestionsigma.entity.documentos.FormUpDoc;

// formulario de subir docuemento
public interface FormUpDocService {
    
    List<FormUpDoc> findAllFormupdoc();
    FormUpDoc saveFormupdoc(FormUpDoc forme);
    FormUpDoc updateFormupdoc(Long id);
    void deleteFormupdoc(Long id);
    FormUpDoc getFormularioById(Long id);

}
