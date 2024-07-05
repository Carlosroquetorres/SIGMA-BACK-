package com.sigma.gestionsigma.controllers.quejas;

import java.util.List;
import org.apache.poi.xwpf.usermodel.*;

import java.io.*;



import org.springframework.core.io.Resource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ByteArrayResource;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.sigma.gestionsigma.entity.quejas.FormOt;
import com.sigma.gestionsigma.service.quejas.FormOtService;

import jakarta.validation.Valid;
// formulario de docentes y administrativos
@RestController
@CrossOrigin(origins = "http://localhost:19006")

public class FromOtController {

    @Autowired
    FormOtService formOtService;

   
    @GetMapping("/findAllFormeOT")
    public List<FormOt> findAllForme(){
        return formOtService.findAllForme();
    }

   
    @PostMapping("/saveFormOT")
    public FormOt saveForme(@Valid @RequestBody FormOt formad){
        return formOtService.saveForme(formad);
    }
   
    @PutMapping("/updateFormOT/{folio}")
    public FormOt updateForme(@PathVariable Long folio,@RequestBody FormOt formad){
        return formOtService.updateForme(folio, formad);
    }

    @DeleteMapping("/deleteFormOT/{folio}")
    public String deleteForme(@PathVariable Long folio){
        formOtService.deleteForme(folio);
        return "Successfully deleted"; 
    }

    //endpoint de plantilla docx 
    @GetMapping("/generarDocumentot/{id}")
public ResponseEntity<Resource> generarDocumento(@PathVariable Long id) {
    try {
        // Obtener los datos de la base de datos usando el ID
        FormOt formad = formOtService.getFormularioById(id);

        // Cargar la plantilla .docx existente
        InputStream inputStream = getClass().getResourceAsStream("/queja_administrador.docx");
        XWPFDocument document = new XWPFDocument(inputStream);

        String phoneString = String.valueOf(formad.getPhone());
        String dateString = String.valueOf(formad.getDate());
        //String compString = String.valueOf(formad.getComplaint());

        // Modificar el contenido de la plantilla según sea necesario
        replacePlaceholder(document, "folio", formad.getFolio());
        replacePlaceholder(document, "date", dateString);
        replacePlaceholder(document, "nome", formad.getName());
        replacePlaceholder(document, "lastname", formad.getLastname());
        replacePlaceholder(document, "mail", formad.getMail());
        replacePlaceholder(document, "phone", phoneString);
        replacePlaceholder2(document, "comp", formad.getComplaint());
                                                  
        // Convertir el documento a un arreglo de bytes
        ByteArrayOutputStream docxOutputStream = new ByteArrayOutputStream();
        document.write(docxOutputStream);
        document.close();

        // Devolver el documento .docx como recurso descargable
        ByteArrayResource resource = new ByteArrayResource(docxOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", formad.getFolio()+".docx");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
    }
}




 public static void replacePlaceholder(XWPFDocument document, String placeholder, String value) {
     // Recorre todos los párrafos del documento
     for (XWPFParagraph paragraph : document.getParagraphs()) {
        // Recorre todas las ejecuciones (partes de texto) dentro del párrafo
        for (XWPFRun run : paragraph.getRuns()) {
           // Obtiene el texto de la ejecución
             String text = run.getText(0);
             // Verifica si el texto contiene el marcador de posición
             if (text != null && text.contains(placeholder)) {
                 // Reemplaza el marcador de posición con el valor deseado
                run.setText(text.replace(placeholder, value), 0);
            }
         }
   }
 }

public static void replacePlaceholder2(XWPFDocument document, String placeholder, String value) {
    // Recorrer todas las tablas en el documento
    for (XWPFTable table : document.getTables()) {
        // Recorrer todas las filas en la tabla
        for (XWPFTableRow row : table.getRows()) {
            // Recorrer todas las celdas en la fila
            for (XWPFTableCell cell : row.getTableCells()) {
                // Obtener los párrafos en la celda
                for (XWPFParagraph paragraph : cell.getParagraphs()) {
                    // Recorrer todas las ejecuciones (partes de texto) dentro del párrafo
                    for (XWPFRun run : paragraph.getRuns()) {
                        // Obtener el texto de la ejecución
                        String text = run.getText(0);
                        // Verificar si el texto contiene el marcador de posición
                        if (text != null && text.contains(placeholder)) {
                            // Reemplazar el marcador de posición con el valor deseado
                            run.setText(text.replace(placeholder, value), 0);
                        }
                    }
                }
            }
        }
    }
}
    
}
