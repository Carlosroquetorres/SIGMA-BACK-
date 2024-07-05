package com.sigma.gestionsigma.controllers.documentos;



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


import com.sigma.gestionsigma.entity.documentos.FormUpDoc;
import com.sigma.gestionsigma.service.documentos.FormUpDocService;


import jakarta.validation.Valid;
// controlador de control de archvios
@RestController
@CrossOrigin(origins = "http://localhost:19006")
public class FormeDocController {

    @Autowired
    FormUpDocService FormUpDocService;

     @Autowired
    TemplateEngine templateEngine;


   
    @GetMapping("/findAllFormUpDoc")
    public List<FormUpDoc> findAllFormupdoc(){
        return FormUpDocService.findAllFormupdoc();
    }

   
    @PostMapping("/saveFormUpDoc")
    public FormUpDoc saveFormupdoc(@Valid @RequestBody FormUpDoc formupdoc){
        return FormUpDocService.saveFormupdoc(formupdoc);
    }
   
    @PutMapping("/updateFormUpDoc/{id}")
    public FormUpDoc updateFormupdoc(@PathVariable Long id,@RequestBody FormUpDoc formupdoc){
        return FormUpDocService.updateFormupdoc(id);
    }

    @DeleteMapping("/deleteFormUpDoc/{id}")
    public String deleteFormupdoc(@PathVariable Long id){
        FormUpDocService.deleteFormupdoc(id);
        return "Successfully deleted"; 
    }

    
}
