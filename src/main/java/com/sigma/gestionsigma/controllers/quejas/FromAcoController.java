package com.sigma.gestionsigma.controllers.quejas;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.poi.xwpf.usermodel.*;

import java.io.*;

import java.util.regex.Pattern;
import java.util.regex.Matcher;


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
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.thymeleaf.TemplateEngine;

import com.sigma.gestionsigma.entity.quejas.FormAco;
import com.sigma.gestionsigma.service.quejas.FormAcoService;

import jakarta.validation.Valid;
// formulario de Acoso 
@RestController
@CrossOrigin(origins = "http://localhost:19006")

public class FromAcoController {

     @Autowired
    TemplateEngine templateEngine;

    @Autowired
    FormAcoService formAcoService;

   
    @GetMapping("/findAllFormeAco")
    public List<FormAco> findAllForme(){
        return formAcoService.findAllForme();
    }

   
    @PostMapping("/saveFormAco")
    public FormAco saveForme(@Valid @RequestBody FormAco formaco){
        return formAcoService.saveForme(formaco);
    }
    
   
    // @PutMapping("/updateFormAD/{id}")
    // public FormAd updateForme(@PathVariable Long id,@RequestBody FormAd formad){
    //     return formAdService.updateForme(id, formad);
    // }

    @DeleteMapping("/deleteFormAco/{id}")
    public String deleteForme(@PathVariable Long id){
        formAcoService.deleteForme(id);
        return "Successfully deleted"; 
    }

    // enpoint uso de thymeleaf (plantillas de html)
    // @GetMapping("/generarDocumento/{id}")
    // public ResponseEntity<Resource> generarDocumento(@PathVariable Long id) {
    //     // Obtener los datos de la base de datos usando el ID
    //     FormAd formad = formAdService.getFormularioById(id);

    //     // Crear un contexto para la plantilla HTML
    //     Context context = new Context();
    //     context.setVariable("date", formad.getDate());
    //     context.setVariable("folio", formad.getFolio());
    //     context.setVariable("name", formad.getName());
    //     context.setVariable("lastname", formad.getLastname());
    //     context.setVariable("mail", formad.getMail());
    //     context.setVariable("phone", formad.getPhone());
    //     context.setVariable("complaint", formad.getComplaint());

    //     // Procesar la plantilla HTML con Thymeleaf
    //     String contenidoHtml = templateEngine.process("pg_0001", context);

    //     try {
    //         // Generar el PDF a partir del contenido HTML
    //         ITextRenderer renderer = new ITextRenderer();
    //         renderer.setDocumentFromString(contenidoHtml);
    //         renderer.layout();

    //         ByteArrayOutputStream outputStream = new ByteArrayOutputStream();
    //         renderer.createPDF(outputStream);

    //         // Convertir el contenido PDF a un recurso de byte
    //         ByteArrayResource resource = new ByteArrayResource(outputStream.toByteArray());

    //         // Devolver el archivo descargable como respuesta
    //         return ResponseEntity.ok()
    //                 .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=documento.pdf")
    //                 .contentType(MediaType.APPLICATION_PDF)
    //                 .contentLength(outputStream.size())
    //                 .body(resource);
    //     } catch (Exception e) {
    //         e.printStackTrace();
    //         return ResponseEntity.badRequest().build();
    //     }
    // }

    //endpoint de plantilla docx 
    @GetMapping("/generarDocumentoAco/{id}")
public ResponseEntity<Resource> generarDocumento(@PathVariable Long id) {
    try {

        
        
        // Obtener los datos de la base de datos usando el ID
        FormAco formaco = formAcoService.getFormularioById(id);

        // Cargar la plantilla .docx existente
        InputStream inputStream = getClass().getResourceAsStream("/name.docx");
        XWPFDocument document = new XWPFDocument(inputStream);

        
        
        String phoneString = String.valueOf(formaco.getPhone());
        String phoneTString = String.valueOf(formaco.getPhoneT());
        String dateString = String.valueOf(formaco.getDate());
        String datehString = String.valueOf(formaco.getDateH());
        String hourString = String.valueOf(formaco.getHour());


       
        // Modificar el contenido de la plantilla según sea necesario -- datos del acosado
        replacePlaceholder2(document, "folio", formaco.getFolio());
        replacePlaceholder2(document, "date", dateString);
        replacePlaceholder2(document, "name1", formaco.getName());
        replacePlaceholder2(document, "lastname1", formaco.getLastname());
        replacePlaceholder2(document, "gendre1", formaco.getGendre1());
        replacePlaceholder2(document, "phone", phoneString);
        replacePlaceholder2(document, "mail1", formaco.getMail());
        replacePlaceholder2(document, "puesto1", formaco.getPuesto1());
        //datos del acosador
        replacePlaceholder2(document, "nameD", formaco.getNameD());
        replacePlaceholder2(document, "lastnameD", formaco.getLastnameD());
        replacePlaceholder2(document, "gendre2", formaco.getGendre2());
        replacePlaceholder2(document, "puesto2", formaco.getPuesto2());
        replacePlaceholder2(document, "entityD", formaco.getEntityD());

        //descripcion de los hechos
        replacePlaceholder2(document, "dateO", datehString);
        replacePlaceholder2(document, "hour", hourString);
        replacePlaceholder2(document, "placE", formaco.getPlace());
        replacePlaceholder2(document, "frec", formaco.getFacts());
        replacePlaceholder2(document, "desch", formaco.getDescHechos());

        //datos del testigo
        replacePlaceholder2(document, "nameT", formaco.getNameT());
        replacePlaceholder2(document, "lastnameT", formaco.getLastnameT());
        replacePlaceholder2(document, "phoneT", phoneTString);
        replacePlaceholder2(document, "mail2", formaco.getMailT());
        replacePlaceholder2(document, "entityT", formaco.getEntityT());
        replacePlaceholder2(document, "puesto3", formaco.getPuesto3());
        

        // Convertir el documento a un arreglo de bytes
        ByteArrayOutputStream docxOutputStream = new ByteArrayOutputStream();
        document.write(docxOutputStream);
        document.close();

        // Devolver el documento .docx como recurso descargable
        ByteArrayResource resource = new ByteArrayResource(docxOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", formaco.getFolio()+".docx");

        return new ResponseEntity<>(resource, headers, HttpStatus.OK);
    } catch (Exception e) {
        return new ResponseEntity<>(HttpStatus.INTERNAL_SERVER_ERROR);
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

    
    

