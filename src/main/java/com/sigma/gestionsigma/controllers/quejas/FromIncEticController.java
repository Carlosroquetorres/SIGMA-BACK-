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

import com.sigma.gestionsigma.entity.quejas.FormInc;
import com.sigma.gestionsigma.service.quejas.FormIncEticService;

import jakarta.validation.Valid;
// formulario de incumplimiento de las normas eticas
@RestController
@CrossOrigin(origins = "http://localhost:19006")

public class FromIncEticController{

     @Autowired
    TemplateEngine templateEngine;

    @Autowired
    FormIncEticService formIncEticService;

   
    @GetMapping("/findAllFormeInc")
    public List<FormInc> findAllForme(){
        return formIncEticService.findAllForminc();
    }

   
    @PostMapping("/saveformInc")
    public FormInc saveForme(@Valid @RequestBody FormInc forminc){
        return formIncEticService.saveForminc(forminc);
    }
    
   
    // @PutMapping("/updateFormInc/{id}")
    // public FormInc updateForminc(@PathVariable Long id,@RequestBody FormInc forminc){
    //     return formIncEticService.updateForminc(id, forminc);
    // }

    @DeleteMapping("/deleteformInc/{id}")
    public String deleteForme(@PathVariable Long id){
        formIncEticService.deleteForminc(id);
        return "Successfully deleted"; 
    }

    // enpoint uso de thymeleaf (plantillas de html)
    // @GetMapping("/generarDocumento/{id}")
    // public ResponseEntity<Resource> generarDocumento(@PathVariable Long id) {
    //     // Obtener los datos de la base de datos usando el ID
    //     FormInc forminc = formIncEticService.getFormularioById(id);

    //     // Crear un contexto para la plantilla HTML
    //     Context context = new Context();
    //     context.setVariable("date", forminc.getDate());
    //     context.setVariable("folio", forminc.getFolio());
    //     context.setVariable("name", forminc.getName());
    //     context.setVariable("lastname", forminc.getLastname());
    //     context.setVariable("mail", forminc.getMail());
    //     context.setVariable("phone", forminc.getPhone());
    //     context.setVariable("complaint", forminc.getComplaint());

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
    @GetMapping("/generarDocumentoInc/{id}")
public ResponseEntity<Resource> generarDocumento(@PathVariable Long id) {
    try {

        
        
        // Obtener los datos de la base de datos usando el ID
        FormInc forminc = formIncEticService.getFormularioById(id);

        // Cargar la plantilla .docx existente
        InputStream inputStream = getClass().getResourceAsStream("/name.docx");
        XWPFDocument document = new XWPFDocument(inputStream);

        
        
        String phoneString = String.valueOf(forminc.getPhone());
        String phoneTString = String.valueOf(forminc.getPhoneT());
        String dateString = String.valueOf(forminc.getDate());
        String datehString = String.valueOf(forminc.getDateH());
        String hourString = String.valueOf(forminc.getHour());


       
        // Modificar el contenido de la plantilla según sea necesario -- datos del acosado
        replacePlaceholder2(document, "folio", forminc.getFolio());
        replacePlaceholder2(document, "date", dateString);
        replacePlaceholder2(document, "name1", forminc.getName());
        replacePlaceholder2(document, "lastname1", forminc.getLastname());
        replacePlaceholder2(document, "gendre1", forminc.getGendre1());
        replacePlaceholder2(document, "phone", phoneString);
        replacePlaceholder2(document, "mail1", forminc.getMail());
        replacePlaceholder2(document, "puesto1", forminc.getPuesto1());
        //datos del acosador
        replacePlaceholder2(document, "nameD", forminc.getNameD());
        replacePlaceholder2(document, "lastnameD", forminc.getLastnameD());
        replacePlaceholder2(document, "gendre2", forminc.getGendre2());
        replacePlaceholder2(document, "puesto2", forminc.getPuesto2());
        replacePlaceholder2(document, "entityD", forminc.getEntityD());

        //descripcion de los hechos
        replacePlaceholder2(document, "dateO", datehString);
        replacePlaceholder2(document, "hour", hourString);
        replacePlaceholder2(document, "placE", forminc.getPlace());
        replacePlaceholder2(document, "frec", forminc.getFacts());
        replacePlaceholder2(document, "desch", forminc.getDescHechos());

        //datos del testigo
        replacePlaceholder2(document, "nameT", forminc.getNameT());
        replacePlaceholder2(document, "lastnameT", forminc.getLastnameT());
        replacePlaceholder2(document, "phoneT", phoneTString);
        replacePlaceholder2(document, "mail2", forminc.getMailT());
        replacePlaceholder2(document, "entityT", forminc.getEntityT());
        replacePlaceholder2(document, "puesto3", forminc.getPuesto3());
        

        // Convertir el documento a un arreglo de bytes
        ByteArrayOutputStream docxOutputStream = new ByteArrayOutputStream();
        document.write(docxOutputStream);
        document.close();

        // Devolver el documento .docx como recurso descargable
        ByteArrayResource resource = new ByteArrayResource(docxOutputStream.toByteArray());

        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_OCTET_STREAM);
        headers.setContentDispositionFormData("attachment", forminc.getFolio()+".docx");

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

    
    

