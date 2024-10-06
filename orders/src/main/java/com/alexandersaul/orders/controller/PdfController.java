package com.alexandersaul.orders.controller;

import com.alexandersaul.orders.service.IPdfService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;

@RestController
@RequestMapping("/api/pdf")
public class PdfController {

    @Autowired
    private IPdfService pdfGeneratorService;

    @GetMapping("/generate-boleta/{orderId}")
    public ResponseEntity<InputStreamResource> generatePdf(@PathVariable Long orderId) {
        ByteArrayInputStream bis = pdfGeneratorService.generatePdf(orderId);

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=generated.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }
}