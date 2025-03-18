package com.example.dio.controller;



import com.example.dio.service.QRCodeService;


import com.google.zxing.WriterException;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.IOException;

@RestController
@RequestMapping("${app.base-url}")
@Slf4j
@AllArgsConstructor
public class QRController {

    private QRCodeService qrCodeService;
    @GetMapping(value = "/qr", produces = MediaType.IMAGE_PNG_VALUE)
    public ResponseEntity<byte[]> generateQRCode(@RequestParam("url") String url) {
        try {
            // Generate a 250x250 QR code image for the provided URL
            byte[] qrImage = qrCodeService.generateQRCode(url);
            return ResponseEntity.ok().contentType(MediaType.IMAGE_PNG).body(qrImage);
        } catch (WriterException | IOException e) {
            log.error("Failed to generate QR code message: {}, rootCause: ", e.getMessage(), e.getCause());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).build();
        } catch (Exception e){
            throw new RuntimeException(e);
        }
    }
}

