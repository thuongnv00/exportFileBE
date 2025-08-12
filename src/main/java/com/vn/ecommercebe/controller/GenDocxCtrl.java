package com.vn.ecommercebe.controller;

import com.vn.ecommercebe.request.InfoDTO;
import com.vn.ecommercebe.service.DocxReplaceService;
import com.vn.ecommercebe.service.MailService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.mail.MessagingException;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

@RestController
@CrossOrigin(origins = "*")
@RequestMapping("/api/doc")
public class GenDocxCtrl {
    @Autowired
    private  DocxReplaceService docxReplaceService;

    @Autowired
    private MailService mailService;


    public GenDocxCtrl(DocxReplaceService docxReplaceService) {
        this.docxReplaceService = docxReplaceService;
    }

    @GetMapping("/replace")
    public ResponseEntity<byte[]> replaceDocx(
            @RequestParam String name,
            @RequestParam String date) throws IOException {

        Map<String, String> replacements = new HashMap<>();
        replacements.put("name", name);
        replacements.put("date", date);

        byte[] bytes = docxReplaceService.replacePlaceholders(replacements);

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.docx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(bytes);
    }

    @PostMapping("/sendMail")
    public ResponseEntity<byte[]> senDocx(
            @RequestBody InfoDTO infoDTO) throws IOException, MessagingException {

        Map<String, String> replacements = new HashMap<>();
        replacements.put("name", infoDTO.getName());
        replacements.put("date", infoDTO.getDate());

        byte[] bytes = docxReplaceService.replacePlaceholders(replacements);
        mailService.constructEmail(infoDTO.getToEmail());

        return ResponseEntity.ok()
                .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=output.docx")
                .contentType(MediaType.parseMediaType(
                        "application/vnd.openxmlformats-officedocument.wordprocessingml.document"))
                .body(bytes);
    }
}
