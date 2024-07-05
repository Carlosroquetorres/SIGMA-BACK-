package com.sigma.gestionsigma.controllers.ventanilla;



import java.util.List;

import java.io.*;




import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;


import com.sigma.gestionsigma.entity.ventanilla.FormTramite;
import com.sigma.gestionsigma.service.ventanilla.FormUpTramService;


import jakarta.validation.Valid;
// controlador de ventanilla
@RestController
@CrossOrigin(origins = "http://localhost:19006")
public class FormeTramitController {

    @Autowired
    FormUpTramService FormUpTramService;

     @Autowired
    TemplateEngine templateEngine;


   
    @GetMapping("/findAllFormUpTram")
    public List<FormTramite> findAllFormuptram(){
        return FormUpTramService.findAllFormuptram();
    }

   
    @PostMapping("/saveFormUpTram")
    public FormTramite saveFormuptram(@Valid @RequestBody FormTramite formuptram){
        return FormUpTramService.saveFormuptram(formuptram);
    }
   
    @PutMapping("/updateFormUpTram/{id}")
    public FormTramite updateFormuptram(@PathVariable Long id,@RequestBody FormTramite formuptram){
        return FormUpTramService.updateFormuptram(id);
    }

    @DeleteMapping("/deleteFormUpTram/{id}")
    public String deleteFormuptram(@PathVariable Long id){
        FormUpTramService.deleteFormuptram(id);
        return "Successfully deleted"; 
    }

    
}
